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

'选课列表接口路径'
def path='Object Repository/Api/Mobile Api/Campus/Course Selection/course_selection_plan_list'
'选课详情接口路径'
def path1='Object Repository/Api/Mobile Api/Campus/Course Selection/course_select_plan_detail'

'获取选课列表'
ResponseObject list_response=WS.sendRequestAndVerify(findTestObject(path), FailureHandling.CONTINUE_ON_FAILURE)
def list_jsonResponse=get_jsonResponse(list_response)

if(list_jsonResponse.data.size>0){
	WS.comment('选课列表有数据')
	for(int x:(0..list_jsonResponse.data.size-1)){
		def title=list_jsonResponse.data[x].title
		WS.comment('即将进入标题为：'+title+'的详情页...')
		def plan_id=list_jsonResponse.data[x].plan_id
		'进入详情页'
		WS.sendRequestAndVerify(findTestObject(path1, [('plan_id'):plan_id]), FailureHandling.CONTINUE_ON_FAILURE)
		
	}
	
	
}else{

	WS.comment('选课列表没有数据')
}


//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}