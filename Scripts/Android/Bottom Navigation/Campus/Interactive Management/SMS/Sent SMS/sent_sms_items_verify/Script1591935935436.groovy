import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject


import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

import org.openqa.selenium.WebElement

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

'前置条件:进入手机短信页面'
Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/SMS/to_sms_page"), null, FailureHandling.CONTINUE_ON_FAILURE)

"点击顶部已发短信"
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/sent_sms_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'接口登录教师'
if(GlobalVariable.access_token==''){
	'没有登录过，则进行接口登录'
	WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/Login And Logout/teacher_login_success"), null, FailureHandling.CONTINUE_ON_FAILURE)	
}
'发送获取已发短信列表数据'
ResponseObject list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/SMS/sms_sent_list", [('size'):size,('from'):from]), FailureHandling.CONTINUE_ON_FAILURE)
def list_jsonResponse=get_jsonResponse(list_response)

if(list_jsonResponse.data.size>0){
	
	WS.comment('已发短信列表有数据:'+list_response.getResponseText())
	
	for(int x:(x..list_jsonResponse.data.size-1)){
		def owner_name=list_jsonResponse.data[x].owner_name   				//发送人
		def created_date=list_jsonResponse.data[x].created_date  			//创建的时间
		def fail=list_jsonResponse.data[x].sent_static.fail    				//失败的数量
		def replied=list_jsonResponse.data[x].replied_static.replied		//回复的数量
		def address_str=list_jsonResponse.data[x].address_str				//接受人
		def payload_content=list_jsonResponse.data[x].payload_content		//短信内容
		
		'接口时间格式转换'
		def time=CustomKeywords.'time.SystemTime.dataFormat'(created_date,'GMT+0800')
		time=setTimeFormat(time)		//判断是否是当前年份
//		'滚动到对应时间'
//		Mobile.scrollToText(time, FailureHandling.CONTINUE_ON_FAILURE)
		'查找对应元素'
		verifyElementExist(time,payload_content,address_str)
	}
		
}else{

	WS.comment('已短信列表没有数据:'+list_response.getResponseText())
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}

/**
 * 验证元素存在
 * 
 * return :存在则返回true ,不存在则返回false
 **/
def WebElement verifyElementExist(String time,String content,String address){
	
	String xpath='//*[@resource-id="com.goldlokedu.tgoldlokedu:id/time" and @text="'+time+'"]/../../android.widget.RelativeLayout/android.widget.TextView[@resource-id="com.goldlokedu.tgoldlokedu:id/class_text_one" and @text="'+address+'"]/../../android.widget.TextView[@text="'+content+'"]'
	
	WebElement element=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
	
	return element
	
}

/**
 * 处理时间格式:若是当前年份,返回不带年份的时间,若非当前年份则返回带年份时间
 * time:时间字符串
 * is_filter:是否过滤当前年份
 * return :处理后的时间
 **/
def String setTimeFormat(String time){
	Pattern regexPat = Pattern.compile('[0-9]{4}')
	Matcher mat = regexPat.matcher(time)
	if(mat.find()) {		
		String result= mat.group()
		println result	
		Date date = new Date()
		SimpleDateFormat df = new SimpleDateFormat('yyyy')
		String str_years = df.format(date)
		if(WS.verifyEqual(result, str_years, FailureHandling.OPTIONAL)){
			def temp=time.replace(result+'-','')
			return temp
		}else{
			return time
		}		
	}else {
		KeywordUtil.markFailed('匹配不到年份:'+time)	
	}
	
}

