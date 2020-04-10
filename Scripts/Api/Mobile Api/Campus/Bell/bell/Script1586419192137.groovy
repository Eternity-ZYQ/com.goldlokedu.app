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
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper
import static org.assertj.core.api.Assertions.*



'获取小铃铛列表数据'
get_bell_list()


if(data_size>0&&reminder_id!=''){
	'查看消息提醒'
	reminder_view(reminder_id)

}else if(data_size==0){
	
	WS.comment('消息列表为空')

}else{
	WS.verifyEqual('1', '2', FailureHandling.CONTINUE_ON_FAILURE)
	WS.comment('接口异常')
}

'一键已读'
reminder_view_all(GlobalVariable.user_id)


'全部删除'
delete_reminder_all()







//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}


//获取小铃铛列表
def void get_bell_list(){
	'发送获取小铃铛列表接口'
	ResponseObject bell_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Bell/bell_reminder_search"), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(bell_response)
	WS.comment('小铃铛列表数据body:'+bell_response.getResponseText())
	
	'判断接口请求是否成功'
	if(WS.verifyResponseStatusCode(bell_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		'接口返回数据包含total'
		assertThat(bell_response.getResponseText()).contains('total')
		
		'保存列表数据长度'
		data_size=jsonResponse.data.size
			
		
		if(data_size>0){
			
			for(int index:(0..data_size-1)){
				
				if(!jsonResponse.data[index].is_read){
					'未读的才保存reminder_id'
					reminder_id=jsonResponse.data[index].reminder_id
					
					return
				}			
			}		
		}		
	}	
	
	
}




//查看小铃铛,使其变已读状态
def void reminder_view(String reminder_ids){
	'发送查看小铃铛信息接口'
	ResponseObject reminder_view_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Bell/bell_reminder_view",[('reminder_ids'):reminder_ids]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(reminder_view_response)
	WS.comment('小铃铛查看接口返回body:'+reminder_view_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(reminder_view_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(reminder_view_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
				
	}
	
	
	
}


//一键已读
def void reminder_view_all(String owner_id){
	'发送一键已读接口'
	ResponseObject reminder_view_all_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Bell/bell_reminder_view_all",[('owner_id'):owner_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(reminder_view_all_response)
	WS.comment('小铃铛一键已读接口返回body:'+reminder_view_all_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(reminder_view_all_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(reminder_view_all_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
	}
	
	
	
	
}

//全部删除
def void delete_reminder_all(){
	'发送全部删除接口'
	ResponseObject delete_reminder_all_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Bell/bell_reminder_all_erase"), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(delete_reminder_all_response)
	WS.comment('小铃铛一键已读接口返回body:'+delete_reminder_all_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(delete_reminder_all_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(delete_reminder_all_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
	}
	
	
}









