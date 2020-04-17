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





'调用我发的通知'
def my_send_jsonResponse=WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/Campus/Notification/my_send_notification_list"), [('from'):from,('size'):size], FailureHandling.CONTINUE_ON_FAILURE)

for(int x:(0..my_send_jsonResponse.data.size-1)){
	'通知未读页面'
	notification_unread(my_send_jsonResponse.data[x].notification_id)
		
}
















//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}


'通知未读页面'
def Object notification_unread(String notification_id){
	ResponseObject notification_unread_response=WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/Notification/unread_notification_list', [('notification_id'):notification_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def notification_unread_jsonResponse =get_jsonResponse(notification_unread_response)
	WS.comment('通知详情页面body'+notification_unread_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(notification_unread_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(notification_unread_response, 'data', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return notification_unread_jsonResponse
	}
	return
	
}

