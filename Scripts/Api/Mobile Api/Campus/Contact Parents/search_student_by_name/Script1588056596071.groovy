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



ResponseObject response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact/Search/search_contact_student_by_name_unconnected_mobile", [('student_name'):name]), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse =get_jsonResponse(response)

if(jsonResponse.data.size>0){
	
	for(int x:(0..jsonResponse.data.size-1)){
		
		WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/User/user_head_image", [('user_id'):jsonResponse.data[x].userId]), FailureHandling.CONTINUE_ON_FAILURE)
	}
	
}else{

	WS.comment('搜索结果为空')

}












//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}