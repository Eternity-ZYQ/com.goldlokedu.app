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


'获取打电话班级列表数据,发送打电话班级联系人列表接口'
ResponseObject class_mobile_contact_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact/Student/student_mobile_class_contact"), FailureHandling.CONTINUE_ON_FAILURE)
def class_mobile_contact_jsonResponse=get_jsonResponse(class_mobile_contact_response)

if(class_mobile_contact_jsonResponse.data.size>0){
	
	for(int x:(0..class_mobile_contact_jsonResponse.data.size-1)){
	
		'发送打电话班级内学生联系人列表接口'
		ResponseObject class_student_mobile_contact_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact/Student/student_mobile_contact",[('class_id'):class_mobile_contact_jsonResponse.data[x].id]), FailureHandling.CONTINUE_ON_FAILURE)
		def class_student_mobile_contact_jsonResponse=get_jsonResponse(class_student_mobile_contact_response)
		
		if(class_student_mobile_contact_jsonResponse.data.size>0){
			for(int y:(0..class_student_mobile_contact_jsonResponse.data.size-1)){
				
				'发送用户头像接口'
				WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/User/user_head_image",[('user_id'):class_student_mobile_contact_jsonResponse.data[y].user_id]), FailureHandling.CONTINUE_ON_FAILURE)
				
					
			}
		}else{
			WS.comment('该班级没有学生')
		
		}
		
	}
	
}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}









