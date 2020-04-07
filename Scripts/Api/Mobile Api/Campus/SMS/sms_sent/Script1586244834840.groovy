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



'已收短信'
sms_sent_list()













//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	JsonSlurper
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())

	return jsonResponse
}


//已收短信列表
def void sms_sent_list(){
	'发送获取已发短信列表:10条'
	ResponseObject sms_sent_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/SMS/sms_sent_list", [('size'):10,('from'):0]),FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(sms_sent_list_response)
	WS.comment('已发短信列表10条body:'+jsonResponse)	
	
	'判断请求接口成功'
	if(WS.verifyResponseStatusCode(sms_sent_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		'返回数据中有total'
		assertThat(sms_sent_list_response.getResponseText()).contains('total')
		
		WS.comment('已读短信列表size'+jsonResponse.data.size)
		for (int index : (0..jsonResponse.data.size)) {
			
			if(jsonResponse.data[index].sent_static.fail>0){
				
				sms_sent_list_unreceived(jsonResponse.data[index].message_id,jsonResponse.data[index].sent_static.fail)
				
				return
			}
			
			
			
			
		}
		
		
		
		
	}
	
	
}



//未收到消息
def void sms_sent_list_unreceived(String message_id,int size){
	
	'发送获取未收到短信的联系人数据'
	ResponseObject sms_sent_list_unreceived_response=WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/sms_sent_unreceived', [('message_id'):message_id,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(sms_sent_list_unreceived_response)
	WS.comment('未收到短信的联系人数据body:'+jsonResponse)
	
	'请求接口成功'
	if(WS.verifyResponseStatusCode(sms_sent_list_unreceived_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		'返回数据中有total'
		assertThat(sms_sent_list_unreceived_response.getResponseText()).contains('total')
		
		
		
	}
	
}










