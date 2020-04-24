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




'发送打电话搜索联系人列表接口:搜索名字'
ResponseObject search_name_or_number_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact/Search/search_contact_mobile_name_or_phone_number",[('name'):name]), FailureHandling.CONTINUE_ON_FAILURE)
def search_name_or_number_jsonResponse=get_jsonResponse(search_name_or_number_response)

'家长搜索到的头像'
for_get_data(search_name_or_number_jsonResponse)






//数据获取循环
def void for_get_data(Object jsonResponse){
	
	if(jsonResponse.data.size>0){
		
		for(int x:(0..jsonResponse.data.size-1)){
						
				'发送用户头像接口'
				WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/User/user_head_image",[('user_id'):jsonResponse.data[x].user_id]), FailureHandling.CONTINUE_ON_FAILURE)
													
		}
		
	}

}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}



