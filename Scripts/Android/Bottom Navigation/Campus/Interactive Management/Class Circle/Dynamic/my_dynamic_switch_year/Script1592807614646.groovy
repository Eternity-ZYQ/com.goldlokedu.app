import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.SimpleDateFormat

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

'前提条件:进入班级圈模块' //返回管理和教授的班级数据
def jsonResponse=Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/Class Circle/to_class_crcle"), null, FailureHandling.CONTINUE_ON_FAILURE)

if(jsonResponse.data.size>0){
	Mobile.comment('教师有管理和教授的班级,可进入班级圈')
	'获取默认tab的班级名称'
	class_name=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_tab_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击更多'
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_more_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
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
	'点击我的动态'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/my_dynamic_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	def detele_class_name=search_personal_dynamic_list_jsonResponse.data[0].klass_name
	def detele_created=search_personal_dynamic_list_jsonResponse.data[0].created
	def detele_content=search_personal_dynamic_list_jsonResponse.data[0].content
	def time=DataFormat(detele_created)
	'我的动态页面删除按钮对应的xpath'
	def xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+detele_content+'"]/..//android.widget.TextView[@text="'+detele_class_name+'"]/../..//android.widget.TextView[@text="删除"]'
	'找到删除按钮'
	CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
	'获取学年接口数据'
	ResponseObject get_years_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/year"), FailureHandling.CONTINUE_ON_FAILURE)
	'获取学年'
	def get_yearsjsonResponse=get_jsonResponse(get_years_response)
	def year_code=''				//学年code,用于接口请求
	def year_name=''				//学年名称,用于app定位
	if(get_yearsjsonResponse.data.size>0){
		year_code=get_yearsjsonResponse.data[0].code
		year_name=get_yearsjsonResponse.data[0].name
	}else{
		KeywordUtil.markFailed('学年接口数据异常')
	}
	
	'点击学年按钮'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/year_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击确定'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/year_sure_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'获取动态个人列表数据--筛选学年'
	ResponseObject search_personal_dynamic_list_filter_year_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_personal_dynamic_list_filter_year",[('from'):from,('size'):size,('school_year'):year_code]), FailureHandling.CONTINUE_ON_FAILURE)
	def search_personal_dynamic_list_filter_yearjsonResponse=get_jsonResponse(search_personal_dynamic_list_filter_year_response)
	if(search_personal_dynamic_list_filter_yearjsonResponse.data.size>0){
		detele_class_name=search_personal_dynamic_list_filter_yearjsonResponse.data[0].klass_name
		detele_created=search_personal_dynamic_list_filter_yearjsonResponse.data[0].created
		detele_content=search_personal_dynamic_list_filter_yearjsonResponse.data[0].content
		time=DataFormat(detele_created)
		'我的动态页面删除按钮对应的xpath'
		xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+detele_content+'"]/..//android.widget.TextView[@text="'+detele_class_name+'"]/../..//android.widget.TextView[@text="删除"]'
		'找到删除按钮'
		CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
	
	}else{
	
	WS.comment(year_name+'没有数据')
	}

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