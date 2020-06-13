import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.SimpleDateFormat

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

'前置条件：进入到手机短信页面'
Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/SMS/to_sms_page"), null, FailureHandling.CONTINUE_ON_FAILURE)

'点击定时短信tab'
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/timed_sms_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'接口登录'
if(GlobalVariable.access_token==''){
	'没有登录过，则进行接口登录'
	WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/Login And Logout/teacher_login_success"), null, FailureHandling.CONTINUE_ON_FAILURE)
}

'接口获取定时短信列表'
ResponseObject timed_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/SMS/sms_timed_list"), FailureHandling.CONTINUE_ON_FAILURE)
def timed_list_jsonResponse=get_jsonResponse(timed_list_response)

if(timed_list_jsonResponse.timer_sms_msg.size==0){
	Mobile.comment('定时短信列表为空')
	
	'验证暂无数据页面'
	CustomKeywords.'public_action.findMobileElement.byXpath'('//android.widget.TextView[@text="暂无内容"]/../android.widget.TextView[@text="去看看其他内容"]')
	'关闭app，结束当前用例'
	Mobile.closeApplication(FailureHandling.CONTINUE_ON_FAILURE)
	'调用发送接口发送定时短信'
	WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/Campus/SMS/timed_sms_sending"), null, FailureHandling.CONTINUE_ON_FAILURE)
	'重新执行app删除定时短信用例'
	Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/SMS/Timed SMS/detele_timed_sms"), null, FailureHandling.CONTINUE_ON_FAILURE)
}else if(timed_list_jsonResponse.timer_sms_msg.size>0){
	def scheduled_date=timed_list_jsonResponse.timer_sms_msg[0].scheduled_date				//定时发送时间
	def content=timed_list_jsonResponse.timer_sms_msg[0].content.content							//定时短信内容
	
	'时间转换'
	def time=DataFormat(scheduled_date)
	def xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]/..//*[@class="android.widget.TextView" and @text="删除"]'
	'找到删除按钮'
	WebElement detele_btn=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
	'点击删除按钮'
	detele_btn.click()
	'验证删除提示内容'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Timed SMS/detele_tips_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击确定'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Timed SMS/sure_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
}else{
	KeywordUtil.markError("接口返回数据有问题："+timed_list_response.getResponseText())
}



//时间转换，今天则返回HH:mm，否则返回yyyy-MM-dd HH:mm
def String DataFormat(String time){
	long timestamp=CustomKeywords.'time.SystemTime.getTimeStamp'(time, 'GMT+0800')
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date now = sdf.parse(sdf.format(new Date()))
	long nowTime = now.getTime()
	long day = (nowTime - timestamp) / (24 * 60 * 60 * 1000)
	WS.comment('day:'+day)
	if (day < 1&&day>0) {  //今天
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(timestamp);
	}  else {    //非今天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(timestamp);
	}

}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}
