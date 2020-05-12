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

'历史聊天列表接口路径'
def path='Object Repository/Api/Mobile Api/Campus/Contact Teacher/chat_history_latest_list'
'查看班主任接口路径'
def path1='Object Repository/Api/Mobile Api/My/Individual/Student/student_get_adviser_teacher'
'加载文件接口路径'
def path2='Object Repository/Api/Mobile Api/Campus/Contact Teacher/dowmload_file'

'获取班主任信息'
ResponseObject teacher_msg_response=WS.sendRequestAndVerify(findTestObject(path1), FailureHandling.CONTINUE_ON_FAILURE)
def teacher_msg_jsonResponse=get_jsonResponse(teacher_msg_response)

if(WS.containsString(teacher_msg_response, 'user_id', false, FailureHandling.OPTIONAL)){
	def name=teacher_msg_jsonResponse.data.name
	def user_id=teacher_msg_jsonResponse.data.user_id
	WS.comment('班主任名称:'+name+',班主任user_id:'+user_id)
	
	'获取联系教师聊天列表'
	ResponseObject chat_list_response=WS.sendRequestAndVerify(findTestObject(path, [('size'):size,('teacher_id'):user_id]), FailureHandling.CONTINUE_ON_FAILURE)
	def chat_list_jsonResponse=get_jsonResponse(chat_list_response)
	if(chat_list_jsonResponse.data.size>0){
		WS.comment('聊天列表有数据')
		
		for(int x:(0..chat_list_jsonResponse.data.size-1)){
			def message_type=chat_list_jsonResponse.data[x].message_type
			if(message_type=='Json'){
				def file_id=chat_list_jsonResponse.data[x].message.file_id
				'加载文件'
				WS.sendRequestAndVerify(findTestObject(path2, [('file_id'):file_id]), FailureHandling.CONTINUE_ON_FAILURE)		
			}
			
		}
	}else{
	
		WS.comment('聊天列表没有数据')
	}
	
}else{

	WS.comment('该班级还没有班主任,学生:'+GlobalVariable.user_name)
}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}
