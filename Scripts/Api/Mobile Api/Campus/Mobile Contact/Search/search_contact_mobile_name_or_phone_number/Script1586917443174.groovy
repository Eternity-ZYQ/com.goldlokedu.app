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



def teacher_name='测试庄'
def student_name='测试学生庄'
def mobile='18344264447'

'搜索教师名字'
for_get_data(search_name_or_number(teacher_name))

'搜索学生名字'
for_get_data(search_name_or_number(student_name))

'搜索手机号'
for_get_data(search_name_or_number(mobile))









//数据获取循环
def void for_get_data(Object jsonResponse){
	
	if(jsonResponse.data.size>0){
		
		for(int x:(0..jsonResponse.data.size-1)){
			
				user_head_image(jsonResponse.data[x].user_id,jsonResponse.data[x].name)
							
					
		}
		
	}
	
	
	
}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}





//打电话搜索联系人
def Object search_name_or_number(String name){
	'发送打电话搜索联系人列表接口'
	ResponseObject search_name_or_number_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact/Search/search_contact_mobile_name_or_phone_number",[('name'):name]), FailureHandling.CONTINUE_ON_FAILURE)
	def search_name_or_number_jsonResponse=get_jsonResponse(search_name_or_number_response)
	WS.comment('打电话教师联系人列表数据body:'+search_name_or_number_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(search_name_or_number_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(search_name_or_number_response, 'code', 200,FailureHandling.CONTINUE_ON_FAILURE)
		
		return search_name_or_number_jsonResponse
		
	}
	
		return
		
}


//获取用户头像
def void user_head_image(String user_id,String name){
	'发送用户头像接口'
	ResponseObject user_head_image_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/My/Individual/User/user_head_image",[('user_id'):user_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	
	if(WS.verifyResponseStatusCode(user_head_image_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		WS.comment('加载成功')
					
	}else{
		WS.comment(name+'加载失败')
	
	}
	
	
	
	
}
