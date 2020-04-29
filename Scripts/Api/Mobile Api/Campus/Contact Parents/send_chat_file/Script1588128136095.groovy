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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

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

'获取教师作为班主任管理的班级'
ResponseObject response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/manager_class"), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse = get_jsonResponse(response)

'是否有数据'
if(jsonResponse.data.size>0){
	
	WS.comment('是管理'+jsonResponse.data.size+'个班的班主任')
	
	'获取班级下的学生列表'
	for(int x:(0..jsonResponse.data.size-1)){
		WS.comment('获取第'+jsonResponse.data[x].klass_full_name+'学生列表中...')
		ResponseObject student_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact/Student/class_student_list", [('class_id'):jsonResponse.data[x].klass_id]), FailureHandling.CONTINUE_ON_FAILURE)
		def student_list_jsonResponse=get_jsonResponse(student_list_response)
		
		'班级是否有学生'
		if(student_list_jsonResponse.data.size>0){
			'上传文件'
			ResponseObject upload_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact Parents/upload_file"), FailureHandling.CONTINUE_ON_FAILURE)
			def message =upload_response.getResponseText()
			def upload_jsonResponse =get_jsonResponse(upload_response)
			
			'发送文件给每个班的第一个学生'
			ResponseObject send_chat_response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Contact Parents/send_chat_file', [('message'):message,('student_id'):student_list_jsonResponse.data[0].userId]), FailureHandling.CONTINUE_ON_FAILURE)
			def send_chat_jsonResponse =get_jsonResponse(send_chat_response)
			def next_poll_counter=send_chat_jsonResponse.message_counter
						
			'间隔0.1秒'
			WS.delay(0.1)
					
			'列表获取新数据'
			ResponseObject new_msg_response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Contact Parents/poll_new_chat_record', [('next_poll_counter'):next_poll_counter,('student_id'):student_list_jsonResponse.data[0].userId]), FailureHandling.CONTINUE_ON_FAILURE)
			WS.verifyElementPropertyValue(new_msg_response, 'data[0].message.file_id', upload_jsonResponse.file_id, FailureHandling.CONTINUE_ON_FAILURE)
				
		}else{
		
			WS.comment(jsonResponse.data[x].klass_full_name+'没有学生')
		}
		
	}
		
}else{

	WS.comment('不是任何一个班的班主任')

}









//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}