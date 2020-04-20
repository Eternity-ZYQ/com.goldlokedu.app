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





'获取个人动态列表数据'
ResponseObject search_personal_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_personal_dynamic_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def search_personal_dynamic_list_jsonResponse=get_jsonResponse(search_personal_dynamic_list_response)

if(search_personal_dynamic_list_jsonResponse.data.size>0){
	WS.comment('我的动态有数据')
	
	
	
	'发送删除班级动态接口'
	WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/delete_personal_dynamic",[('dynamic_moment_id'):search_personal_dynamic_list_jsonResponse.data[0].moment_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	
	
}







//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}










