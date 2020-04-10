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



'短信模板列表'
get_sms_template_list()

'设置content,title文本'
content=CustomKeywords.'time.SystemTime.get_system_time'()+'添加的模板内容'
title=CustomKeywords.'time.SystemTime.get_system_time'()+'添加的标题'

'添加模板'
add_sms_template(content,title)


'设置编辑的content,title文本'
content=CustomKeywords.'time.SystemTime.get_system_time'()+'编辑的模板内容'
title=CustomKeywords.'time.SystemTime.get_system_time'()+'编辑的标题'

'修改短信模板'
edit_sms_template(template_id,content,title)



if(data_size>0){
	'删除第一条模板'
	delete_sms_template(template_id)
	
}else if(data_size==0){

	WS.comment('没有短信模板,不执行删除')
}else{
	WS.verifyEqual('1', '2', FailureHandling.CONTINUE_ON_FAILURE)
	WS.comment('data_size异常:'+data_size)
}











//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())

	return jsonResponse
}



//获取短信模板列表数据
def void get_sms_template_list(){
	'发送短信列表接口获取数据'
	ResponseObject sms_template_response= WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/SMS/sms_template_list"), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse =get_jsonResponse(sms_template_response)
	WS.comment('短信模板列表返回数据body:'+sms_template_response.getResponseText())
	
	
	'短信模板列表请求是否成'
	if(WS.verifyResponseStatusCode(sms_template_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.comment('短信模板列表请求成功')
		
		'返回body是否包含data'
		assertThat(sms_template_response.getResponseText()).contains('data')
		
		'保存模板个数data_size'
		data_size=jsonResponse.data.size
		
		'判断模板个数大于0'
		if(data_size>0){
			
			'有模板则保存第一个模板的template_id'
			template_id=jsonResponse.data[0].template_id
			
			
		}
		
			
	}
		
}



//添加短信模板
def void add_sms_template(String content,String title){
	
	ResponseObject add_sms_template_response=WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/add_sms_template', [('content'):content,('title'):title]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse =get_jsonResponse(add_sms_template_response)
	WS.comment('添加短信模板列表返回数据body:'+add_sms_template_response.getResponseText())
	
	'添加短信接口请求是否成功'
	if(WS.verifyResponseStatusCode(add_sms_template_response, 200, FailureHandling.OPTIONAL)){
		
		WS.comment('添加短信模板列表请求成功')
		
		'返回body是否包含template_id'
		assertThat(add_sms_template_response.getResponseText()).contains('template_id')
		
	
	}else if(WS.verifyResponseStatusCode(add_sms_template_response, 416, FailureHandling.OPTIONAL)){
		
		WS.comment('添加短信模板列表请求成功:模板格式到达6个,不能再添加')
	
		WS.verifyElementPropertyValue(add_sms_template_response, 'code', 416, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyElementPropertyValue(add_sms_template_response, 'message', '模板最多创建6个哦！', FailureHandling.CONTINUE_ON_FAILURE)
		
	
	}
	
	
	
	
	
}




//删除短信模板
def void delete_sms_template(String id){
	'发送删除短信模板接口'
	ResponseObject delete_response=WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/delete_template', [('template_id'):id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse =get_jsonResponse(delete_response)
	WS.comment('删除短信模板列表返回数据body:'+delete_response.getResponseText())
	
	'判断接口请求是否成功'
	if(WS.verifyResponseStatusCode(delete_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.comment('删除短信模板列表请求成功')
		
		WS.verifyElementPropertyValue(delete_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
		
		
	}
	
	
	
	
	
	
}


//编辑短信模板
def void edit_sms_template(String id,String new_content,String new_title){
	
	'获取短信模板列表,拿到要修改的模板'
	get_sms_template_list()
	
	'发送编辑短信模板'
	ResponseObject edit_response=WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/SMS/edit_sms_template', [('template_id'):id,('content'):new_content,('title'):new_title]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse =get_jsonResponse(edit_response)
	WS.comment('编辑短信模板列表返回数据body:'+edit_response.getResponseText())
	
	'判断接口请求是否成功'
	if(WS.verifyResponseStatusCode(edit_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		'返回body中包含template_id'
		assertThat(edit_response.getResponseText()).contains('template_id')
		
		
		
	}
	
	
	
}

























