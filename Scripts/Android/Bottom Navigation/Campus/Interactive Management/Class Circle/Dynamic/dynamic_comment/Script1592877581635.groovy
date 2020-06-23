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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

'前提条件:进入班级圈模块' //返回管理和教授的班级数据
def jsonResponse=Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/Class Circle/to_class_crcle"), null, FailureHandling.CONTINUE_ON_FAILURE)

if(jsonResponse.data.size>0){
	Mobile.comment('教师有管理和教授的班级,可进入班级圈')
	'获取默认tab的班级名称'
	class_name=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_tab_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	klass_id=''			//默认的班级的class_id
	for(int x:(0..jsonResponse.data.size)){
		'匹配默认班级的名字'
		def is_match=Mobile.verifyMatch(jsonResponse.data[x].klass_full_name, class_name, false, FailureHandling.OPTIONAL)
		if(is_match){
			'匹配到,则取出默认班级的class_id'
			klass_id=jsonResponse.data[x].klass_id
			break
		}
	}
	'接口发送获取班级动态列表数据'
	ResponseObject search_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
	
	if(search_dynamic_list_jsonResponse.data.size==0||search_dynamic_list_jsonResponse.data[0].article!=null){
		'没有动态则调用接口发布一条动态'
		WS.callTestCase(findTestCase('Test Cases/Api/Mobile Api/Campus/Class Circle/Class Dynamic/publish_dynamic_text'), null, FailureHandling.CONTINUE_ON_FAILURE)
		'接口发送重新获取班级动态列表数据'
		search_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
		search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
	}
	'点击更多'
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_more_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	def content=search_dynamic_list_jsonResponse.data[0].content		//第一条动态内容
	def created=search_dynamic_list_jsonResponse.data[0].created		//第一条动态的接口时间字段
	def time=DataFormat(created)
	'获取评论元素的xpath'
	def xpath=get_comment_btn_xpath(time, content)
	'找到评论按钮'
	WebElement comment_btn=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
	'点击评论按钮'
	comment_btn.click()
	'生成输入的内容'
	String comment_content=CustomKeywords.'time.SystemTime.get_system_time'()+'Android app发送动态评论'
	'输入动态评论内容'
	Mobile.setText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_commet_edittext'), comment_content, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击发送'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_comment_send_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'验证评论出现在评论区'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_comment_content_text', [('text'):GlobalVariable.user_name+': '+comment_content]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
}

//返回对应的评论按钮xpath
def String get_comment_btn_xpath(String time,String content){
	String xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]/..//*[@resource-id="com.goldlokedu.tgoldlokedu:id/comment_texts"]'
	return xpath
}

//时间转换，今天则返回HH:mm，否则返回MM-dd HH:mm
def String DataFormat(String time){
	long timestamp=CustomKeywords.'time.SystemTime.getTimeStamp'(time, 'GMT+0800')
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date now = sdf.parse(sdf.format(new Date()))
	long startTime = now.getTime()
	long endTime=startTime+24 * 60 * 60 * 1000-1

	if(timestamp>=startTime&&timestamp<=endTime){ //今天
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(timestamp);
	}else{//非今天
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		return format.format(timestamp);
	}

}


//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}
