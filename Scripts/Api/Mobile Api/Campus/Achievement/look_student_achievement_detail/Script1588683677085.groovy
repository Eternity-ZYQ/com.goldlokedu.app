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

'获取默认成绩列表'
ResponseObject achieve_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Achievement/achievement_list"), FailureHandling.CONTINUE_ON_FAILURE)
def achieve_jsonResponse=get_jsonResponse(achieve_response)

if(achieve_jsonResponse.data.records.size>0){
	
	WS.comment('默认成绩列表有数据')

	for(int x:(0..achieve_jsonResponse.data.records.size-1)){
		def exam_id=achieve_jsonResponse.data.records[x].exam_id
		def exam_name=achieve_jsonResponse.data.records[x].exam_name
		def class_name=achieve_jsonResponse.data.records[x].klass_name
		def exam_category=achieve_jsonResponse.data.records[x].exam_category
		def semester_name=achieve_jsonResponse.data.records[x].semester_name
		
		WS.comment('进入以下详情：'+semester_name+exam_category+class_name+exam_name)
		'进入成绩详情页面'
		ResponseObject class_achieve_response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Achievement/look_student_achievement_list', [('exam_id'):exam_id]), FailureHandling.CONTINUE_ON_FAILURE)
		def class_achieve_jsonResponse=get_jsonResponse(class_achieve_response)
		
		if(class_achieve_jsonResponse.data.students.size>0){
			
			WS.comment(class_name+'内有学生，即将查看第一个学生成绩详情')
			
			
				
				def student_name=class_achieve_jsonResponse.data.students[0].student_name
				def student_id=class_achieve_jsonResponse.data.students[0].student_user_id
				WS.comment('查看'+student_name+'成绩详情中...')
				WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Achievement/look_student_achievement_detail', [('exam_id'):exam_id,('student_id'):student_id]), FailureHandling.CONTINUE_ON_FAILURE)
				
			
			
		}else{
		
			WS.comment(class_name+'内没有学生，不查看学生成绩详情')
		
		}
		
	}
	
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}