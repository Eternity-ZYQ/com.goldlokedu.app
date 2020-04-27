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

'生成通知标题'
setTitle()

'生成通知内容'
setContent()

'生成联系人数据address'
def address=address_factory()

'发送通知接口'

publish_notification(address,address_str,title,content)









//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	def jsonSlurper = new JsonSlurper()
	
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())

	return jsonResponse
}

//生成通知标题:时间+测试存文本通知标题
def void setTitle(){
	
	title=CustomKeywords.'time.SystemTime.get_system_time'()+'测试纯文本通知标题'
	
}



//生成通知内容:时间+发布存文本通知内容
def void setContent(){
	
	content=CustomKeywords.'time.SystemTime.get_system_time'()+'发布纯文本通知内容'
	
}


//发送通知
def void publish_notification(String address,String address_str,String title,String content){
	
	WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Notification/publish_notification", [('address'):address,('address_str'):address_str,('title'):title,('content'):content]), FailureHandling.CONTINUE_ON_FAILURE)
	
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


//组合短信接口请求body字段address
def String get_address(String teacher_address,String student_address,String groups_address){
	def address='''
		"teacher_address": {'''+teacher_address+'''},
		"groups_address": {'''+groups_address+'''},
		"student_address": {'''+student_address+'''}
				'''
	
	
	return address
}


//组合短信接口请求body字段teacher_address
def String get_teacher_address(String teacher_ids,String department_ids){
	
	def teacher_address='''
							"teacher_ids": ['''+teacher_ids+'''],
							"department_ids": ['''+department_ids+''']
						'''
	
	return teacher_address
}

//组合短信接口请求body字段groups_address
def String get_groups_address(String groip_student_ids,String dorm_ids){
	
	def groups_address='''
							"group_ids": [{
							"id_type": "student",
							"ids": ['''+groip_student_ids+''']
							}, 
							{
							"id_type": "dorm",
							"ids": ['''+dorm_ids+''']
							}]
						'''
	
	return groups_address
}

//组合短信接口请求body字段student_address
def String get_student_address(String student_ids,String klass_ids){
	
	def student_address='''
							"student_ids": ['''+student_ids+'''],
							"klass_ids": ['''+klass_ids+''']
						'''

	return student_address
}



//address进行组合
def String address_factory(){
	
	def teacher_ids     		//teacher_ids 教师id字段
	def num						//未分组随机数
	def num1					//其他部门随机数
	def department_ids			//部门id字段
	def teacher_address			//教师及部门address
	def student_address			//班级及学生address
	def student_ids				//学生id字段
	def klass_ids				//班级id字段
	def num2					//班级随机数
	def num3					//学生随机数
	def groups_address			//宿舍及学生address
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
		address_str+='"'+teacher_jsonResponse.data.children[0].children[num].name+'"'
		
		WS.comment('添加未分组教师后：'+address_str)
		
		
		
	}else if(teacher_jsonResponse.data.children.size>1){
		
		WS.comment('没加数据之前：'+address_str)
		num=(int)(Math.random()*1000)%teacher_jsonResponse.data.children[0].children.size
		teacher_ids='"'+teacher_jsonResponse.data.children[0].children[num].user_id+'"'		
		num1=(int)(Math.random()*1000)%(teacher_jsonResponse.data.children.size-1)+1
		department_ids='"'+teacher_jsonResponse.data.children[num1].department_id+'"'
		address_str+='"'+teacher_jsonResponse.data.children[0].children[num].name+'",'+'"'+teacher_jsonResponse.data.children[num1].name+'"'
		WS.comment('加了数据之后：'+address_str)

	}
	
	teacher_address=get_teacher_address(teacher_ids,department_ids)
	
	
	
	'请求学生通讯录接口获取班级数据'
	def student_class_jsonResponse=get_first_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Student/student_class_contact_new_no_rule')
	WS.comment('学生通讯录接口获取班级数据'+student_class_jsonResponse)
	if(student_class_jsonResponse.data.size==0){
		
		student_ids=''
		klass_ids=''
		
		
	}else if(student_class_jsonResponse.data.size==1){
		
		klass_ids='"'+student_class_jsonResponse.data[0].id+'"'
		address_str+=',"'+student_class_jsonResponse.data[0].name+'"'
		WS.comment('只有一个班级,添加班级后'+address_str)
	
	
	}else{
		klass_ids='"'+student_class_jsonResponse.data[0].id+'"'
		WS.comment('班级id:'+klass_ids)
		address_str+=',"'+student_class_jsonResponse.data[0].name+'"'
		WS.comment('多个班级添加其中一个班级后'+address_str)
		num2=(int)(Math.random()*1000)%(student_class_jsonResponse.data.size-1)+1
		
		'请求学生通讯录接口获取班内学生数据'
		def student_jsonResponse=get_second_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Student/student_contact_new_no_rule',student_class_jsonResponse.data[num2].id)	
		WS.comment('班内学生数据'+student_jsonResponse)
		WS.comment('班内学生size'+student_jsonResponse.data.size)
		num3=(int)(Math.random()*1000)%(student_jsonResponse.data.size)
		
		student_ids='"'+student_jsonResponse.data[num3].user_id+'"'
		address_str+=',"'+student_jsonResponse.data[num3].name+'"'
		WS.comment('多个班级添加其中一个班级中的学生后'+address_str)
	}
	student_address=get_student_address(student_ids,klass_ids)
	
	
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
			address_str+=',"'+group_jsonResponse.data.children[0].children[0].name+'"'
			//WS.comment(address_str)
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
			
			address_str+=',"'+group_jsonResponse.data.children[0].children[0].name+'",'+'"'+group_student_jsonResponse.data[num5].name+'"'
			//WS.comment(address_str)
		
		}
			
	}
	
	groups_address=get_groups_address(groip_student_ids,dorm_ids)
	
	//WS.comment('出来前'+address_str)
	
	return get_address(teacher_address,student_address,groups_address)
	
	
}