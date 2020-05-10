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


'调用我收的通知列表'
ResponseObject response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Notification/my_received_notification_list", [('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def my_send_jsonResponse=get_jsonResponse(response)

if(my_send_jsonResponse.data.size>0){
	for(int x:(0..my_send_jsonResponse.data.size-1)){
		'通知详情页面'
		ResponseObject detail_response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Notification/notification_details', [('notification_id'):my_send_jsonResponse.data[x].notification_id]), FailureHandling.CONTINUE_ON_FAILURE)
		
		'附件处理'
		handle_file(detail_response)
		
		'图片处理'
		handle_picture(detail_response)
	}
}




//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}




//处理附件
def void handle_file(ResponseObject response){
	
	def detail_jsonResponse=get_jsonResponse(response)
	
	'是否有附件'
	if(detail_jsonResponse.attachments!=null&&detail_jsonResponse.attachments.data.size>0){
		WS.comment('有附件')
		
		for(int x:(0..detail_jsonResponse.attachments.data.size-1)){
			
			WS.comment('加载第'+(x+1)+'个附件')
			WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Notification/dowmload_notification_file_or_picture", [('file_id'):detail_jsonResponse.attachments.data[x].file_id]), FailureHandling.CONTINUE_ON_FAILURE)
				
		}
		
	}else{
		WS.comment('没有附件')
	
	}
}




//处理图片
def void handle_picture(ResponseObject response){
	def detail_jsonResponse=get_jsonResponse(response)
	
	'是否有图片'
	if(detail_jsonResponse.pictures!=null&&detail_jsonResponse.pictures.data.size>0){
		WS.comment('有图片')
		
		for(int x:(0..detail_jsonResponse.pictures.data.size-1)){
			
			WS.comment('加载第'+(x+1)+'个图片')
			WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Notification/dowmload_notification_file_or_picture", [('file_id'):detail_jsonResponse.pictures.data[x].file_id]), FailureHandling.CONTINUE_ON_FAILURE)
				
		}
		
	}else{
		WS.comment('没有图片')
	
	}
}








