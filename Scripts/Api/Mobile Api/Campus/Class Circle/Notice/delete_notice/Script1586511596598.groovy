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
import static org.assertj.core.api.Assertions.*
import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable



'获取教师关联班级信息'
def class_information_jsonResponse=get_class_information()


for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		WS.comment('class_id:'+class_id)
		def jsonResponse=search_notice_list(class_id,0,10)
		
		if(jsonResponse.data.size>0){
			
			if(jsonResponse.data[0].is_deletable){
				
				detele_notice(jsonResponse.data[0].bulletin_id)
				
			}else{
			
				WS.comment('不是'+class_name+'的班主任,不能删除该班公告')
			
			}
										
		}else if(jsonResponse.data.size==0){
		
			WS.comment(class_name+'公告列表为空:'+jsonResponse.data.size)
		}
		
		
		
	}
	
	
}













//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}



//获取教师关联班级信息
def Object get_class_information(){
	'获取教师关联班级信息'
	ResponseObject class_information_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/teacher_related_class"), FailureHandling.CONTINUE_ON_FAILURE)
	
	def class_information_jsonResponse=get_jsonResponse(class_information_response)
	WS.comment('班级信息'+class_information_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(class_information_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(class_information_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyElementPropertyValue(class_information_response, 'message', '操作成功', FailureHandling.CONTINUE_ON_FAILURE)
		
		return class_information_jsonResponse
		
	}
	
	return
	
}



//获取班级公告列表数据
def Object search_notice_list(String class_id,int from ,int size){
	'发送获取班级公告列表接口'
	ResponseObject search_notice_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/search_notice_list", [('class_id'):class_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def search_notice_list_jsonResponse=get_jsonResponse(search_notice_list_response)
	WS.comment('班级公告列表信息body:'+search_notice_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(search_notice_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		assertThat(search_notice_list_response.getResponseText()).contains('total')		
		
		
		return search_notice_list_jsonResponse
		
	}
	
	
}



//删除公告
def void detele_notice(String bulletin_id){
	'发送获取班级公告列表接口'
	ResponseObject detele_notice_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Notice/delete_notice", [('bulletin_id'):bulletin_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def detele_notice_jsonResponse=get_jsonResponse(detele_notice_response)
	WS.comment('删除班级公告body:'+detele_notice_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(detele_notice_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(detele_notice_response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
			
	}
	
}