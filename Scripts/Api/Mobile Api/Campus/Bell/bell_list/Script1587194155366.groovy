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





ResponseObject bell_response=bell_list()
return bell_response











//获取小铃铛列表
def Object bell_list(){
	'发送获取小铃铛列表接口'
	ResponseObject bell_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Bell/bell_reminder_search"), FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.comment('小铃铛列表数据body:'+bell_response.getResponseText())
	
	'判断接口请求是否成功'
	if(WS.verifyResponseStatusCode(bell_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		'接口返回数据包含total'
		WS.containsString(bell_response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return bell_response
	}
	
	return 
}
