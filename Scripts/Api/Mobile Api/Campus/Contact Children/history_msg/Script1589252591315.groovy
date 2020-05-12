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

'历史消息列表接口路径'
def path='Object Repository/Api/Mobile Api/Campus/Contact Children/history_msg'
'加载文件接口路径'
def path1='Object Repository/Api/Mobile Api/Campus/Contact Children/dowmload_file'
'获取历史消息列表数据'
ResponseObject msg_response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def msg_jsonResponse=get_jsonResponse(msg_response)

if(msg_jsonResponse.data.size>0){
	WS.comment('列表有数据')
	for(int x:(0..msg_jsonResponse.data.size-1)){
		def message_type=msg_jsonResponse.data[x].message_type
		if(message_type=='Json'){
		def file_id=msg_jsonResponse.data[x].message.file_id
			WS.sendRequestAndVerify(findTestObject(path1, [('file_id'):file_id]), FailureHandling.CONTINUE_ON_FAILURE)
		}	
	}	
}else{

	WS.comment('列表没有数据')
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}