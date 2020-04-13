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




'获取个人动态列表'
def search_personal_dynamic_list_jsonResponse=search_personal_dynamic_list(0,10)

if(search_personal_dynamic_list_jsonResponse.data.size>0){
	WS.comment('我的动态有数据')
	
	
	detele_personal_dynamic(search_personal_dynamic_list_jsonResponse.data[0].moment_id)
	
	
	
}















//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}






//发送获取动态列表
def Object search_personal_dynamic_list(int from,int size){
	'获取班级列表数据'
	ResponseObject search_personal_dynamic_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_personal_dynamic_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def search_personal_dynamic_list_jsonResponse=get_jsonResponse(search_personal_dynamic_list_response)
	WS.comment('动态列表信息body:'+search_personal_dynamic_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(search_personal_dynamic_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(search_personal_dynamic_list_response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return search_personal_dynamic_list_jsonResponse
		
	}
	
	return
	
	
	
	
}



//删除班级动态
def void detele_personal_dynamic(String dynamic_moment_id){
	'发送删除班级动态接口'
	ResponseObject detele_personal_dynamic_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/delete_personal_dynamic",[('dynamic_moment_id'):dynamic_moment_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	
	WS.comment('删除班级动态返回数据body:'+detele_personal_dynamic_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(detele_personal_dynamic_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		
		WS.verifyElementPropertyValue(detele_personal_dynamic_response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
		
		
	}
	
	
	
	
}





