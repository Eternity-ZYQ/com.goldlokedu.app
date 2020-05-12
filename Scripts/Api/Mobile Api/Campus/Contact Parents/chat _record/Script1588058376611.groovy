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

'获取教师作为班主任管理的班级'
ResponseObject response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/manager_class"), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse = get_jsonResponse(response)

'是否有数据'
if(jsonResponse.data.size>0){
	
	WS.comment('是管理'+jsonResponse.data.size+'个班的班主任')
	
	'获取班级下的学生列表'
	for(int x:(0..jsonResponse.data.size-1)){
		WS.comment('获取第'+jsonResponse.data[x].klass_full_name+'学生列表中...')
		ResponseObject student_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact/Student/class_student_list", [('class_id'):jsonResponse.data[x].klass_id]), FailureHandling.CONTINUE_ON_FAILURE)
		def student_list_jsonResponse=get_jsonResponse(student_list_response)
		
		'班级是否有学生'
		if(student_list_jsonResponse.data.size>0){
			
			'获取与每个班第一个学生的聊天列表'	
			ResponseObject chat_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact Parents/chat _record", [('size'):size,('student_id'):student_list_jsonResponse.data[0].userId]), FailureHandling.CONTINUE_ON_FAILURE)
			def chat_list_jsonResponse =get_jsonResponse(chat_list_response)
			def next_poll_counter=chat_list_jsonResponse.next_poll_counter
			
			if(chat_list_jsonResponse.data.size>0){
				'是图片文件,则加载图片'
				for(int y:(0..chat_list_jsonResponse.data.size-1)){
					if(WS.verifyEqual(chat_list_jsonResponse.data[y].message_type, 'Json', FailureHandling.OPTIONAL)){
						
						WS.comment('该条信息是图片,加载中...')
						WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact Parents/dowmload_file", [('file_id'):chat_list_jsonResponse.data[y].message.file_id]), FailureHandling.CONTINUE_ON_FAILURE)
												
					}else{
						WS.comment('该条信息不是图片,不加载...')
					}
					
				}
			
			}
			
			'加载更多数据'
			if(next_poll_counter-size-1>0){
				
					WS.comment('可以下拉加载更多数据')
					
					def from_counter=1
					def to_counter=next_poll_counter-size-1
					
					WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Contact Parents/search_more_chat', [('from_counter'):from_counter,('to_counter'):to_counter,('student_id'):student_list_jsonResponse.data[0].userId]), FailureHandling.CONTINUE_ON_FAILURE)
					
				}else{
					
					WS.comment('没有更多数据了')
				
				}
									
		}else{
		
			WS.comment(jsonResponse.data[x].klass_full_name+'没有学生')
		}
		
	}
		
}else{

	WS.comment('不是任何一个班的班主任')

}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}