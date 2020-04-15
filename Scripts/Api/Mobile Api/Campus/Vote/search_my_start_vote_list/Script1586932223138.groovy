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


'获取我参与的投票列表'
search_my_start_vote_list(0,10)


















//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}




//查询我参与的投票列表
def Object search_my_start_vote_list(int from,int size){
	'发送我参与的投票列表接口'
	ResponseObject search_my_start_vote_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Vote/search_my_start_vote_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def search_my_start_vote_list_jsonResponse=get_jsonResponse(search_my_start_vote_list_response)
	WS.comment('我参与的投票列表数据body:'+search_my_start_vote_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(search_my_start_vote_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(search_my_start_vote_list_response, 'total', false,FailureHandling.CONTINUE_ON_FAILURE)
		
		return search_my_start_vote_list_jsonResponse
		
	}
	
		return
	
	
	
}







