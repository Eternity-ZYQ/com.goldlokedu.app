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

'获取请假记录默认列表'
ResponseObject list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Leave/leave_records", [('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def list_jsonResponse =get_jsonResponse(list_response)

if(list_jsonResponse.data.size>0){
	
	WS.comment('请假列表有数据')
	def leave_id=list_jsonResponse.data[0].id
	'进入第一个详情页面'
	ResponseObject detal_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Leave/leave_record_detail", ['leave_id':leave_id]), FailureHandling.CONTINUE_ON_FAILURE)
	def detail_jsonResponse=get_jsonResponse(detal_response)
	if(detail_jsonResponse.attachments!=null&&detail_jsonResponse.attachments.data.size>0){
		WS.comment('该请假有图片')
		for(int x:(0..detail_jsonResponse.attachments.data.size-1)){
			
			def file_id=detail_jsonResponse.attachments.data[x].file_id
			def x_count=x+1
			WS.comment('加载第'+x_count+'张图片中...')
			WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Leave/dowmload_picture', [('file_id'):file_id]), FailureHandling.CONTINUE_ON_FAILURE)
			
		}
		
	}else{
	
		WS.comment('该请假没有图片')
	}
}


//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}