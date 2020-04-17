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
ResponseObject class_information_response=WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/My/Individual/Teacher/teacher_related_class"), null, FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)


for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		WS.comment('class_id:'+class_id)
		def search_dynamic_list_jsonResponse=search_dynamic_list(class_id,from,size)
		
		if(search_dynamic_list_jsonResponse.data.size>0){
			WS.comment(class_name+"有动态")
			
			'评论的内容'
			def comment_content=CustomKeywords.'time.SystemTime.get_system_time'()+'发起评论'
			'评论第一条动态'
			dynamic_comment(search_dynamic_list_jsonResponse.data[0].moment_id,comment_content)
			
			for(int k:(0..search_dynamic_list_jsonResponse.data.size-1)){
						
				if(search_dynamic_list_jsonResponse.data[k].replies.size>0){
					WS.comment(class_name+'的第'+(k+1)+'条动态有评论...')
					
					for(int i:(0..search_dynamic_list_jsonResponse.data[k].replies.size-1)){

						def commentator=search_dynamic_list_jsonResponse.data[k].replies[i].commentator
						WS.comment('评论人的id:'+commentator)
						if(WS.verifyNotEqual(commentator, GlobalVariable.user_id, FailureHandling.OPTIONAL)){
							WS.comment('不是本人评论,即将进行回复...')
							'回复评论内容'
							def comment_reply_content=CustomKeywords.'time.SystemTime.get_system_time'()+'发起回复'
							dynamic_comment_reply(search_dynamic_list_jsonResponse.data[k].moment_id,comment_reply_content,search_dynamic_list_jsonResponse.data[k].replies[i].commentator,search_dynamic_list_jsonResponse.data[k].replies[i].commentator_name)
							break
						}else{
						
							WS.comment('是本人评论,不进行回复...')
						
						}		
					}					
					
				}else{
				
					WS.comment(class_name+'的第'+(k+1)+'条动态没有评论...')
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


//评论动态
def void dynamic_comment(String moment_id,String content){
	'发送动态接口评论'
	ResponseObject dynamic_comment_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/dynamic_comment",[('moment_id'):moment_id,('content'):content]), FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.comment('动态列表信息body:'+dynamic_comment_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(dynamic_comment_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(dynamic_comment_response, 'comment_id', false, FailureHandling.CONTINUE_ON_FAILURE)
			
		
	}
	
}



//回复其他人动态
def void dynamic_comment_reply(String moment_id,String content,String creator,String creator_name){
	'发送动态接口评论'
	ResponseObject dynamic_comment_reply_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/dynamic_comment_reply",[('moment_id'):moment_id,('content'):content,('creator'):creator,('creator_name'):creator_name]), FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.comment('动态列表信息body:'+dynamic_comment_reply_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(dynamic_comment_reply_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(dynamic_comment_reply_response, 'comment_id', false, FailureHandling.CONTINUE_ON_FAILURE)
			
		
	}
	
}





