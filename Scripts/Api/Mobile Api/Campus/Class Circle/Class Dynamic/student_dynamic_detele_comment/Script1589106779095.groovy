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


'学生班级信息接口路径'
def path='Object Repository/Api/Mobile Api/My/Individual/Student/query_student_information_class'
'获取学生班级信息'
ResponseObject class_information_response=WS.sendRequestAndVerify(findTestObject(path,[('student_id'):GlobalVariable.user_id]), FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)

		
def class_id=class_information_jsonResponse.data.klass_id
WS.comment('class_id:'+class_id)

'获取班级动态列表数据'
ResponseObject search_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):class_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)

if(search_dynamic_list_jsonResponse.data.size>0){
	WS.comment("有动态")
	
	for(int k:(0..search_dynamic_list_jsonResponse.data.size-1)){
		
		if(search_dynamic_list_jsonResponse.data[k].replies.size>0){
			WS.comment('第'+(k+1)+'条动态有评论...')
			
			for(int i:(0..search_dynamic_list_jsonResponse.data[k].replies.size-1)){
				def commentator=search_dynamic_list_jsonResponse.data[k].replies[i].commentator
				WS.comment('评论人的id:'+commentator)
				'如果是自己发的可以删除'
				if(WS.verifyEqual(commentator, GlobalVariable.user_id, FailureHandling.OPTIONAL)){
					WS.comment('是本人评论,即将进行删除...')
					
					'删除动态评论'
					WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/delete_comment',[('comment_id'):search_dynamic_list_jsonResponse.data[k].replies[i].comment_id]), FailureHandling.CONTINUE_ON_FAILURE)
					
					break
				}else{
				
					WS.comment('不是本人评论,不能删除...')
				
				}				
				
			}
						
		}else{
		
			WS.comment('第'+(k+1)+'条动态没有评论')
		}
					
	}
						
}else{

	WS.comment("没有有动态")

}




//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}








