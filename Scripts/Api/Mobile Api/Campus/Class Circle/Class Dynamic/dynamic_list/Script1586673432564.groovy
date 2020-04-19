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

ResponseObject search_dynamic_list_response=search_dynamic_list(class_id,from,size,class_name)
def search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
if(search_dynamic_list_jsonResponse.data.size>0){
	WS.comment(class_name+"有动态")
			
	for(int k:(0..search_dynamic_list_jsonResponse.data.size-1)){
				
		if(search_dynamic_list_jsonResponse.data[k].pictures!=null&&search_dynamic_list_jsonResponse.data[k].pictures.data.size>0){
			WS.comment(class_name+'的第'+(k+1)+'条动态有图片,准备加载图片...')
					
			for(int i:(0..search_dynamic_list_jsonResponse.data[k].pictures.data.size-1)){
						
				WS.comment(class_name+'的第'+(k+1)+'条动态的第'+(i+1)+'张图片加载中...')
				dowmload_picture(search_dynamic_list_jsonResponse.data[k].pictures.data[i].attachment_id)
											
			}
					
		}else{
				
			WS.comment(class_name+'的第'+(k+1)+'条动态没有图片,不需要加载图')
		}				
	}
						
}else{
		
	WS.comment(class_name+"没有有动态")
		
}
		

return 





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}



//发送获取动态列表
def Object search_dynamic_list(String class_id,int from,int size){
	'获取班级列表数据'
	ResponseObject search_dynamic_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):class_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	
	//def search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
	WS.comment('动态列表信息body:'+search_dynamic_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(search_dynamic_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(search_dynamic_list_response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return search_dynamic_list_response
		
	}
	
	return
	
	
	
	
}



//加载动态图片
def void dowmload_picture(String picture_id){
	'发送下载动态图片接口'
	ResponseObject dowmload_picture_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/dowmload_picture",[('picture_id'):picture_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyResponseStatusCode(dowmload_picture_response, 200, FailureHandling.CONTINUE_ON_FAILURE)
	
	
	
	
}





