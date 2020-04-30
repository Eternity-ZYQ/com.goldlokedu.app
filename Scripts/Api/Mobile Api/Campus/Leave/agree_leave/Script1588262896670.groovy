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

'获取请假待审批列表'
ResponseObject leave_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Leave/approval_pending_list", [('from'):from,('size'):size,('audit_states'):audit_states,('order_by'):order_by]), FailureHandling.CONTINUE_ON_FAILURE)
def leave_jsonResponse =get_jsonResponse(leave_response)

if(leave_jsonResponse.data.size>0){
	
	WS.comment('待审批列表有数据')
	
	def leave_id=leave_jsonResponse.data[0].id
	'第一条待审批操作：同意'
	WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Leave/agree_leave', [('leave_id'):leave_id]), FailureHandling.CONTINUE_ON_FAILURE)
	

}else{
	WS.comment('待审批列表为空')

}









//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}