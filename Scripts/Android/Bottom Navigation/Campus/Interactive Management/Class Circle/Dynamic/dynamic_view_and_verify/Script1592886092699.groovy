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
	'验证班级圈页面默认班级的动态的UI'
	verify_and_operate(is_manager,klass_id,class_name)
	//WS.comment('切换前的班级id:'+klass_id+',class_name:'+class_name)
	
	'点击左上角返回键,返回班级圈'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_inside_back_layout'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'点击切换班级按钮'
	Mobile.tap(findTestObject("Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'切换班级页面标题验证'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_title_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

	'进行切换班级'
	page_switch(manager_response,manager_jsonResponse,jsonResponse,is_manager)
	
	//WS.comment('切换后的班级id:'+klass_id+',class_name:'+class_name)
	'验证切换班级后的动态的公告的UI'
	verify_and_operate(!is_manager,klass_id,class_name)
	
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

//页面验证及操作
def void verify_and_operate(boolean is_manager,String klass_id,String class_name){
	def content=''						//动态内容
	def created=''						//动态创建时间
	def time=''							//转换后的时间
	def like_address=''					//点赞按钮的用户名字文本
	'获取班级动态列表信息'				
	ResponseObject search_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
	
	if(search_dynamic_list_jsonResponse.data.size>0){
		if(search_dynamic_list_jsonResponse.data[0].article!=null){
			'第一条是班级文章,调用接口发送一条动态'
			WS.callTestCase(findTestCase('Test Cases/Api/Mobile Api/Campus/Class Circle/Class Dynamic/publish_dynamic_text'), null, FailureHandling.CONTINUE_ON_FAILURE)
			'重新获取动态列表数据'
			search_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
			search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
		}
		content=search_dynamic_list_jsonResponse.data[0].content
		created=search_dynamic_list_jsonResponse.data[0].created
		time=DataFormat(created)
		def xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]'
		'找到班级圈页面最新动态内容'
		CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
		if(search_dynamic_list_jsonResponse.data[0].pictures!=null&&search_dynamic_list_jsonResponse.data[0].pictures.data.size>0){
			'动态第一条有带图,找到图片布局'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_outside_picture_layout'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		}
		
	}else{
		WS.comment(class_name+'动态列表没有数据,即将验证提示语')
		Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_no_data_tip'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	}
	
	'点击更多按钮'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_more_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'验证班级动态title'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_title',[('text'):class_name+'动态']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'获取点赞按钮xpath'
	def like_btn_xpath=get_like_btn_xpath(time, content)
	'找到点赞按钮'
	CustomKeywords.'public_action.findMobileElement.byXpath'(like_btn_xpath)
	'获取评论按钮xpath'
	def commemt_btn_xpath=get_comment_btn_xpath(time, content)
	'找到评论按钮'
	CustomKeywords.'public_action.findMobileElement.byXpath'(commemt_btn_xpath)
	'生成点赞人列表文本'
	if(search_dynamic_list_jsonResponse.data[0].favorites.size>0){
		'获取like_address'
		like_address=get_like_address(search_dynamic_list_jsonResponse)
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
	'是否有带图片'
	if(search_dynamic_list_jsonResponse.data[0].pictures!=null&&search_dynamic_list_jsonResponse.data[0].pictures.data.size>0){
		'动态第一条有带图,找到图片布局xpath'
		def picture_layout_xpath=get_inside_picture_layout_xpath(time, content)
		'找到图片布局'
		CustomKeywords.'public_action.findMobileElement.byXpath'(picture_layout_xpath)
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

//返回对应删除按钮xpath
def String get_detele_btn_xpath(String time,String content){
	String xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]/..//android.widget.TextView[@text="删除"]'
	return xpath
}

//返回对应的评论按钮xpath
def String get_comment_btn_xpath(String time,String content){
	String xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]/..//*[@resource-id="com.goldlokedu.tgoldlokedu:id/comment_texts"]'
	return xpath
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

def String get_like_address(LazyMap search_dynamic_list_jsonResponse){
	def like_address=''
	for(int x:(0..search_dynamic_list_jsonResponse.data[0].favorites.size-1)){
		def creator_name=search_dynamic_list_jsonResponse.data[0].favorites[x].creator_name
		if(x==0){
			like_address=like_address+creator_name
		}else{
			like_address=like_address+','+creator_name
		}
	}
	return like_address
}

def String get_inside_picture_layout_xpath(String time,String content){
	def xpath='//android.widget.TextView[@text="'+time+'"]/../../android.widget.TextView[@text="'+content+'"]/..//android.widget.FrameLayout[@resource-id="com.goldlokedu.tgoldlokedu:id/Pictures_View"]'
	
	return xpath
}
//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}

