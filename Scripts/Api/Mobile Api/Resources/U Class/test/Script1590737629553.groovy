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

'获取优课列表'
ResponseObject list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Resources/U Class/u_class_list"), FailureHandling.CONTINUE_ON_FAILURE)
def list_jsonResponse=get_jsonResponse(list_response)

if(list_jsonResponse.data.size>0){
	for(int x:(0..list_jsonResponse.data.size-1)){
	
		def course_id=list_jsonResponse.data[x].course_id
		def title=list_jsonResponse.data[x].title
		
		'获取课程详情'
		ResponseObject detail_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Resources/U Class/u_class_course_detail",[('course_id'):course_id]), FailureHandling.CONTINUE_ON_FAILURE)
		def detail_jsonResponse=get_jsonResponse(detail_response)
		
		if(detail_jsonResponse.lessons.size>0){
			
			for(int y:(0..detail_jsonResponse.lessons.size-1)){
				
				def videos_list=detail_jsonResponse.lessons[y].videos
				def videos_title=detail_jsonResponse.lessons[y].lesson.title
				
				if(videos_list.size>0){
					
					for(int i:(0..videos_list.size-1)){
						
						def url=videos_list[i].url
						def name=videos_list[i].name
						WS.comment('课程名称:'+title+videos_title+name)
						WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Resources/U Class/video',[('url'):url]), FailureHandling.CONTINUE_ON_FAILURE)
						
					}
					
				}
				
			}
		}
	
	}
}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}