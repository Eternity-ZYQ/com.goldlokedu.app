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

'获取我发的通知列表'
ResponseObject response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Notification/my_send_notification_list", [('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def get_jsonResponse=get_jsonResponse(response)


if(get_jsonResponse.data.size>0){
	for(int x:(0..get_jsonResponse.data.size-1)){
		
		'是否是自己发的'
		if(WS.verifyEqual(get_jsonResponse.data[x].sender_id,GlobalVariable.user_id, FailureHandling.CONTINUE_ON_FAILURE)){
			
			WS.comment('是本教师发的通知,删除中...')
			WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Notification/delete_notification", [('notification_id'):get_jsonResponse.data[x].notification_id]), FailureHandling.CONTINUE_ON_FAILURE)
			return
		
		}else{
			WS.comment('不是本教师发的通知,不能删除')
		}
	
	}
}






//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	def jsonSlurper = new JsonSlurper()
	
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())

	return jsonResponse
}

