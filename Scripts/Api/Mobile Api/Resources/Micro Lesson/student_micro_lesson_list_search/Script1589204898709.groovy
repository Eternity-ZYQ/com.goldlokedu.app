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

'学生微课搜索接口路径'
def path='Object Repository/Api/Mobile Api/Resources/Micro Lesson/student_micro_lesson_list_search'
'获取年纪学科属性'
def setting_jsonResponse=get_property_items_setting()
'获取年级'
def grades=setting_jsonResponse.grades[0].id
'获取全部科目'
def courses=get_courses(setting_jsonResponse)
ResponseObject response=WS.sendRequestAndVerify(findTestObject(path, [('from'):from,('size'):size,('content'):search_content,('grades'):grades,('courses'):courses]), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse =get_jsonResponse(response)

if(jsonResponse.result.size>0){
	
	WS.comment('搜索到数据:'+search_content)
	for(int x:(0..jsonResponse.result.size-1)){
		
		def name=jsonResponse.result[x].name
		WS.comment('真实值:'+name+',期望值:'+search_content)
		WS.verifyMatch(name, '.*'+search_content+'.*', true, FailureHandling.CONTINUE_ON_FAILURE)
	}
}else{

	WS.comment('搜索不到数据:'+search_content)
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}


def String get_courses(Object jsonResponse){
	def courses=''
	
	if(jsonResponse.courses.size>0){
		WS.comment('有课程')
		
		for(int x:(0..jsonResponse.courses.size-1)){
			if(courses==''){
				
				courses=courses+jsonResponse.courses[x].id
			}else{
			
				courses=courses+','+jsonResponse.courses[x].id
			}
			
		}
		
	}else{
		WS.comment('没有课程')
	
	}
	
	return courses
	
}



//获取属性
def Object get_property_items_setting(){
	def path='Object Repository/Api/Mobile Api/Resources/Courseware/coursewrae_property_items'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	
	return jsonResponse
}