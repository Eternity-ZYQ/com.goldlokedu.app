import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

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

'接口登录教师'
if(GlobalVariable.access_token==''){
	'没有登录过，则进行接口登录'
	WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/Login And Logout/teacher_login_success"), null, FailureHandling.CONTINUE_ON_FAILURE)
}

'发送短信模板列表接口获取数据'
ResponseObject sms_template_response= WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/SMS/sms_template_list"), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse =get_jsonResponse(sms_template_response)

if(jsonResponse.data.size<1){
	Mobile.comment('没有短信模板')
	'设置编辑的content,title文本'
	def content=CustomKeywords.'time.SystemTime.get_system_time'()+'接口添加的模板内容'
	def title=CustomKeywords.'time.SystemTime.get_system_time'()+'接口添加的标题'
	'接口创建短信模板'
	WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/add_sms_template', [('content'):content,('title'):title]), FailureHandling.CONTINUE_ON_FAILURE)
	 sms_template_response= WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/SMS/sms_template_list"), FailureHandling.CONTINUE_ON_FAILURE)
	 jsonResponse =get_jsonResponse(sms_template_response)
		
}else{
	Mobile.comment('存在'+jsonResponse.data.size+'个短信模板')
}
'前提条件2：进入短信页面'
Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/SMS/to_sms_page"), null, FailureHandling.CONTINUE_ON_FAILURE)


'点击短信模板tab'
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/sms_template_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

def template_content=jsonResponse.data[0].content						//短信模板内容
def tempate_title=jsonResponse.data[0].title								//短信模板标题
def xpath='//android.widget.TextView[@text="'+tempate_title+'"]/../android.widget.TextView[@text="'+template_content+'"]/..//android.widget.TextView[@text="删除"]'

'找到删除按钮'
WebElement detele_btn=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
'点击删除'
detele_btn.click()
'验证吐司：删除成功！'
CustomKeywords.'public_action.verifyToast.VerifyToastElementExistByText'('删除成功!')

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}
