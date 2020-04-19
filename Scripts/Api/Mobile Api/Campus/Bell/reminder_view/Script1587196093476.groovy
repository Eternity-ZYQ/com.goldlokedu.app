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



ResponseObject bell_list_response=WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/Campus/Bell/bell_list"), null, FailureHandling.CONTINUE_ON_FAILURE)
def bell_list_jsonResponse=get_jsonResponse(bell_list_response)

'保存列表数据长度'
data_size=bell_list_jsonResponse.data.size
	

if(data_size>0){
	
	for(int index:(0..data_size-1)){
		
		if(!bell_list_jsonResponse.data[index].is_read){
			'未读的才保存reminder_id'
			reminder_id=bell_list_jsonResponse.data[index].reminder_id
			
			return
		}
	}
}


if(reminder_id!=''){
	
reminder_view(reminder_id)

}else{

WS.comment('没有未读的信息')
}











//查看小铃铛,使其变已读状态
def void reminder_view(String reminder_ids){
	'发送查看小铃铛信息接口'
	ResponseObject reminder_view_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Bell/bell_reminder_view",[('reminder_ids'):reminder_ids]), FailureHandling.CONTINUE_ON_FAILURE)
	
	//def rsonResponse=get_jsonResponse(reminder_view_response)
	WS.comment('小铃铛查看接口返回body:'+reminder_view_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(reminder_view_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(reminder_view_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
				
	}
	
	
	
}


//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}
