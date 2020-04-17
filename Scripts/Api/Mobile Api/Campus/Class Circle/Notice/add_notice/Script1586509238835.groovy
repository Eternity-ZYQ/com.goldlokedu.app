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
		def time=CustomKeywords.'time.SystemTime.get_system_time'()
		def content=time+'发布的测试公告----'+class_name
		
		'获取教师是否为班主任信息'
		ResponseObject judge_adviser_response=WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/My/Individual/Teacher/judge_adviser"), [('class_id'):class_id], FailureHandling.CONTINUE_ON_FAILURE)
		def judge_adviser_jsonResponse=get_jsonResponse(judge_adviser_response)
		
		if(judge_adviser_jsonResponse.data.adviser){
			
			WS.comment('是'+class_name+'的班主任')
			
			add_notice(class_id,content)
			
		}else{
		
			WS.comment('不是'+class_name+'的班主任')
				
		}
	}
	
	
}






//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}





//发布公告
def void add_notice(String class_id,String content){
	'发送发布公告接口'
	ResponseObject add_notice_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/add_notice",[('class_id'):class_id,('content'):content]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def class_information_jsonResponse=get_jsonResponse(add_notice_response)
	WS.comment('发布公告返回信息body'+add_notice_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(add_notice_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
				
		WS.verifyElementPropertyValue(add_notice_response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
	}
	
}




