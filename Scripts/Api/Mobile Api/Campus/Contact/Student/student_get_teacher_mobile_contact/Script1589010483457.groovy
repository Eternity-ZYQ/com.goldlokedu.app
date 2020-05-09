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

'学生获取打电话的教师列表的接口路径'
def path='Object Repository/Api/Mobile Api/Campus/Contact/Student/student_get_teacher_mobile_contact'
'用户头像接口路径'
def path1='Object Repository/Api/Mobile Api/My/Individual/User/user_head_image'
'发送接口获取教师通讯录类表'
ResponseObject teacher_list_response=WS.sendRequestAndVerify(findTestObject(path), FailureHandling.CONTINUE_ON_FAILURE)
def list_jsonResponse=get_jsonResponse(teacher_list_response)

if(list_jsonResponse.data.size>0){
	
	WS.comment('通讯录列表有数据')
	
	for(int x:(0..list_jsonResponse.data.size-1)){
		def teacher=list_jsonResponse.data[x].teacher
		def user_id=list_jsonResponse.data[x].user_id
		def mobile=list_jsonResponse.data[x].mobile
		WS.comment('教师:'+teacher)
		'手机号不能为空'
		WS.verifyNotEqual(mobile, '', FailureHandling.CONTINUE_ON_FAILURE)
		'获取教师头像'
		WS.sendRequestAndVerify(findTestObject(path1, [('user_id'):user_id]), FailureHandling.CONTINUE_ON_FAILURE)
		
	}
}else{
	WS.comment('通讯录列表没有数据')	

}
//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}
