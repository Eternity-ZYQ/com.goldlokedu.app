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
import static org.assertj.core.api.Assertions.*




'定时短信列表'
get_sms_timed_list()

'短信列表有数据,则进行删除第一条'
if(timer_sms_msg_size>0){
	
	delete_sms_timed_list_item(draft_id)
	
}else if(timer_sms_msg_size==0){

	WS.comment('定时短信列表数据为空')

}else{
	WS.verifyEqual('1', '2', FailureHandling.CONTINUE_ON_FAILURE)
	WS.comment('定时短信接口异常')
}












//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())

	return jsonResponse
}



//获取定时短信列表
def void get_sms_timed_list(){
	'发送获取定时短信数'
	ResponseObject sms_timed_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/SMS/sms_timed_list"), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(sms_timed_list_response)
	WS.comment('定时短信列表数据body:'+sms_timed_list_response.getResponseText())
	
	'请求接口成功'
	if(WS.verifyResponseStatusCode(sms_timed_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		'返回数据中有timer_sms_msg'
		WS.containsString(sms_timed_list_response, 'timer_sms_msg', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		'保存定时短信列表数据长度:timer_sms_msg_size'
		timer_sms_msg_size=jsonResponse.timer_sms_msg.size
	
	'判断是否有数据'
	if(timer_sms_msg_size>0){
			
		'保存定时短信第一条条目id:draft_id'
		draft_id=jsonResponse.timer_sms_msg[0].draft_id
	
	}
	
	}
	
}





//删除定时短信数据
def void delete_sms_timed_list_item(String id){
	'删除指定定时短信数据'
	ResponseObject delete_response=WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/delete_sms_timed_item', [('draft_id'):id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(delete_response)
	WS.comment('删除定时短信数据返回body:'+delete_response.getResponseText())
	
	'接口请求成功'
	if(WS.verifyResponseStatusCode(delete_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		'是否删除成功'
		WS.verifyElementPropertyValue(delete_response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
		
	}
	
	
	
}










