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

'查看班主任接口路径'
def path='Object Repository/Api/Mobile Api/My/Individual/Student/student_get_adviser_teacher'
'发送文本消息接口路径'
def path1='Object Repository/Api/Mobile Api/Campus/Contact Teacher/send_chat_message'
'获取最新消息接口路径'
def path2='Object Repository/Api/Mobile Api/Campus/Contact Teacher/poll_new_chat_record'

'获取班主任信息'
ResponseObject teacher_msg_response=WS.sendRequestAndVerify(findTestObject(path), FailureHandling.CONTINUE_ON_FAILURE)
def teacher_msg_jsonResponse=get_jsonResponse(teacher_msg_response)

if(WS.containsString(teacher_msg_response, 'user_id', false, FailureHandling.OPTIONAL)){
	def name=teacher_msg_jsonResponse.data.name
	def user_id=teacher_msg_jsonResponse.data.user_id
	WS.comment('班主任名称:'+name+',班主任user_id:'+user_id)
	
	def message=CustomKeywords.'time.SystemTime.get_system_time'()+'学生:'+GlobalVariable.user_name+'发送给教师:'+name+'的文本信息'
	ResponseObject send_response=WS.sendRequestAndVerify(findTestObject(path1, [('teacher_id'):user_id,('message'):message]), FailureHandling.CONTINUE_ON_FAILURE)
	def send_jsonResponse=get_jsonResponse(send_response)
	def next_poll_counter=send_jsonResponse.message_counter
	
	'列表获取新数据'
	ResponseObject new_msg_response=WS.sendRequestAndVerify(findTestObject(path2, [('next_poll_counter'):next_poll_counter,('teacher_id'):user_id]), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyElementPropertyValue(new_msg_response, 'data[0].message', message, FailureHandling.CONTINUE_ON_FAILURE)
}else{

	WS.comment('该班级还没有班主任,学生:'+GlobalVariable.user_name)
}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}