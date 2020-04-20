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
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		
		'获取教师是否为班主任信息'
		ResponseObject judge_adviser_response=WS.callTestCase(findTestCase("null"), [('class_id'):class_id], FailureHandling.CONTINUE_ON_FAILURE)
		def judge_adviser_jsonResponse=get_jsonResponse(judge_adviser_response)
		
		if(judge_adviser_jsonResponse.data.adviser){
			
			WS.comment('是'+class_name+'的班主任')
			def slogan=class_name+'2020加油!'
			edit_class_training(class_id,slogan)
			
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




//编辑班训
def void edit_class_training(String class_id,String slogan){
	'发送编辑班训接口'
	ResponseObject edit_class_training_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Training/edit_class_training",[('class_id'):class_id,('slogan'):slogan]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def edit_class_training_jsonResponse=get_jsonResponse(edit_class_training_response)
	WS.comment('是否为班主任返回body:'+edit_class_training_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(edit_class_training_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(edit_class_training_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyElementPropertyValue(edit_class_training_response, 'message', '操作成功', FailureHandling.CONTINUE_ON_FAILURE)
				
	}
	
	
}
