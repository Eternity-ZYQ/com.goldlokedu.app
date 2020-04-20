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
ResponseObject class_information_response=WS.callTestCase(findTestCase("null"), null, FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)

for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		WS.comment('class_id:'+class_id)
		class_training(class_id)	
	}
	
	
}








//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}




//获取班级班训
def void class_training(String class_id){
	'发送获取教师是否为班主任接口数据'
	ResponseObject class_training_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Training/class_training_content",[('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def class_training_jsonResponse=get_jsonResponse(class_training_response)
	WS.comment('班训接口返回body:'+class_training_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(class_training_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(class_training_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyElementPropertyValue(class_training_response, 'message', '操作成功', FailureHandling.CONTINUE_ON_FAILURE)
		
		
		
	}
	
	
	
}







