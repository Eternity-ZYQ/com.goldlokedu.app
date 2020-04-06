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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable

'判断该学校是否开通了短信业务'
ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/can_send'))

def jsonResponse = get_jsonResponse(response)

//必须该学校开通了短信，才能发送短信
if (jsonResponse.can_send) {
//    '成功发送及时短信'
//    success_now_sms()
//	
	'发送定时短信'
	success_timed_sms()
} else {
    WS.comment('该学校未开通短信服务')
}








//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
    def jsonSlurper = new JsonSlurper()

    def jsonResponse = jsonSlurper.parseText(response.getResponseText())

    return jsonResponse
}

def void success_now_sms() {
	//成功发送即时短信
	//sms_type,sms_signature,sms_signature_id,sms_content
	'保存sms_type到全局变量'
	save_sms_type()
	
	'保存sms_signature，sms_signature_id'
	save_sms_signature_and_sms_signature_id()
	
	'获取要发送的短信内容sms_content'
	def sms_content=get_sms_contnt()
	
	'输出sms_content'
	WS.comment(sms_content)

	'组合接口字段address'
	def address=address_factory()
	
	'请求发送短信接口'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/sms_sending',[('sms_content'):sms_content,('teacher_ids'):GlobalVariable.user_id,('address'):address,('address_str'):address_str]))
	
	//WS.comment(response.responseText)
	
	//请求接口成功
	if(WS.verifyResponseStatusCode(response, 200)){
		
		"文本result值:Success"
		WS.verifyElementPropertyValue(response, 'result', 'Success')
	
	}
		
}


def void success_timed_sms() {
	//成功发送定时短信
	//sms_type,sms_signature,sms_signature_id,sms_content
	'保存sms_type到全局变量'
	save_sms_type()
	
	'保存sms_signature，sms_signature_id'
	save_sms_signature_and_sms_signature_id()
	
	'获取要发送的短信内容sms_content'
	def sms_content=get_time_sms_contnt()
	
	'输出sms_content'
	WS.comment(sms_content)

	'组合接口字段address'
	def address=address_factory()
	
	'设置定时发送时间：目前时间+1年'
	sms_timed=CustomKeywords.'time.SystemTime.get_future_time'(1)
	
	
	'请求发送定时短信接口'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/sms_timed',[('sms_content'):sms_content,('teacher_ids'):GlobalVariable.user_id,('address'):address,('address_str'):address_str,('sms_timed'):sms_timed]))
	
	//WS.comment(response.responseText)
	
	//请求接口成功
	if(WS.verifyResponseStatusCode(response, 200)){
		
		"文本result值:Success"
		WS.verifyElementPropertyValue(response, 'result', 'Success')
	
	}
		
}

//保存sms_type到全局变量
def void save_sms_type() {
	
    '发送获取sms_type接口'
    ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/sms_type'))

    def jsonResponse = get_jsonResponse(response)

	def num=(int)(Math.random()*100)%jsonResponse.data.size
	
	WS.comment('sms_type:'+jsonResponse.data[num].category_id)
	
	
	CustomKeywords.'public_method.Helper.addGlobalVariable'('sms_type', jsonResponse.data[num].category_id)
}

//保存sms_signature，sms_signature_id到全局变量
def void save_sms_signature_and_sms_signature_id(){
	
	'发送获取sms_signature，sms_signature_id接口'
	ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/sms_signature'))

	def jsonResponse = get_jsonResponse(response)
	
	def num=(int)(Math.random()*100)%jsonResponse.data.size
	//WS.comment('size:'+jsonResponse.data.size)
	//WS.comment('num:'+num)
	//WS.comment('sms_signature:'+jsonResponse.data[num].name)
	//WS.comment('sms_signature_id'+jsonResponse.data[num].signature_id)
	
	'保存sms_signature到全局变量'
	CustomKeywords.'public_method.Helper.addGlobalVariable'('sms_signature', jsonResponse.data[num].name)
	'保存sms_signature_id到全局变量'
	CustomKeywords.'public_method.Helper.addGlobalVariable'('sms_signature_id', jsonResponse.data[num].signature_id)
	
	
}

//获取发送短信的内容：当前时间时间+'接口测试及时短信'
def String get_sms_contnt(){
	
	def time=CustomKeywords.'time.SystemTime.get_system_time'()
	
	def sms_content=time+'接口测试及时短信'
	
	return sms_content
	
}

//获取发送定时短信的内容：当前时间时间+'接口测试定时短信'
def String get_time_sms_contnt(){
	
	def time=CustomKeywords.'time.SystemTime.get_system_time'()
	
	def sms_content=time+'接口测试定时短信'
	
	return sms_content
	
}


//获取一级加载级联系人列表：班级
def Object get_first_order_contact(String object_path){
	'发送获取学生联系人接口请求'
	ResponseObject response=WS.sendRequest(findTestObject(object_path))
	
	if(WS.verifyResponseStatusCode(response, 200)){
		
		WS.verifyElementPropertyValue(response, 'code', 200)
		WS.verifyElementPropertyValue(response, 'message', '操作成功')
		
		return get_jsonResponse(response)
	}
	return 
	
}

//获取二级加载学生联系人列表
def Object get_second_order_contact(String object_path,String class_id){
	'发送获取学生联系人接口请求'
	ResponseObject response=WS.sendRequest(findTestObject(object_path,[('class_id'):class_id]))
	
	if(WS.verifyResponseStatusCode(response, 200)){
		
		WS.verifyElementPropertyValue(response, 'code', 200)
		WS.verifyElementPropertyValue(response, 'message', '操作成功')
			
		
		return get_jsonResponse(response)
	}
	
	return
	
}

//获取二级加载分组联系人列表
def Object get_group_second_order_contact(String object_path,String group_id){
	'发送获取学生联系人接口请求'
	ResponseObject response=WS.sendRequest(findTestObject(object_path,[('group_id'):group_id]))
	
	if(WS.verifyResponseStatusCode(response, 200)){
		
		WS.verifyElementPropertyValue(response, 'code', 200)
		WS.verifyElementPropertyValue(response, 'message', '操作成功')
			
		
		return get_jsonResponse(response)
	}
	
	return
	
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
	"请求教师通讯录接口获取数据"
	def teacher_jsonResponse=get_first_order_contact("Object Repository/Api/Mobile Api/Campus/Contact/Teacher/sms_teacher_contact")
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
	
	//教师模块逻辑：若是只有未分组，则在未分组中随机拿一个教师发送，若是存在未分组和其他部门，则其他部门随机拿一个部门及未分组随机一个教师
	if(teacher_jsonResponse.data.children.size==1&&teacher_jsonResponse.data.children[0].sort_num==-1){
		//WS.comment('进入未分组数据：'+teacher_jsonResponse)
		//只有未分组，则只拿一个teacher_id，department_id为空
		num=(int)(Math.random()*1000)%teacher_jsonResponse.data.children[0].children.size
		teacher_ids='"'+teacher_jsonResponse.data.children[0].children[num].user_id+'"'
		department_ids=''
		address_str+='"'+teacher_jsonResponse.data.children[0].children[num].name+'"'
		
		//WS.comment('添加未分组教师后：'+address_str)
		
		
		
	}else if(teacher_jsonResponse.data.children.size>1){
		//WS.comment('没有进入未分组数据：'+teacher_jsonResponse)
		//WS.comment('没加数据之前：'+address_str)
		num=(int)(Math.random()*1000)%teacher_jsonResponse.data.children[0].children.size
		teacher_ids='"'+teacher_jsonResponse.data.children[0].children[num].user_id+'"'		
		num1=(int)(Math.random()*1000)%(teacher_jsonResponse.data.children.size-1)+1
		department_ids='"'+teacher_jsonResponse.data.children[num1].department_id+'"'
		address_str+='"'+teacher_jsonResponse.data.children[0].children[num].name+'",'+'"'+teacher_jsonResponse.data.children[num1].name+'"'
		//WS.comment('加了数据之后：'+address_str)

	}
	
	teacher_address=get_teacher_address(teacher_ids,department_ids)
	
	
	
	'请求学生通讯录接口获取班级数据'
	def student_class_jsonResponse=get_first_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Student/sms_student_class_contact')
	
	if(student_class_jsonResponse.data.size==0){
		
		student_ids=''
		klass_ids=''
		
		
	}else if(student_class_jsonResponse.data.size==1){
		
		klass_ids='"'+student_class_jsonResponse.data[0].id+'"'
		address_str+=',"'+student_class_jsonResponse.data[0].name+'"'
		//WS.comment(address_str)
	
	
	}else{
		klass_ids='"'+student_class_jsonResponse.data[0].id+'"'
		address_str+=',"'+student_class_jsonResponse.data[0].name+'"'
		num2=(int)(Math.random()*1000)%(student_class_jsonResponse.data.size-1)+1
		
		'请求学生通讯录接口获取班内学生数据'
		def student_jsonResponse=get_second_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Student/sms_student_contact',student_class_jsonResponse.data[num2].id)
		num3=(int)(Math.random()*1000)%(student_jsonResponse.data.size)
		
		student_ids='"'+student_jsonResponse.data[num3].user_id+'"'
		address_str+=',"'+student_jsonResponse.data[num3].name+'"'
		//WS.comment(address_str)
	}
	student_address=get_student_address(student_ids,klass_ids)
	
	
	'请求分组通讯录获取分组数据'
	def group_jsonResponse=get_first_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Group/sms_group_contact')
	
	if(group_jsonResponse.data.children.size==0){
		
		groip_student_ids=''
		dorm_ids=''
		
	}else{
			
		if(group_jsonResponse.data.children[0].children.size==1){
			
			dorm_ids='"'+group_jsonResponse.data.children[0].children[0].id+'"'
			address_str+=',"'+group_jsonResponse.data.children[0].children[0].name+'"'
			//WS.comment(address_str)
		}else if(group_jsonResponse.data.children[0].children.size>1){
		
			dorm_ids='"'+group_jsonResponse.data.children[0].children[0].id+'"'
			num4=(int)(Math.random()*1000)%(group_jsonResponse.data.children[0].children.size-1)+1
			
			'请求分组通讯录接口获取分组下的学生数据列表'
			def group_student_jsonResponse=get_group_second_order_contact('Object Repository/Api/Mobile Api/Campus/Contact/Group/sms_group_user_contact',group_jsonResponse.data.children[0].children[num4].id)
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

