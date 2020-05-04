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

'获取教师作业列表'
ResponseObject homework_respons=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Homework/teacher_homewrok_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def homework_jsonResponse=get_jsonResponse(homework_respons)

'是否有数据'
if(homework_jsonResponse.data.size>0){
	
	WS.comment('作业列表有数据')
	for(int x:(0..homework_jsonResponse.data.size-1)){
		def homework_date=homework_jsonResponse.data[x].date_key       //作业日期
		WS.comment(homework_date+'的作业')
		if(homework_jsonResponse.data[x].homework_list.size>0){
			WS.comment('有科目作业')
			
			for(int y:(0..homework_jsonResponse.data[x].homework_list.size-1)){
				
				def course_name=homework_jsonResponse.data[x].homework_list[y].course.course_name
				'是否有图片'
				if(homework_jsonResponse.data[x].homework_list[y].pictures!=null&&homework_jsonResponse.data[x].homework_list[y].pictures.size>0){
					
					WS.comment('有图片的科目：'+course_name)
					for(int i:(0..homework_jsonResponse.data[x].homework_list[y].pictures.size-1)){
						
						def picture_id=homework_jsonResponse.data[x].homework_list[y].pictures[i].picture_id
						
						'加载缩略图'
						WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Homework/download_picture_thumb', [('picture_id'):picture_id]), FailureHandling.CONTINUE_ON_FAILURE)
						
						'加载原略图'
						WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Homework/download_picture', [('picture_id'):picture_id]), FailureHandling.CONTINUE_ON_FAILURE)
						
					}
					
				}else{
				
					WS.comment('没有图片')
				
				}
				
			}
			
		}else{
		
		}
		
		
		
		
	}
	
	
}else{

	WS.comment('作业列表没有数据')
}


//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}