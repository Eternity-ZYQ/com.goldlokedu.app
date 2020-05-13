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

'学生发起文本及图片请假路径'
def path='Object Repository/Api/Mobile Api/Campus/Leave/student_leave_text_and_picture'
'上传请假图片的接口路径'
def path1='Object Repository/Api/Mobile Api/Campus/Leave/upload_picture'

'上传图片'
ResponseObject picture_response=WS.sendRequestAndVerify(findTestObject(path1), FailureHandling.CONTINUE_ON_FAILURE)
def data=picture_response.getResponseText()
def start_date=CustomKeywords.'time.SystemTime.get_start_time'(0)
def end_date=CustomKeywords.'time.SystemTime.get_future_day_time'(1)
def start_day_part=day_part[1]
def end_day_part=day_part[0]
def leave_type=leave_type[0]
def reason=CustomKeywords.'time.SystemTime.get_system_time'()+'发起文本带图片请假'

WS.sendRequestAndVerify(findTestObject(path, [('date'):start_date,('day_part'):start_day_part,('end_date'):end_date,('end_day_part'):end_day_part,('leave_type'):leave_type,('reason'):reason,('data'):data]), FailureHandling.CONTINUE_ON_FAILURE)

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}