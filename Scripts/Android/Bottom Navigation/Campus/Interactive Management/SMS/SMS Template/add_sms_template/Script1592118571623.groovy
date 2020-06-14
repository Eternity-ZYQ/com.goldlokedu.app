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

'前提条件：进入短信页面'
Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/SMS/to_sms_page"), null, FailureHandling.CONTINUE_ON_FAILURE)

'点击短信模板tab'
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/sms_template_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'接口登录教师'
if(GlobalVariable.access_token==''){
	'没有登录过，则进行接口登录'
	WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/Login And Logout/teacher_login_success"), null, FailureHandling.CONTINUE_ON_FAILURE)
}

'发送短信模板列表接口获取数据'
ResponseObject sms_template_response= WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/SMS/sms_template_list"), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse =get_jsonResponse(sms_template_response)

def can_add_count=max_count-jsonResponse.data.size       //还能添加的模板数量

if(can_add_count==0){
	'验证可以点击的创建按钮不见了'
	Mobile.verifyElementNotExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/add_layout'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'有模板则保存第一个模板的template_id'
	def template_id=jsonResponse.data[0].template_id
	'发送删除短信模板接口'
	WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/delete_template', [('template_id'):template_id]), FailureHandling.CONTINUE_ON_FAILURE)
	'关闭app'
	Mobile.closeApplication(FailureHandling.CONTINUE_ON_FAILURE)
	'重新执行本用例'
	Mobile.callTestCase(findTestCase('Test Cases/Android/Bottom Navigation/Campus/Interactive Management/SMS/SMS Template/add_sms_template'), null, FailureHandling.CONTINUE_ON_FAILURE)
	
}else if(can_add_count>0){
	'没到达最大模板数量：点击添加模板按钮'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/add_layout'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'添加模板页面标题验证存在'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/add_page_title'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'获取标题输入框hide文字'
	def actual_title_hide=Mobile.getText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/title_edittext'), 0, FailureHandling.CONTINUE_ON_FAILURE)
	
	'验证hide文字是否正确'
	Mobile.verifyEqual(actual_title_hide, title_hide, FailureHandling.CONTINUE_ON_FAILURE)
	
	'获取内容输入框hide文字'
	def actual_content_hide=Mobile.getText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/content_edittext'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'验证hide文字是否正确'
	Mobile.verifyEqual(actual_content_hide, content_hide, FailureHandling.CONTINUE_ON_FAILURE)
	
	'生成模板标题'
	def title=CustomKeywords.'time.SystemTime.get_system_time_minute'(0)+'标题'
	
	'输入模板标题'
	Mobile.setText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/title_edittext'), title, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'数字统计生成'
	def Statistics=''+title.length()+'/'+title_max_count
	WS.comment('Statistics:'+Statistics)
	
	'标题数字统计验证'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/size_text',[('text'):Statistics]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'统计模块文字：标题/内容'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/focus_text', [('text'):'标题']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'生成模板内容'
	def content=CustomKeywords.'time.SystemTime.get_system_time'()+'Android添加的模板内容'
	
	'输入模板内容'
	Mobile.setText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/content_edittext'), content, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'数字统计生成'
	Statistics=''+content.length()+'/'+content_max_count
	WS.comment('Statistics:'+Statistics)
	
	'标题数字统计验证'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/size_text',[('text'):Statistics]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'统计模块文字：标题/内容'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/focus_text', [('text'):'内容']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'点击保存'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/SMS Template/save_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'验证添加成功提示'
	CustomKeywords.'public_action.verifyToast.VerifyToastElementExistByText'('添加成功')
	
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}






