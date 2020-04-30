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
	for(int x:(0..leave_jsonResponse.data.size-1)){
		
		def class_name=leave_jsonResponse.data[x].class_name
		def student_name=leave_jsonResponse.data[x].student_name
		def student_id=leave_jsonResponse.data[x].student_id
		def count=x+1
		WS.comment(class_name+student_name+'头像加载中...')
		
		'获取学生头像'
		WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/My/Individual/User/user_head_image', [('user_id'):student_id]), FailureHandling.CONTINUE_ON_FAILURE)
		
		'请假是否有图片'
		if(leave_jsonResponse.data[x].attachments!=null&&leave_jsonResponse.data[x].attachments.data.size>0){
			WS.comment('待审批列表第'+count+'条请假带有图片')
			
			for(int y:(0..leave_jsonResponse.data[x].attachments.data.size-1)){
				
				def file_id=leave_jsonResponse.data[x].attachments.data[y].file_id
				def y_count=y+1
				WS.comment('加载第'+y_count+'张图片中...')
				WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Leave/dowmload_picture', [('file_id'):file_id]), FailureHandling.CONTINUE_ON_FAILURE)
				
			}
			
		}else{
			
			WS.comment('待审批列表第'+count+'没有图片')
		}
	}
}else{
	WS.comment('待审批列表为空')

}









//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}