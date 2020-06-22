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
import groovy.json.internal.LazyMap
import internal.GlobalVariable as GlobalVariable

'前提条件:进入班级圈模块' //返回管理和教授的班级数据
def jsonResponse=Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/Class Circle/to_class_crcle"), null, FailureHandling.CONTINUE_ON_FAILURE)

if(jsonResponse.data.size>0){
	Mobile.comment('教师有管理和教授的班级,可进入班级圈')
	'获取默认tab的班级名称'
	class_name=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_tab_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'接口发送获取我的动态列表数据'
	ResponseObject search_personal_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_personal_dynamic_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def search_personal_dynamic_list_jsonResponse=get_jsonResponse(search_personal_dynamic_list_response)
	
	if(search_personal_dynamic_list_jsonResponse.data.size==0||search_personal_dynamic_list_jsonResponse.data[0].article!=null){
		'没有动态则调用接口发布一条动态'
		WS.callTestCase(findTestCase('Test Cases/Api/Mobile Api/Campus/Class Circle/Class Dynamic/publish_dynamic_text'), null, FailureHandling.CONTINUE_ON_FAILURE)
		'接口发送重新获取我的动态列表数据'
		search_personal_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_personal_dynamic_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
		search_personal_dynamic_list_jsonResponse=get_jsonResponse(search_personal_dynamic_list_response)
	}
	'点击更多'
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_more_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	def content=search_personal_dynamic_list_jsonResponse.data[0].content		//第一条动态内容
	def created=search_personal_dynamic_list_jsonResponse.data[0].created		//第一条动态的接口时间字段
	def time=DataFormat(created)
	def like=search_personal_dynamic_list_jsonResponse.data[0].like				//本人否点赞过第一条动态
	'验证点赞前页面'
	verify_operate(search_personal_dynamic_list_jsonResponse)
	
	'获取点赞按钮xpath'
	def like_btn_xpath=get_like_btn_xpath(time, content)
	'找到点赞按钮'
	WebElement like_btn=CustomKeywords.'public_action.findMobileElement.byXpath'(like_btn_xpath)
	'点击点赞按钮'
	like_btn.click()
	'接口发送重新获取我的动态列表数据'
	search_personal_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_personal_dynamic_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	search_personal_dynamic_list_jsonResponse=get_jsonResponse(search_personal_dynamic_list_response)
	def new_like=search_personal_dynamic_list_jsonResponse.data[0].like				//点及点赞按钮后的状态
	'验证点赞前后两次状态相反'
	Mobile.verifyEqual(like, !new_like, FailureHandling.CONTINUE_ON_FAILURE)
	
}

def void verify_operate(LazyMap search_personal_dynamic_list_jsonResponse){
	def content=search_personal_dynamic_list_jsonResponse.data[0].content		//第一条动态内容
	def created=search_personal_dynamic_list_jsonResponse.data[0].created		//第一条动态的接口时间字段
	def time=DataFormat(created)												//时间转换
	def like_address=''															//点赞人的名字显示
	'生成点赞人列表文本'
	if(search_personal_dynamic_list_jsonResponse.data[0].favorites.size>0){
		'获取like_address'
		like_address=get_like_address(search_personal_dynamic_list_jsonResponse)
		'获取xpath'
		def like_address_xpath=get_like_address_xpath(time,content,like_address)
		'验证第一条动态点赞用户名字存在'
		CustomKeywords.'public_action.findMobileElement.byXpath'(like_address_xpath)
	}else{
		'获取like_address'
		like_address=''
		'获取xpath'
		def like_address_xpath=get_like_address_xpath(time,content,like_address)
		'验证没有点赞用户文本列表控件'
		CustomKeywords.'public_action.findMobileElement.notFindByXpath'(like_address_xpath)
	}
	
}


def String get_like_address(LazyMap search_personal_dynamic_list_jsonResponse){
	def like_address=''
	for(int x:(0..search_personal_dynamic_list_jsonResponse.data[0].favorites.size-1)){
		def creator_name=search_personal_dynamic_list_jsonResponse.data[0].favorites[x].creator_name
		if(x==0){
			like_address=like_address+creator_name
		}else{
			like_address=like_address+','+creator_name
		}
	}
	return like_address
}

//点赞按钮xpath获取,动态获取
def String get_like_btn_xpath(String time,String content){
	'点赞按钮的xpath'
	def like_btn_xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]/..//*[@resource-id="com.goldlokedu.tgoldlokedu:id/laud"]'
	
	return like_btn_xpath
}

//已点赞的用户名字xpath获取
def String get_like_address_xpath(String time,String content,String like_address){
	def like_address_xpath=''
	if(like_address!=''){
		'已点赞的用户名字xpath'
		like_address_xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]/..//android.widget.TextView[@resource-id="com.goldlokedu.tgoldlokedu:id/loves" and @text="'+like_address+'"]'
	}else{
		'已点赞的用户名字xpath'
		like_address_xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]/..//android.widget.TextView[@resource-id="com.goldlokedu.tgoldlokedu:id/loves"]'
	
	}

	return like_address_xpath
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