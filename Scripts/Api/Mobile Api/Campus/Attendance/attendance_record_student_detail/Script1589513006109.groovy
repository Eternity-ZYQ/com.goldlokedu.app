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

'学生考勤详情接口路径'
def path='Object Repository/Api/Mobile Api/Campus/Attendance/attendance_record_student_detail'
'加载考勤图片接口路径'
def path1='Object Repository/Api/Mobile Api/Campus/Attendance/dowmload_picture'

def check_date=CustomKeywords.'time.SystemTime.get_day_time'()
def student_id=GlobalVariable.user_id
'发送获取学生考勤数据'
ResponseObject response=WS.sendRequestAndVerify(findTestObject(path, [('check_date'):check_date,('student_id'):student_id]), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse=get_jsonResponse(response)

if(jsonResponse.data.detail.detail.size>0){
	
	WS.comment('有考勤规则')
	for(int x:(0..jsonResponse.data.detail.detail.size-1)){
		def pic =jsonResponse.data.detail.detail[x].pic
		if(pic!=''){
			
			WS.comment('有图片,即将加载...')
			'获取考勤图片'
			WS.sendRequestAndVerify(findTestObject(path1, [('pic'):pic]), FailureHandling.CONTINUE_ON_FAILURE)
		}else{
		
			WS.comment('没有图片')
		}
		
	}
}else{

	WS.comment('没有考勤规则')
}
//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}