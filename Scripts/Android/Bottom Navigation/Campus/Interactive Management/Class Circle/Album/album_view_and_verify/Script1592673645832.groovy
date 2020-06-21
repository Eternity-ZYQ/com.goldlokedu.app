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

	'验证默认页面'
	verify_and_operate(is_manager,klass_id,class_name)
	
	'点击切换班级按钮'
	Mobile.tap(findTestObject("Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'切换班级页面标题验证'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_title_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'进行切换班级'
	page_switch(manager_response,manager_jsonResponse,jsonResponse,is_manager)
	
	'验证切换后的页面'
	verify_and_operate(!is_manager,klass_id,class_name)
	
}

//切换班级,默认是授课班级的切换为管理班级,若是管理班级则切换为授课班级
def void page_switch(ResponseObject manager_response,LazyMap manager_jsonResponse,LazyMap jsonResponse,boolean is_manager){
	//def class_name=''			//需要切换到的班级名称
	//def klass_id=''				//需要切换到的班级的class_id
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
	
	String tip=''						//列表为空是的提示语
	def name=''						//相册名称
	def album_id=''					//相册id
	def album_size=''				//相册内的图片数量
	def xpath=''
	'获取班级相册列表信息'
	ResponseObject album_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/album_list",[('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def album_list_jsonResponse=get_jsonResponse(album_list_response)
	if(album_list_jsonResponse.albums.size>0){
		//如果有相册，则保存第一个相册信息
		name=album_list_jsonResponse.albums[0].name					//相册名称
		album_id=album_list_jsonResponse.albums[0].album_id		//相册id
		album_size=album_list_jsonResponse.albums[0].size 			//相册内的图片数量
		xpath='//android.widget.TextView[@text="'+name+'"]/../android.widget.TextView[@text="'+album_size+'张"]/..'
	}
	
	if(is_manager){
		WS.comment('是'+class_name+'的班主任')
		if(album_list_jsonResponse.albums.size==0){
			WS.comment('长度：'+album_list_jsonResponse.albums.size)
			tip='暂无相册～'
			'验证相册列表为空是提示语'
			Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/no_album_prompt_text", [('text'):tip]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'点击创建按钮'
			Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_more_or_create", [('text'):'创建']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证班级相册title名称'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_title_text', [('text'):class_name+'相册']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证有新建相册按钮'
			'验证添加模块整体布局layout存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_layout'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证添加模块+图标存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_image'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证添加模块，新建相册文字存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

		}else if(album_list_jsonResponse.albums.size>0){
			'点击更多按钮'
			Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_more_or_create", [('text'):'更多']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证班级相册title名称'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_title_text', [('text'):class_name+'相册']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证有新建相册按钮'
			'验证添加模块整体布局layout存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_layout'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证添加模块+图标存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_image'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证添加模块，新建相册文字存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'点击第一个相册'
			WebElement album_element=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
			album_element.click()
			'验证相册名称标题'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_name_title', [('text'):name]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证添加上传图片存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/upload_picture_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证右上角菜单按钮存在'
			'右上角菜单xpath'
			def menu_xpath='//android.widget.RelativeLayout[@resource-id="com.goldlokedu.tgoldlokedu:id/dot_more"]/android.widget.ImageView'
			'找到右上角菜单按钮'
			WebElement menu_elment=CustomKeywords.'public_action.findMobileElement.byXpath'(menu_xpath)
			'点击右上角菜单按钮'
			menu_elment.click()
			'验证修改名称菜单存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/menu_edit_name'), album_size, FailureHandling.CONTINUE_ON_FAILURE)
			'验证删除相册菜单存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/menu_detele_album'), album_size, FailureHandling.CONTINUE_ON_FAILURE)
			'验证批量管理菜单存在'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/menu_batch_manage'), album_size, FailureHandling.CONTINUE_ON_FAILURE)
		}
		
		
	}else{
		WS.comment('不是'+class_name+'的班主任')
		if(album_list_jsonResponse.albums.size==0){
			tip='班主任还没有创建相册呢～'	
			'验证相册列表为空是提示语'
			Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/no_album_prompt_text", [('text'):tip]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证创建按钮不存在'
			Mobile.verifyElementNotExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_more_or_create", [('text'):'创建']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证更多按钮不存在'
			Mobile.verifyElementNotExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_more_or_create", [('text'):'更多']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
		}else if(album_list_jsonResponse.albums.size>0){
			'点击更多按钮'
			Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_more_or_create", [('text'):'更多']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证班级相册title名称'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_title_text', [('text'):class_name+'相册']), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证有新建相册按钮'
			'验证添加模块整体布局layout不存在'
			Mobile.verifyElementNotExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_layout'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证添加模块+图标不存在'
			Mobile.verifyElementNotExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_image'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证添加模块，新建相册文字不存在'
			Mobile.verifyElementNotExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'点击第一个相册'
			WebElement album_element=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
			album_element.click()
			'验证相册名称标题'
			Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_name_title', [('text'):name]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'验证添加上传图片不存在'
			Mobile.verifyElementNotExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/upload_picture_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
			'右上角菜单xpath'
			def menu_xpath='//android.widget.RelativeLayout[@resource-id="com.goldlokedu.tgoldlokedu:id/dot_more"]/android.widget.ImageView'
			'验证右上角菜单按钮不存在'
			CustomKeywords.'public_action.findMobileElement.notFindByXpath'(menu_xpath)
			

		}

	}
	
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}


