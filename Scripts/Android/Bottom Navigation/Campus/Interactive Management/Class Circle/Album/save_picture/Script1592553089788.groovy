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
		WS.comment('有管理的班级，即将进行保存相片操作')
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
		   '不是班主任，切换到管理班级的模块'
		   page_switch(manager_jsonResponse)
	   }
	   '进行保存/下载图片操作'
	   save_picture(class_name,klass_id)
	}else{
		WS.comment('没有管理的班级，不进行创建相册的用例')
	}
		
}


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


//保存/下载图片
def void save_picture(String class_name,String klass_id){
	'发送获取相册数据接口'
	ResponseObject album_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/album_list",[('class_id'):klass_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def album_list_jsonResponse=get_jsonResponse(album_list_response)
	if(album_list_jsonResponse.albums.size==0){
		//没有相册,调用接口创建相册
		def album_name=CustomKeywords.'time.SystemTime.get_system_time'()+'Android调用接口添加相册'
		'发送添加相册接口'
		WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/add_album",[('album_name'):album_name,('class_id'):klass_id]), FailureHandling.CONTINUE_ON_FAILURE)
		'点击右上传创建按钮'
		Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_more_or_create', [('text'):'创建']),GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	}

	'进入相册上传图片操作'			//若果没有相册,则接口添加一个相册,点击更多,进入里面操作,若有相册,则外面点击相册进行操作
	save_picture_action_operate(class_name,album_list_jsonResponse,album_list_jsonResponse.albums.size==0,klass_id)
}



//保存/下载图片步骤操作
def void save_picture_action_operate(String class_name,LazyMap album_list_jsonResponse,boolean is_inside,String klass_id){
	'获取第一个相册的名称'
	def name=album_list_jsonResponse.albums[0].name
	'获取该相册的图片数量'
	def album_size=album_list_jsonResponse.albums[0].size
	'获取album_id'
	def album_id=album_list_jsonResponse.albums[0].album_id
	def xpath=''
	
	if(is_inside){
		'内部相册封面的xpath'
		xpath='//android.widget.TextView[@text="'+name+'"]/../android.widget.TextView[@text="'+album_size+'张"]/..'
	}else{
		'外部相册封面xpath'
		xpath='//android.widget.TextView[@text="'+name+'"]/..'
	}
	'找到该相册元素'
	WebElement album_elment=CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
	'点击相册,进入'
	album_elment.click()
	'判断相册内是否有图片,没有则通过接口上传一张,有则继续操作'
	if(album_size==0){
		'上传照片'
		ResponseObject upload_album_picture_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/upload_album_picture",[('class_id'):klass_id]), FailureHandling.CONTINUE_ON_FAILURE)
		def upload_album_picture_jsonResponse=get_jsonResponse(upload_album_picture_response)	
		'关联到班级相册'
		WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/album_picture_retaled_class",[('class_id'):klass_id,('picture_id'):upload_album_picture_jsonResponse.picture_id,('name'):upload_album_picture_jsonResponse.name,('album_id'):album_id]), FailureHandling.CONTINUE_ON_FAILURE)
	}
	'右上角菜单xpath'
	def menu_xpath='//android.widget.RelativeLayout[@resource-id="com.goldlokedu.tgoldlokedu:id/dot_more"]/android.widget.ImageView'
	'找到右上角菜单按钮'
	WebElement menu_elment=CustomKeywords.'public_action.findMobileElement.byXpath'(menu_xpath)
	'点击右上角菜单按钮'
	menu_elment.click()
	'点击批量管理按钮'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/menu_batch_manage'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'选中第一张照片'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/manage_page_selete_picture_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击保存'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/magnage_page_save_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'验证toast:亲,图片下载完毕'
	CustomKeywords.'public_action.verifyToast.VerifyToastElementExistByText'('亲,图片下载完毕')

}
//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}







