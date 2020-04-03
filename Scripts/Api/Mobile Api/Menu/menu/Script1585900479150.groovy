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

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject

"获取ios菜单"
ios_app_menu()

'获取Android菜单'
android_app_menu()










//ios菜单获取
def void ios_app_menu(){
	
	ResponseObject response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Menu/ios_app_menu"), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse =get_jsonResponse(response)
	if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(response, 'code', 200)
		WS.verifyElementPropertyValue(response, 'message', '操作成功')
		WS.comment(''+jsonResponse)
	}
	
	
}


//ios菜单获取
def void android_app_menu(){
	
	ResponseObject response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Menu/android_app_menu"), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse =get_jsonResponse(response)
	if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(response, 'code', 200)
		WS.verifyElementPropertyValue(response, 'message', '操作成功')
		WS.comment(''+jsonResponse)
	}
	
	
}




//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}




