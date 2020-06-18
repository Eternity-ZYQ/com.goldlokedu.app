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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
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
import groovy.json.internal.LazyMap
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver
'前提条件:进入班级圈模块' //返回管理和教授的班级数据
def jsonResponse=Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/Class Circle/to_class_crcle"), null, FailureHandling.CONTINUE_ON_FAILURE)

if(jsonResponse.data.size>0){
	Mobile.comment('教师有管理和教授的班级,可进入班级圈')
	
	'获取教师作为班主任管理的班级'
	ResponseObject manager_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/manager_class"), FailureHandling.CONTINUE_ON_FAILURE)
	def manager_jsonResponse=get_jsonResponse(manager_response)
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
	'验证班级圈页面默认班级的公告的UI'
	verifu_page_ui(klass_id,is_manager,class_name)
	//WS.comment('切换前的班级id:'+klass_id+',class_name:'+class_name)
	'点击切换班级按钮'
	Mobile.tap(findTestObject("Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'切换班级页面标题验证'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_title_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

	'进行切换班级'
	page_switch(manager_response,manager_jsonResponse,jsonResponse,is_manager)
	
	//WS.comment('切换后的班级id:'+klass_id+',class_name:'+class_name)
	'验证切换班级后的班级的公告的UI'
	verifu_page_ui(klass_id,!is_manager,class_name)
	
}

//切换班级,默认是授课班级的切换为管理班级,若是管理班级则切换为授课班级
def void page_switch(ResponseObject manager_response,LazyMap manager_jsonResponse,LazyMap jsonResponse,boolean is_manager){
	class_name=''			//需要切换到的班级名称
	klass_id=''				//需要切换到的班级的class_id
	if(manager_jsonResponse.data.size==jsonResponse.data.size){
		WS.comment('班级圈全部是班主任管理的班级,本次用例结束')
	}else if(jsonResponse.data.size>manager_jsonResponse.data.size){
		WS.comment('班级圈存在教师授课的班级')
		if(is_manager){
			WS.comment('已进行班主任管理班级操作,即将进行授课班级操作')
			for(int y:(0..jsonResponse.data.size)){
				class_name=jsonResponse.data[y].klass_full_name
				'判断是不是授课班级'
				if(!WS.containsString(manager_response, class_name, false, FailureHandling.OPTIONAL)){
					'是授课班级,拿到class_id'
					klass_id=jsonResponse.data[y].klass_id   //授课班级的class_id
					break
				}
				
			}
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
			
		}else{
			WS.comment('已进行授课班级操作,即将进行班主任操作')
			if(manager_jsonResponse.data.size>0){
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

			}else{
				Mobile.comment('没有管理班级,本次用例结束')
			}
			
		}
	}
		
}

//验证班级圈页面班训的UI
def void verifu_page_ui(String klass_id,boolean is_manager,String class_name){
	'发送获取最新班级公告数据接口'
	ResponseObject notice_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/new_one_notice", [('class_id'):klass_id]), FailureHandling.CONTINUE_ON_FAILURE)
	def notice_jsonResponse=get_jsonResponse(notice_response)

	if(WS.verifyResponseStatusCode(notice_response, 200, FailureHandling.OPTIONAL)&&notice_jsonResponse.content!=""){
		'若是有最新公告内容,则保存下来'
		def notice_content=notice_jsonResponse.content							//公告内容																	//公告内容文本
		def created_date=notice_jsonResponse.created_date						//公告发布时间													//公告发布时间
		long timestamp=CustomKeywords.'time.SystemTime.getTimeStamp'(created_date,'GMT+0800')		//转换成时间戳
		Date date = new Date(timestamp)
		SimpleDateFormat df = new SimpleDateFormat('MM/dd')
		def time = df.format(date)
		'有公告的时候,验证公告内容'
		Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_content_text', [('text'):time+' '+notice_content]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
		'点击更多按钮'
		Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_more_or_publish', [('text'):'更多']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
		'验证进入更多页面后,班级公告列表页面的名称:四年级三班公告'
		Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_class_name_title_text', [('text'):class_name+'公告']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
		'发送获取班级公告列表接口'
		ResponseObject notice_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/search_notice_list", [('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
		def notice_list_jsonResponse=get_jsonResponse(notice_list_response)
		for(int x:(0..notice_list_jsonResponse.data.size-1)){
			def content=notice_list_jsonResponse.data[x].content			//公告内容
			created_date=notice_list_jsonResponse.data[x].created_date	//发布时间

			'时间转换'
			time=DataFormat(created_date)
			'公告内容的xpath'
			def xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]'
			'找到对应公告内容'
			CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)													//文本xpath
			'非班主任没有带删除按钮的文本xpath'
			def detele_xpath='//android.widget.TextView[@text="'+time+'"]/..//android.widget.TextView[@text="删除"]'
			//def detele_xpath='fdsfadasfsa'
			if(is_manager){
				'找到发布公告按钮'
				Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_in_publish_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
				'找到对应的删除按钮'
				CustomKeywords.'public_action.findMobileElement.byXpath'(detele_xpath)		
			}else{
				'找不到发公告按钮'
				Mobile.verifyElementNotExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_in_publish_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
				'找不到删除按钮'
				CustomKeywords.'public_action.findMobileElement.notFindByXpath'(detele_xpath)

			}	
		}
		
		'点击返回按钮,返回班级圈页面'
		Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/back_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
	}else if(WS.verifyResponseStatusCode(notice_response, 404, FailureHandling.OPTIONAL)&&notice_jsonResponse.code==404){
		'没有公告的时候,验证提示'
		Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/no_notice_promp_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		if(is_manager){   
			'是教师管理的班级,则有发布按钮'
			Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_more_or_publish", [('text'):'发布']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		}
		'更多按钮不存在'
		Mobile.verifyElementNotExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_more_or_publish", [('text'):'更多']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
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



