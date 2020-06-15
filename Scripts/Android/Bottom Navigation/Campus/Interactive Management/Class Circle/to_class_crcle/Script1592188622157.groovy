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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

'登录后处于首页'
Mobile.callTestCase(findTestCase("Test Cases/Android/Login Before Module/No Class But Study/cancel"), null, FailureHandling.CONTINUE_ON_FAILURE)

'接口登录教师'
if(GlobalVariable.access_token==''){
	'没有登录过，则进行接口登录'
	WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/Login And Logout/teacher_login_success"), null, FailureHandling.CONTINUE_ON_FAILURE)
}
'发送获取教师管理及教授的班级'
ResponseObject response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/teacher_manager_and_teach_class"), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse=get_jsonResponse(response)

'点击班级圈模块按钮'
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/class_circle_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

if(jsonResponse.data.size==0){
	Mobile.comment('没有教授和管理的班级,不能进入班级圈模块,给出相应提示:此功能仅限任教老师使用哦')
	CustomKeywords.'public_action.verifyToast.VerifyToastElementExistByText'('此功能仅限任教老师使用哦')
}

return jsonResponse		



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}