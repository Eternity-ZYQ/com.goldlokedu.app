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




'获取打电话教师数据'
def teacher_mobile_contact_jsonResponse=teacher_mobile_contact()

if(teacher_mobile_contact_jsonResponse.data.size>0){
	
	for(int x:(0..teacher_mobile_contact_jsonResponse.data.size-1)){
		
		for(int y:(0..teacher_mobile_contact_jsonResponse.data[x].children.size-1)){
			
			user_head_image(teacher_mobile_contact_jsonResponse.data[x].children[y].user_id)
				
		}
		
		
	}
	
	
	
	
	
	
}













//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}






//获取联系人列表
def Object teacher_mobile_contact(){
	'发送打电话教师联系人列表接口'
	ResponseObject teacher_mobile_contact_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact/Teacher/teacher_mobile_contact"), FailureHandling.CONTINUE_ON_FAILURE)
	def teacher_mobile_contact_jsonResponse=get_jsonResponse(teacher_mobile_contact_response)
	WS.comment('打电话教师联系人列表数据body:'+teacher_mobile_contact_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(teacher_mobile_contact_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(teacher_mobile_contact_response, 'code', 200,FailureHandling.CONTINUE_ON_FAILURE)
		
		return teacher_mobile_contact_jsonResponse
		
	}
	
		return
	
	
}




//获取用户头像
def void user_head_image(String user_id){
	'发送用户头像接口'
	ResponseObject user_head_image_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/My/Individual/User/user_head_image",[('user_id'):user_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	
	WS.verifyResponseStatusCode(user_head_image_response, 200, FailureHandling.CONTINUE_ON_FAILURE)
		
	
	
}