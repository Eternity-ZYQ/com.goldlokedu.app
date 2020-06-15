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
	
	'获取教师作为班主任管理的班级'
	ResponseObject manager_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/manager_class"), FailureHandling.CONTINUE_ON_FAILURE)
	def manager_jsonResponse=get_jsonResponse(manager_response)
	'获取默认tab的班级名称'
	def class_name=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_tab_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'判断默认的班级是否为班主任管理的班级,还是作为任课教师关联的班级'
	def is_manager=WS.containsString(manager_response, class_name, false, FailureHandling.OPTIONAL)
	
	def klass_id=''			//默认的班级的class_id
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
	verifu_page_ui(klass_id)
	
}



//验证班级圈页面班训的UI
def void verifu_page_ui(String klass_id){
	'发送获取最新班级公告列表接口'
	ResponseObject notice_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/new_one_notice", [('class_id'):klass_id]), FailureHandling.CONTINUE_ON_FAILURE)
	def notice_jsonResponse=get_jsonResponse(notice_response)

	if(WS.verifyResponseStatusCode(notice_response, 200, FailureHandling.OPTIONAL)&&notice_jsonResponse.content!=""){
		'若是有最新公告内容,则保存下来'
		def notice_content=notice_jsonResponse.content																			//公告内容文本
		def created_date=notice_jsonResponse.created_date																	//公告发布时间
		long timestamp=CustomKeywords.'time.SystemTime.getTimeStamp'(created_date,'GMT+0800')		//转换成时间戳
		Date date = new Date(timestamp)
		SimpleDateFormat df = new SimpleDateFormat('MM/dd')
		def time = df.format(date)
		'有公告的时候,验证公告内容'
		Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_content_text', [('text'):time+' '+notice_content]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	}else if(WS.verifyResponseStatusCode(notice_response, 404, FailureHandling.OPTIONAL)&&notice_jsonResponse.code==404){
		'没有班训的时候,验证提示'
		Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/no_notice_promp_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	}
	
}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}



