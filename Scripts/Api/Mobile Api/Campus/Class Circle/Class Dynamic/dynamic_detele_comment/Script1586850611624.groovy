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
			
			for(int k:(0..search_dynamic_list_jsonResponse.data.size-1)){
				
				if(search_dynamic_list_jsonResponse.data[k].replies.size>0){
					WS.comment(class_name+'的第'+(k+1)+'条动态有评论...')
					
					for(int i:(0..search_dynamic_list_jsonResponse.data[k].replies.size-1)){
						def commentator=search_dynamic_list_jsonResponse.data[k].replies[i].commentator
						WS.comment('评论人的id:'+commentator)
						'如果是自己发的可以删除'
						if(WS.verifyEqual(commentator, GlobalVariable.user_id, FailureHandling.OPTIONAL)){
							WS.comment('是本人评论,即将进行删除...')
							detele_comment(search_dynamic_list_jsonResponse.data[k].replies[i].comment_id)
							break
						}else{
						
							WS.comment('不是本人评论,不能删除...')
						
						}
						
						
						
					}
					
					
				}else{
				
					WS.comment(class_name+'的第'+(k+1)+'条动态没有评论')
				}
				
				
				
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


//删除动态评论
def void detele_comment(String comment_id){
	'获取班级列表数据'
	ResponseObject detele_comment_response=WS.sendRequest(findTestObject('Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/delete_comment',[('comment_id'):comment_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.comment('动态列表信息body:'+detele_comment_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(detele_comment_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(detele_comment_response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
		
		
	}
	
	
	
	
}





