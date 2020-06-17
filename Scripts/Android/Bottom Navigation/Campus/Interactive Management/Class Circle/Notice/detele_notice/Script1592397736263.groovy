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
		WS.comment('有管理的班级，即将进行删除公告操作')
		'获取默认tab的班级名称'
		class_name=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_tab_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	   '判断默认的班级是否为班主任管理的班级,还是作为任课教师关联的班级'
	   def is_manager=WS.containsString(manager_response, class_name, false, FailureHandling.OPTIONAL)
	   
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
	   
	   
	   if(!is_manager){
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
	   
	}else{
		WS.comment('没有管理的班级，不进行删除公告用例')
	}
	
	'进行删除操作'
	detele_notice(klass_id,,class_name)

}

//删除公告操作
def void detele_notice(String klass_id,String class_name){
	'发送获取最新班级公告数据接口'
	ResponseObject notice_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/new_one_notice", [('class_id'):klass_id]), FailureHandling.CONTINUE_ON_FAILURE)
	def notice_jsonResponse=get_jsonResponse(notice_response)

	if(WS.verifyResponseStatusCode(notice_response, 200, FailureHandling.OPTIONAL)&&notice_jsonResponse.content!=""){
	
		'点击更多按钮'
		Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_more_or_publish', [('text'):'更多']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			
		'发送获取班级公告列表接口'
		ResponseObject notice_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/search_notice_list", [('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
		def notice_list_jsonResponse=get_jsonResponse(notice_list_response)
		def content=notice_list_jsonResponse.data[0].content			//公告内容
		def created_date=notice_list_jsonResponse.data[0].created_date	//发布时间
		'时间转换'
		def time=DataFormat(created_date)
		'班主任带删除按钮的文本xpath'
		def detele_xpath='//android.widget.TextView[@text="'+content+'"]/..//android.widget.TextView[@text="'+time+'"]/..//android.widget.TextView[@text="删除"]'
		'找到删除按钮'
		 WebElement detele_btn=CustomKeywords.'public_action.findMobileElement.byXpath'(detele_xpath)
		'点击删除'
		detele_btn.click()
		'点击确定'
		Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/sure_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		'验证删除成功，提示为：亲，删除成功'
		CustomKeywords.'public_action.verifyToast.VerifyToastElementExistByText'('亲,删除成功')
	
	}else{
		WS.comment(class_name+'没有公告数据，即将通过接口增加一条数据')
		def content=CustomKeywords.'time.SystemTime.get_system_time'()+'发布的测试公告----'+class_name
		'发送发布公告接口'
		WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/add_notice",[('class_id'):klass_id,('content'):content]), FailureHandling.CONTINUE_ON_FAILURE)
		'调用自己'
		detele_notice(klass_id,class_name)
		
	}
}



//时间转换，今天则返回HH:mm，否则返回MM/dd HH:mm
def String DataFormat(String time){
	long timestamp=CustomKeywords.'time.SystemTime.getTimeStamp'(time, 'GMT+0800')
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date now = sdf.parse(sdf.format(new Date()))
	long nowTime = now.getTime()
	long day = (nowTime - timestamp) / (24 * 60 * 60 * 1000)
	WS.comment('day:'+day)
	if (day < 1&&day>=0) {  //今天
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(timestamp);
	}  else {    //非今天
		SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm");
		return format.format(timestamp);
	}

}


//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}
