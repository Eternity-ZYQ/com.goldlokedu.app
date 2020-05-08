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

'课件列表接口路径'
def path='Object Repository/Api/Mobile Api/Resources/Courseware/courseware_list'
'课件详情接口路径'
def path1='Object Repository/Api/Mobile Api/Resources/Courseware/courseware_detail'
'课件已读接口路径'
def path2='Object Repository/Api/Mobile Api/Resources/Courseware/views_course_wares'

'先检查年级和学科是否设置'
WS.callTestCase(findTestCase('Test Cases/Api/Mobile Api/Resources/Courseware/course_and_grade_and_setting'), [:], FailureHandling.CONTINUE_ON_FAILURE)
'获取课件默认列表'
ResponseObject response=WS.sendRequestAndVerify(findTestObject(path), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse=get_jsonResponse(response)

if(jsonResponse.result.size>0){
	
	WS.comment('课件列表有数据')
	'第一条数据的id'
	def course_ware_id=jsonResponse.result[0].course_ware_id
	'查看课件详情'
	WS.sendRequestAndVerify(findTestObject(path1, [('course_ware_id'):course_ware_id]), FailureHandling.CONTINUE_ON_FAILURE)
	'课件已读'
	WS.sendRequestAndVerify(findTestObject(path2, [('course_ware_id'):course_ware_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
}else{
	WS.comment('课件列表没有数据')
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}