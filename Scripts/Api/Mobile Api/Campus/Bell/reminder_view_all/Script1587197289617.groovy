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






'一键已读'
reminder_view_all(GlobalVariable.user_id)














//一键已读
def void reminder_view_all(String owner_id){
	'发送一键已读接口'
	ResponseObject reminder_view_all_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Bell/bell_reminder_view_all",[('owner_id'):owner_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(reminder_view_all_response)
	WS.comment('小铃铛一键已读接口返回body:'+reminder_view_all_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(reminder_view_all_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(reminder_view_all_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
	}
	
	
	
	
}


//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	 
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}




