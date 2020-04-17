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




'获取我发的通知列表'
my_send_notificatiom_list(from,size)




















//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}




//我发的通知列表
def Object my_send_notificatiom_list(int from,int size){
	ResponseObject my_send_notificatiom_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Notification/my_send_notification_list", [('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def my_send_notificatiom_list_jsonResponse=get_jsonResponse(my_send_notificatiom_list_response)
	WS.comment('我发的通知列表body:'+my_send_notificatiom_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(my_send_notificatiom_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
			WS.containsString(my_send_notificatiom_list_response, 'data', false, FailureHandling.CONTINUE_ON_FAILURE)
			WS.containsString(my_send_notificatiom_list_response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return my_send_notificatiom_list_jsonResponse
	}
	
	return
}