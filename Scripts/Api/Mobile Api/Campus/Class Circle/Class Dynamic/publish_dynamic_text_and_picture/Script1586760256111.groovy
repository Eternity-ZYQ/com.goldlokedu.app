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


'获取教师关联班级信息'
ResponseObject class_information_response=WS.callTestCase(findTestCase("null"), null, FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)


for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		WS.comment('class_id:'+class_id)
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		
		WS.comment(class_name+"发布动态中...")
		List picture_ids_list=new ArrayList<String>()
		def dynamic_content=CustomKeywords.'time.SystemTime.get_system_time'()+'发布带图片动态'
		
		def picture_count=0		//已添加几张照片
		def picture_max_count=1			//最多添加几张照片
		while (picture_count++<picture_max_count) {
			def upload_picture_jsonResponse=upload_picture()
			String data=get_data(upload_picture_jsonResponse)
			WS.comment(data)
			picture_ids_list.add(data)
		}
		
		WS.comment('data的数据:'+picture_ids_list.toString())
		publish_dynamic_text(class_id,dynamic_content,picture_ids_list.toString())
		
	}
		
}







//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}





//发布纯文本动态
def void publish_dynamic_text(String class_id,String content,String data){
	'发送发布动态接口'
	ResponseObject publish_dynamic_text_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/dynamic_publish_text_and_picture",[('class_id'):class_id,('content'):content,('data'):data]), FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.comment('发布动态返回数据body:'+publish_dynamic_text_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(publish_dynamic_text_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(publish_dynamic_text_response, 'moment_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		
	}
	
}



//上传动态图片
def Object upload_picture(){
	'上传动态图片'
	ResponseObject upload_picture_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/upload_dynamic_picture"), FailureHandling.CONTINUE_ON_FAILURE)
	
	def upload_picture_jsonResponse=get_jsonResponse(upload_picture_response)
	WS.comment('班级信息'+upload_picture_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(upload_picture_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(upload_picture_response, 'attachment_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(upload_picture_response, 'attachment_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(upload_picture_response, 'attachment_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(upload_picture_response, 'attachment_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return upload_picture_jsonResponse
		
	}
	
	return
	
	
}


//组成图片字段的data
def String get_data(Object jsonResponse){
	
	String data='{'+
				'"content_type": "'+jsonResponse.content_type+'",'+
				'"attachment_id": "'+jsonResponse.attachment_id+'",'+
				'"size":'+jsonResponse.size+
				'}'

				
	return data

}


