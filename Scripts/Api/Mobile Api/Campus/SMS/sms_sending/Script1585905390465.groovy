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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable

'判断该学校是否开通了短信业务'
ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/can_send'))

def jsonResponse = get_jsonResponse(response)

//必须该学校开通了短信，才能发送短信
if (jsonResponse.can_send) {
    '成功发送短信'
    success_now_sms()
} else {
    WS.comment('该学校未开通短信服务')
}





//获取返回体json解析
Object get_jsonResponse(ResponseObject response) {
    def jsonSlurper = new JsonSlurper()

    def jsonResponse = jsonSlurper.parseText(response.getResponseText())

    return jsonResponse
}

void success_now_sms() {
	//成功发送即时短信--发给自己
	//sms_type,sms_signature,sms_signature_id,sms_content
	'保存sms_type到全局变量'
	save_sms_type()
	
	'保存sms_signature，sms_signature_id'
	save_sms_signature_and_sms_signature_id()
	
	'获取要发送的短信内容sms_content'
	def sms_content=get_sms_contnt()
	
	'输出sms_content'
	WS.comment(sms_content)
	

	'请求发送短信接口'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/sms_sending',[('sms_content'):sms_content,('teacher_ids'):GlobalVariable.user_id]))
	
	WS.comment(response.responseText)
	
	//请求接口成功
	if(WS.verifyResponseStatusCode(response, 200)){
		
		"文本result值:Success"
		WS.verifyElementPropertyValue(response, 'result', "Success")
		
		
		
	}
	
	
}
//保存sms_type到全局变量
void save_sms_type() {
	
    '发送获取sms_type接口'
    ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/sms_type'))

    def jsonResponse = get_jsonResponse(response)

	def num=(int)(Math.random()*100)%jsonResponse.data.size
	
	WS.comment('sms_type:'+jsonResponse.data[num].category_id)
	
	
	CustomKeywords.'public_method.Helper.addGlobalVariable'('sms_type', jsonResponse.data[num].category_id)
}

//保存sms_signature，sms_signature_id到全局变量
void save_sms_signature_and_sms_signature_id(){
	
	'发送获取sms_signature，sms_signature_id接口'
	ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/sms_signature'))

	def jsonResponse = get_jsonResponse(response)
	
	def num=(int)(Math.random()*100)%jsonResponse.data.size
	WS.comment('size:'+jsonResponse.data.size)
	WS.comment('num:'+num)
	WS.comment('sms_signature:'+jsonResponse.data[num].name)
	WS.comment('sms_signature_id'+jsonResponse.data[num].signature_id)
	
	CustomKeywords.'public_method.Helper.addGlobalVariable'('sms_signature', jsonResponse.data[num].name)
	CustomKeywords.'public_method.Helper.addGlobalVariable'('sms_signature_id', jsonResponse.data[num].signature_id)
	
	
}

//获取发送短信的内容：当前时间时间+'接口测试及时短信'
String get_sms_contnt(){
	
	def time=CustomKeywords.'time.SystemTime.get_system_time'()
	
	def sms_content=time+'接口测试及时短信'
	
	return sms_content
	
}



