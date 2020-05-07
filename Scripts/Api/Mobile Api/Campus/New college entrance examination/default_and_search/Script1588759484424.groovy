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

'接口对象路径'
def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/select_require_list'
'获取新高考默认列表'
WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)

def search_school='北京大学'
def search_school_code='10001'

'搜索学校名称'
ResponseObject name_response=WS.sendRequestAndVerify(findTestObject(path, [('from'):from,('size'):size,('school_code_or_name'):search_school]), FailureHandling.CONTINUE_ON_FAILURE)
def name_jsonResponse=get_jsonResponse(name_response)
if(name_jsonResponse.data.size>0){
	WS.comment('搜索有结果')
	for(int x:(0..name_jsonResponse.data.size-1)){
		
		def school_name=name_jsonResponse.data[x].school_name
		WS.comment('真实值:'+school_name+',期望值:'+search_school)
		WS.verifyMatch(school_name, '.*'+search_school+'.*', true, FailureHandling.CONTINUE_ON_FAILURE)
		
	}	
}else{
	WS.comment('搜索不到'+search_school)

}




'搜索院校代码'
ResponseObject code_response=WS.sendRequestAndVerify(findTestObject(path, [('from'):from,('size'):size,('school_code_or_name'):search_school_code]), FailureHandling.CONTINUE_ON_FAILURE)
def code_jsonResponse=get_jsonResponse(code_response)
if(code_jsonResponse.data.size>0){
	WS.comment('搜索有结果')
	for(int x:(0..code_jsonResponse.data.size-1)){
		
		def school_code=name_jsonResponse.data[x].school_code
		WS.comment('真实值:'+school_code+',期望值:'+search_school_code)
		WS.verifyMatch(school_code+'',search_school_code+'.*', true, FailureHandling.CONTINUE_ON_FAILURE)
		
	}
}else{
	WS.comment('搜索不到'+search_school_code)

}




//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}

