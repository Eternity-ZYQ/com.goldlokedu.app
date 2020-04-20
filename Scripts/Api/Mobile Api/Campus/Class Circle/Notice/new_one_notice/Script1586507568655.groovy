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
import static org.assertj.core.api.Assertions.*
import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable



'获取教师关联班级信息'
ResponseObject class_information_response=WS.callTestCase(findTestCase("null"), null, FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)


for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		WS.comment('class_id:'+class_id)
		new_one_notice(class_id)
	}
	
	
}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}




//获取班级公告列表数据
def Object new_one_notice(String class_id){
	'发送获取最新班级公告列表接口'
	ResponseObject new_one_notice_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/new_one_notice", [('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def new_one_notice_jsonResponse=get_jsonResponse(new_one_notice_response)
	WS.comment('班级公告列表信息body:'+new_one_notice_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(new_one_notice_response, 200, FailureHandling.OPTIONAL)){
		
		WS.containsString(new_one_notice_response, 'bulletin_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(new_one_notice_response, 'content', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(new_one_notice_response, 'created_date', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(new_one_notice_response, 'is_deletable', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return new_one_notice_jsonResponse
		
	}else if(WS.verifyResponseStatusCode(new_one_notice_response, 404, FailureHandling.OPTIONAL)){
	
		WS.verifyElementPropertyValue(new_one_notice_response, 'code', 404, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyElementPropertyValue(new_one_notice_response, 'message', '本班级没有公告', FailureHandling.CONTINUE_ON_FAILURE)
		
	
	}else{
		
		WS.verifyResponseStatusCode(new_one_notice_response, 200, FailureHandling.CONTINUE_ON_FAILURE)
		WS.comment('接口异常')
	}
	
	
}