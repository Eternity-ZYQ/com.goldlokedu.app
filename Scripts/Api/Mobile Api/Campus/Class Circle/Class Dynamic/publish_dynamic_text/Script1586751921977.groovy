import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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


'获取教师关联班级信息'
ResponseObject class_information_response=WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/My/Individual/Teacher/teacher_related_class"), null, FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)

for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		WS.comment('class_id:'+class_id)
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		
		WS.comment(class_name+"发布动态中...")
		def dynamic_content=CustomKeywords.'time.SystemTime.get_system_time'()+'发布纯文本动态'
		publish_dynamic_text(class_id,dynamic_content)
		
	}
		
}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}





//发布纯文本动态
def void publish_dynamic_text(String class_id,String content){
	'发送发布动态接口'
	ResponseObject publish_dynamic_text_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/dynamic_publish_text",[('class_id'):class_id,('content'):content]), FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.comment('发布动态返回数据body:'+publish_dynamic_text_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(publish_dynamic_text_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(publish_dynamic_text_response, 'moment_id', false, FailureHandling.CONTINUE_ON_FAILURE)
			
	}
	
	
}



