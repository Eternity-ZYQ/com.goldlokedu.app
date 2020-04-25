import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

'选择单选还是多选'
set_method()

'生成多少个选项'
set_vote_options_size()

if(method==1){
'生成最大选择数'
set_max_option_amount()
}

'生成投票标题'
set_title()

'生成过期时间'
set_expiration_time(1)

'生成投票选项'
set_vote_options(vote_options_size)

'生成联系人列表数据'
vote_participants=vote_participants_factory()


'发送文本投票'
publish_vote_text(max_option_amount,title,expiration,vote_options,vote_range,vote_participants,method)






//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}



//发布动态
def void publish_vote_text(int max_option_amount,String title,String expiration,String vote_options,String vote_range,String vote_participants,int method){
	'发送文本投票接口'
	WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Vote/publish_vote_text",[('max_option_amount'):max_option_amount,('title'):title,('expiration'):expiration,('vote_options'):vote_options,('vote_range'):vote_range,('vote_participants'):vote_participants,('method'):method]), FailureHandling.CONTINUE_ON_FAILURE)

}




//获取一级加载级联系人列表：班级
def Object get_first_order_contact(String object_path){
	'发送获取学生联系人接口请求'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(object_path), FailureHandling.CONTINUE_ON_FAILURE)
	WS.comment('一级加载级联系人列表：班级:'+response.getResponseText())

	return get_jsonResponse(response)

	
}

//获取二级加载学生联系人列表
def Object get_second_order_contact(String object_path,String class_id){
	'发送获取学生联系人接口请求'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(object_path,[('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
	WS.comment('二级加载学生联系人列表:'+response.getResponseText())

	return get_jsonResponse(response)
	
}

//获取二级加载分组联系人列表
def Object get_group_second_order_contact(String object_path,String group_id){
	'发送获取学生联系人接口请求'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(object_path,[('group_id'):group_id]), FailureHandling.CONTINUE_ON_FAILURE)
	WS.comment('二级加载分组联系人列表:'+response.getResponseText())

	return get_jsonResponse(response)
}




//{
//	"group_participants": {
//		"group_ids": [{
//			"ids": [],
//			"id_type": "student"
//		}, {
//			"id_type": "dorm",
//			"ids": []
//		}]
//	},
//	"teacher_participants": {
//		"department_ids": [],
//		"teacher_ids": []
//	},
//	"student_participants": {
//		"student_ids": [],
//		"class_ids": []
//	}
//}


//组合短信接口请求body字段vote_participants
def String get_vote_participants(String teacher_participants,String student_participants,String group_participants){
	def vote_participants='''
		"teacher_participants": {'''+teacher_participants+'''},
		"group_participants": {'''+group_participants+'''},
		"student_participants": {'''+student_participants+'''}
				'''
	
	
	return vote_participants
}



//	"teacher_participants": {
//		"department_ids": [],
//		"teacher_ids": []
//	}
//组合短信接口请求body字段teacher_address
def String get_teacher_participants(String teacher_ids,String department_ids){
	
	def teacher_participants='''
							"teacher_ids": ['''+teacher_ids+'''],
							"department_ids": ['''+department_ids+''']
						'''
	
	return teacher_participants
}



//	"group_participants": {
//		"group_ids": [{
//			"ids": [],
//			"id_type": "student"
//		}, {
//			"id_type": "dorm",
//			"ids": []
//		}]
//	}
//组合短信接口请求body字段groups_address
def String get_group_participants(String groip_student_ids,String dorm_ids){
	
	def group_participants='''
							"group_ids": [{
							"id_type": "student",
							"ids": ['''+groip_student_ids+''']
							}, 
							{
							"id_type": "dorm",
							"ids": ['''+dorm_ids+''']
							}]
						'''
	
	return group_participants
}





//	"student_participants": {
//		"student_ids": [],
//		"class_ids": []
//	}
//组合短信接口请求body字段student_address
def String get_student_participants(String student_ids,String klass_ids){
	
	def student_participants='''
							"student_ids": ['''+student_ids+'''],
							"class_ids": ['''+klass_ids+''']
						'''

	return student_participants
}




//address进行组合
def String vote_participants_factory(){
	
	def teacher_ids     		//teacher_ids 教师id字段
	def num						//未分组随机数
	def num1					//其他部门随机数
	def department_ids			//部门id字段
	def teacher_participants		//教师及部门participants
	def student_address			//班级及学生participants
	def student_ids				//学生id字段
	def klass_ids				//班级id字段
	def num2					//班级随机数
	def num3					//学生随机数
	def groups_address			//宿舍及学生participants
	def groip_student_ids		//分组学生id
	def dorm_ids				//分组宿舍id
	def num4					//宿舍随机数
	def num5					//分组学生随机数
	
	"请求教师通讯录接口获取数据"
	def teacher_jsonResponse=get_first_order_contact("Object Repository/Api/Mobile Api/Campus/Contact/Teacher/teacher_contact_new_no_rule")
	WS.comment('教师通讯录接口获取数据：'+teacher_jsonResponse)
	//教师模块逻辑：若是只有未分组，则在未分组中随机拿一个教师发送，若是存在未分组和其他部门，则其他部门随机拿一个部门及未分组随机一个教师
	if(teacher_jsonResponse.data.children.size==1&&teacher_jsonResponse.data.children[0].sort_num==-1){
		//只有未分组，则只拿一个teacher_id，department_id为空
		num=(int)(Math.random()*1000)%teacher_jsonResponse.data.children[0].children.size
		teacher_ids='"'+teacher_jsonResponse.data.children[0].children[num].user_id+'"'
		department_ids=''
		vote_range+=teacher_jsonResponse.data.children[0].children[num].name
		
		WS.comment('添加未分组教师后：'+vote_range)
		
		
		
	}else if(teacher_jsonResponse.data.children.size>1){
		
		WS.comment('没加数据之前：'+vote_range)
		num=(int)(Math.random()*1000)%teacher_jsonResponse.data.children[0].children.size
		teacher_ids='"'+teacher_jsonResponse.data.children[0].children[num].user_id+'"'
		num1=(int)(Math.random()*1000)%(teacher_jsonResponse.data.children.size-1)+1
		department_ids='"'+teacher_jsonResponse.data.children[num1].department_id+'"'
		vote_range+=teacher_jsonResponse.data.children[0].children[num].name+','+teacher_jsonResponse.data.children[num1].name
		WS.comment('加了数据之后：'+vote_range)

	}
	
	teacher_participants=get_teacher_participants(teacher_ids,department_ids)
	
	
	
	'请求学生通讯录接口获取班级数据'
	def student_class_jsonResponse=get_first_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Student/student_class_contact_new_no_rule')
	WS.comment('学生通讯录接口获取班级数据'+student_class_jsonResponse)
	if(student_class_jsonResponse.data.size==0){
		
		student_ids=''
		klass_ids=''
		
		
	}else if(student_class_jsonResponse.data.size==1){
		
		klass_ids='"'+student_class_jsonResponse.data[0].id+'"'
		vote_range+=','+student_class_jsonResponse.data[0].name
		WS.comment('只有一个班级,添加班级后'+vote_range)
	
	
	}else{
		klass_ids='"'+student_class_jsonResponse.data[0].id+'"'
		WS.comment('班级id:'+klass_ids)
		vote_range+=','+student_class_jsonResponse.data[0].name
		WS.comment('多个班级添加其中一个班级后'+vote_range)
		num2=(int)(Math.random()*1000)%(student_class_jsonResponse.data.size-1)+1
		
		'请求学生通讯录接口获取班内学生数据'
		def student_jsonResponse=get_second_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Student/student_contact_new_no_rule',student_class_jsonResponse.data[num2].id)
		WS.comment('班内学生数据'+student_jsonResponse)
		WS.comment('班内学生size'+student_jsonResponse.data.size)
		num3=(int)(Math.random()*1000)%(student_jsonResponse.data.size)
		
		student_ids='"'+student_jsonResponse.data[num3].user_id+'"'
		vote_range+=','+student_jsonResponse.data[num3].name
		WS.comment('多个班级添加其中一个班级中的学生后'+vote_range)
	}
	student_address=get_student_participants(student_ids,klass_ids)
	
	
	'请求分组通讯录获取分组数据'
	def group_jsonResponse=get_first_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Group/group_contact_new_no_rule')
	WS.comment('分组通讯录获取分组数据:'+group_jsonResponse)
	if(group_jsonResponse.data.children.size==0){
		
		groip_student_ids=''
		dorm_ids=''
		
	}else{
			
		if(group_jsonResponse.data.children[0].children.size==1){
			groip_student_ids=''
			dorm_ids='"'+group_jsonResponse.data.children[0].children[0].id+'"'
			vote_range+=','+group_jsonResponse.data.children[0].children[0].name
			//WS.comment(vote_range)
		}else if(group_jsonResponse.data.children[0].children.size>1){
		
			dorm_ids='"'+group_jsonResponse.data.children[0].children[0].id+'"'
			num4=(int)(Math.random()*1000)%(group_jsonResponse.data.children[0].children.size-1)+1
			
			'请求分组通讯录接口获取分组下的学生数据列表'
			def group_student_jsonResponse=get_group_second_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Group/group_user_contact_new_no_rule',group_jsonResponse.data.children[0].children[num4].id)
			//WS.comment(''+group_jsonResponse.data.children[0].children[num4].id)
			//WS.comment(''+group_student_jsonResponse.data.size)
			//WS.comment(''+group_student_jsonResponse)
			num5=(int)(Math.random()*1000)%(group_student_jsonResponse.data.size)
			groip_student_ids='"'+group_student_jsonResponse.data[num5].id+'"'
			
			vote_range+=','+group_jsonResponse.data.children[0].children[0].name+','+group_student_jsonResponse.data[num5].name
			//WS.comment(vote_range)
		
		}
			
	}
	
	groups_address=get_group_participants(groip_student_ids,dorm_ids)
	
	vote_range='"'+vote_range+'"'
	//WS.comment('出来前'+address_str)
	
	return get_vote_participants(teacher_participants,student_address,groups_address)
	
	
}



//生成过期时间,比现在迟几年
def void set_expiration_time(int year){
	
	expiration=CustomKeywords.'time.SystemTime.get_future_time'(year)
	
}

//生成投票标题
def void set_title(){
	
	 title=CustomKeywords.'time.SystemTime.get_system_time'()+'发布文本投票标题'
	
}

//单选还是多选
def void set_method(){
	
	method=(int)(Math.random()*1000)%2
	
}

//生成投票最多选择数
def void set_max_option_amount(){
	
	max_option_amount=(int)(Math.random()*1000)%vote_options_size+1
	
}


//生成建立多少选项数
def void set_vote_options_size(){
	while(vote_options_size<2){
		
	vote_options_size=(int)(Math.random()*1000)%12+1
	
	}
}



//[
//	{
//	  "sequential": 0,
//	  "content": {
//		  "content": "测试选项1"
//	  }
//  }, {
//	  "content": {
//		  "content": "测试选项2"
//	  },
//	  "sequential": 1
//  }, {
//	  "content": {
//		  "content": "测试选项3"
//	  },
//	  "sequential": 2
//  }]

//生成投票选项内容
def void set_vote_options(int size){
	
	List list=new ArrayList()
	for(int x:(0..size-1)){
		
		def options='{"sequential":'+x+',"content": {"content": "测试投票文本选项'+(x+1)+'"}}'
		list.add(options)
	  }
	
	vote_options=list.toString()

  }
		




