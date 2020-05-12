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

'学生请假列表接口路径'
def path='Object Repository/Api/Mobile Api/Campus/Leave/student_leave_list'
'加载请假图片接口路径'
def path1='Object Repository/Api/Mobile Api/Campus/Leave/dowmload_picture'

'获取学生请假列表'
ResponseObject list_response=WS.sendRequestAndVerify(findTestObject(path, [('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def list_jsonResponse=get_jsonResponse(list_response)

if(list_jsonResponse.data.size>0){
	WS.comment('请假列表有数据')
	
	for(int x:(0..list_jsonResponse.data.size-1)){
		
		if(list_jsonResponse.data[x].attachments.data.size>0){
			WS.comment('第'+(x+1)+'条请假有图片')
			
			for(int y:(0..list_jsonResponse.data[x].attachments.data.size-1)){
				WS.comment('加载第'+(y+1)+'张图片...')
				def file_id=list_jsonResponse.data[x].attachments.data[y].file_id
				'加载图片'
				WS.sendRequestAndVerify(findTestObject(path1, [('file_id'):file_id]), FailureHandling.CONTINUE_ON_FAILURE)
			}
			
		}else{
			
			WS.comment('第'+(x+1)+'条请假没有图片')
		
		}	
	}
}else{

	WS.comment('请假列表没有数据')
}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}