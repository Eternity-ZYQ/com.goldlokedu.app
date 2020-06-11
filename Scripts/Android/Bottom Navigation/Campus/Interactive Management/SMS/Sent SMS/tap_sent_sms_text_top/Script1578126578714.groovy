import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject


import java.text.DateFormat
import java.text.ParseException
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

'前置条件:进入手机短信页面'
Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/SMS/to_sms_page"), null, FailureHandling.CONTINUE_ON_FAILURE)

"点击顶部已发短信"
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/sent_sms_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'发送获取已发短信列表数据'
ResponseObject list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/SMS/sms_sent_list", [('size'):size,('from'):from]), FailureHandling.CONTINUE_ON_FAILURE)
def list_jsonResponse=get_jsonResponse(list_response)

if(list_jsonResponse.data.size>0){
	
	WS.comment('已发短信列表有数据:'+list_response.getResponseText())
	
	for(int x:(x..list_jsonResponse.data.size-1)){
		def owner_name=list_jsonResponse.data[x].owner_name   				//发送人
		def created_date=list_jsonResponse.data[x].created_date  			//创建的时间
		def fail=list_jsonResponse.data[x].sent_static.fail    				//失败的数量
		def replied=list_jsonResponse.data[x].replied_static.replied		//回复的数量
		def address_str=list_jsonResponse.data[x].address_str				//接受人
		def payload_content=list_jsonResponse.data[x].payload_content		//短信内容
	}
		
}else{

	WS.comment('已短信列表没有数据:'+list_response.getResponseText())
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}

/**
 * 验证元素存在
 * 
 * return :存在则返回true ,不存在则返回false
 **/
def Boolean verifyElementExist(String time,String content,String address){
	
	String xpath=""
	
	
	
}


