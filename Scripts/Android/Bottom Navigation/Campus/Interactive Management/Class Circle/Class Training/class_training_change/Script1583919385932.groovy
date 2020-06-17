import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.junit.After
import org.openqa.selenium.WebElement

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
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

	'验证班级圈页面默认班级的班训的UI'
	verifu_page_ui(klass_id)
	
	'默认页面操作'
	operate(is_manager,class_name)
	
	'点击切换班级按钮'
	Mobile.tap(findTestObject("Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'切换班级页面标题验证'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_title_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'进行切换班级'
	page_switch(manager_response,manager_jsonResponse,jsonResponse,is_manager)
	
	'切换后的页面操作'
	operate(!is_manager,class_name)
	
}

//切换班级,默认是授课班级的切换为管理班级,若是管理班级则切换为授课班级
def void page_switch(ResponseObject manager_response,LazyMap manager_jsonResponse,LazyMap jsonResponse,boolean is_manager){
	//def class_name=''			//需要切换到的班级名称
	def klass_id=''				//需要切换到的班级的class_id
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
			'授课班级页面UI验证'
			verifu_page_ui(klass_id)
			
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
				'授课班级页面UI验证'
				verifu_page_ui(klass_id)

			}else{
				Mobile.comment('没有管理班级,本次用例结束')
			}
			
		}
	}
		
}

//修改操作步骤
def void operate(boolean is_manager,String class_name){
	'是班主任,则进行修改,不是则验证修改按钮不存在'
	if(is_manager){
		Mobile.comment('默认班级:'+class_name+'是教师作为班主任管理的班级')
		"点击班训模块修改按钮"
		Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_change_layout"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
		"查看顶部标题title是否为班训"
		Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_title_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
		"拼接班训内容:时间+测试班训"
		String class_training_content=CustomKeywords.'time.SystemTime.get_system_time'()+"改"
		
		"输入班训内容"
		Mobile.setText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_edittext"), class_training_content, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
		"点击保存"
		Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_save_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
		//"返回班级圈页面,验证是否发布成功"
		"获取班级圈页面班训的内容"
		Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_content_text', [('text'):class_training_content]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

		
	}else{
		Mobile.comment('默认班级:'+class_name+'是教师作为任课教师管理的班级')
		'修改按钮不存在'
		Mobile.verifyElementNotExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_change_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	}
}

//验证班级圈页面班训的UI
def void verifu_page_ui(String klass_id){
	'接口获取默认班级的班训内容'
	ResponseObject class_training_response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Class Circle/Class Training/class_training_content', [('class_id'):klass_id]), FailureHandling.CONTINUE_ON_FAILURE)
	def class_training_jsonResponse=get_jsonResponse(class_training_response)
	
	if(class_training_jsonResponse.data!=''&&class_training_jsonResponse.data.slogan!=''){
		'若是有班训内容,则保存下来'
		def class_training_content=class_training_jsonResponse.data.slogan		//班训内容文本
		'有班训的时候,验证班训内容'
		Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_content_text', [('text'):class_training_content]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	}else{
		'没有班训的时候,验证提示'
		Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/no_class_training_prompt_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	}
	
}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}


