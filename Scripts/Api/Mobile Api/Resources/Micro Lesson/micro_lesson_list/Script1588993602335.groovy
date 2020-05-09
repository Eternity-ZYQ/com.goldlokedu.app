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

'先检查年级和学科是否设置'
WS.callTestCase(findTestCase('Test Cases/Api/Mobile Api/Resources/Courseware/course_and_grade_and_setting'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'微课列表接口路径'
def path='Object Repository/Api/Mobile Api/Resources/Micro Lesson/micro_lesson_list'
'微课详情接口路径'
def path1='Object Repository/Api/Mobile Api/Resources/Micro Lesson/micro_lesson_detail'
'微课已读接口路径'
def path2='Object Repository/Api/Mobile Api/Resources/Micro Lesson/views_micro_lesson'
'微课封面图片接口路径'
def path3='Object Repository/Api/Mobile Api/Resources/Micro Lesson/dowmload_cover_picture'
'获取微课默认列表'
ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse =get_jsonResponse(response)
if(jsonResponse.result.size>0){
	
	WS.comment('微课列表有数据')
	'第一条数据的id'
	def micro_lecture_id=jsonResponse.result[0].micro_lecture_id
	'查看课件详情'
	WS.sendRequestAndVerify(findTestObject(path1, [('micro_lecture_id'):micro_lecture_id]), FailureHandling.CONTINUE_ON_FAILURE)
	'课件已读'
	WS.sendRequestAndVerify(findTestObject(path2, [('micro_lecture_id'):micro_lecture_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	for(int x:(0..jsonResponse.result.size-1)){
		'封面图id'
		def thumbmail_id=jsonResponse.result[x].thumbmail_id
		'加载封面图片'
		WS.sendRequestAndVerify(findTestObject(path3, [('thumbmail_id'):thumbmail_id]), FailureHandling.CONTINUE_ON_FAILURE)
		
	}
}else{
	WS.comment('微课列表没有数据')
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}