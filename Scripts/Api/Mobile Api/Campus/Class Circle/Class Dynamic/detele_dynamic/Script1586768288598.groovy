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
def class_information_jsonResponse=get_class_information()

for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		WS.comment('class_id:'+class_id)
		def search_dynamic_list_jsonResponse=search_dynamic_list(class_id,0,10)
		
		if(search_dynamic_list_jsonResponse.data.size>0){
			WS.comment(class_name+"有动态")
			
			if(judge_adviser(class_id)){
				WS.comment('是'+class_name+'的班主任')

					'删除第一条动态'
					detele_dynamic(class_id,search_dynamic_list_jsonResponse.data[0].moment_id)

				
			}else{
				WS.comment('不是'+class_name+'的班主任')
			
			}
			
		}else{
		
			WS.comment(class_name+"没有有动态")
		
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


//发送获取动态列表
def Object search_dynamic_list(String class_id,int from,int size){
	'获取班级列表数据'
	ResponseObject search_dynamic_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):class_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
	WS.comment('动态列表信息body:'+search_dynamic_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(search_dynamic_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(search_dynamic_list_response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return search_dynamic_list_jsonResponse
		
	}
	
	return
	
	
	
	
}



//删除班级动态
def void detele_dynamic(String class_id,String dynamic_moment_id){
	'发送删除班级动态接口'
	ResponseObject detele_dynamic_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/delete_class_dynamic",[('class_id'):class_id,('dynamic_moment_id'):dynamic_moment_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	
	WS.comment('删除班级动态返回数据body:'+detele_dynamic_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(detele_dynamic_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		
		WS.verifyElementPropertyValue(detele_dynamic_response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
		
		
	}
	
	
	
	
}



//判断是否是班主任
def boolean judge_adviser(String class_id){
	'发送获取教师是否为班主任接口数据'
	ResponseObject judge_adviser_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/judge_adviser",[('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def judge_adviser_jsonResponse=get_jsonResponse(judge_adviser_response)
	WS.comment('是否为班主任返回body:'+judge_adviser_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(judge_adviser_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(judge_adviser_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyElementPropertyValue(judge_adviser_response, 'message', '操作成功', FailureHandling.CONTINUE_ON_FAILURE)
		
		return judge_adviser_jsonResponse.data.adviser
		
	}
	
	return
	
}


