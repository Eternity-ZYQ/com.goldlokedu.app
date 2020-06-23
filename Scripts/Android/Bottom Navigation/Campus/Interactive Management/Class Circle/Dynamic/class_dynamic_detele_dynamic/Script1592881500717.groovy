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
	
	'获取教师作为班主任管理的班级'
	ResponseObject manager_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/manager_class"), FailureHandling.CONTINUE_ON_FAILURE)
	def manager_jsonResponse=get_jsonResponse(manager_response)
	
	if(manager_jsonResponse.data.size>0){		//是否有管理的班级
		WS.comment('有管理的班级，即将进行删除动态操作')
		'获取默认tab的班级名称'
		class_name=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_tab_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	   '判断默认的班级是否为班主任管理的班级,还是作为任课教师关联的班级'
	   def is_manager=WS.containsString(manager_response, class_name, false, FailureHandling.OPTIONAL)
	   if(is_manager){
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
	   }else{
		   //'不是班主任，切换到管理班级的模块'
			   page_switch(manager_jsonResponse)
	   }
	   '进行删除动态操作'
	   detele_dynamic(class_name,klass_id)
	}else{
		WS.comment('没有管理的班级，不进行创建相册的用例')
	}
		
}

//删除动态操作
def void detele_dynamic(String class_name,String klass_id){
	'接口发送获取班级动态列表数据'
	ResponseObject search_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
	
	if(search_dynamic_list_jsonResponse.data.size==0||search_dynamic_list_jsonResponse.data[0].article!=null){
		'没有动态则调用接口发布一条动态'
		WS.callTestCase(findTestCase('Test Cases/Api/Mobile Api/Campus/Class Circle/Class Dynamic/publish_dynamic_text'), null, FailureHandling.CONTINUE_ON_FAILURE)
		'接口发送重新获取我的动态列表数据'
		search_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	 	search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
	}
	'点击更多'
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_more_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	def content=search_dynamic_list_jsonResponse.data[0].content		//第一条动态内容
	def created=search_dynamic_list_jsonResponse.data[0].created		//第一条动态的接口时间字段
	def time=DataFormat(created)
	'获取删除按钮xpath'
	def xpath=get_detele_btn_xpath(time, content)
	'找到删除按钮'
	WebElement detele_btn=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
	'点击删除按钮'
	detele_btn.click()
	'验证删除二次确认框提示'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/my_dynamic_page_detele_tip_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击确定'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/my_dynamic_page_detele_tip_sure_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'验证toast:已删除'
	CustomKeywords.'public_action.verifyToast.VerifyToastElementExistByText'('已删除')
	
}


//返回对应删除按钮xpath
def String get_detele_btn_xpath(String time,String content){
	String xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]/..//android.widget.TextView[@text="删除"]'
	return xpath
}

//切换到管理班级的页面
def void page_switch(LazyMap manager_jsonResponse){
	//'不是班主任，切换到管理班级的模块'
	'点击切换班级按钮'
	Mobile.tap(findTestObject("Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'切换班级页面标题验证'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_title_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

	class_name=manager_jsonResponse.data[0].klass_full_name
	klass_id=manager_jsonResponse.data[0].klass_id
	'获取年级名称'
	def grade_name=class_name[0..2]
	'年级元素的xpath'
	def xpath='//android.widget.TextView[@text="'+grade_name+'"]'
	'获取年级元素'
	WebElement grade_btn=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
	'点击该年级'
	grade_btn.click()
	'班级元素的xpath'
	xpath='//android.widget.TextView[@text="'+class_name+'"]'
	'获取年级元素'
	WebElement class_btn=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
	'点击该年级'
	class_btn.click()
	'验证切换确认提示'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_tips', [('text'):'切换到'+class_name]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击切换'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
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
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}