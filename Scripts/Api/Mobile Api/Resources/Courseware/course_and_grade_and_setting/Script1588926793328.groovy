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

'查看是否已经筛选了年级和学科'
boolean is_setting=is_setting()

if(is_setting){
	WS.comment('已经设置过年级及学科')
	
}else{
	WS.comment('没有设置过年级及学科')
	'获取学科年级'
	def jsonResponse=get_property_items_setting()
	'获取全部的学科'
	def courses=get_courses(jsonResponse)
	'获取全部的年级'
	def grades=get_grade(jsonResponse)
	'设置年级学科'
	set_setting(courses,grades)
	
}




//发送请求,设置全选的年级与学科
def void set_setting(String courses,String grades){
	def path='Object Repository/Api/Mobile Api/Resources/Courseware/courseware_set_setting'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('courses'):courses,('grades'):grades]), FailureHandling.CONTINUE_ON_FAILURE)
		
}


//获取属性
def Object get_property_items_setting(){
	def path='Object Repository/Api/Mobile Api/Resources/Courseware/coursewrae_property_items'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	
	return jsonResponse
}

//是否设置了年级学科
def Boolean is_setting(){
	def path='Object Repository/Api/Mobile Api/Resources/Courseware/courseware_setting'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	def is_setting=false
	if(jsonResponse.grades.size>0&&jsonResponse.courses.size>0){
		is_setting=true
	}
	
	return is_setting
}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}



def String get_grade(Object jsonResponse){
	List list=new ArrayList()
	
	if(jsonResponse.grades.size>0){
		WS.comment('年级选择有数据')
		for(int x:(0..jsonResponse.grades.size-1)){
			
			def temp='{"id": "'+jsonResponse.grades[x].id+'",'+'"chinese_name": "'+jsonResponse.grades[x].chinese_name+'"}'
					
			list.add(temp)	
		}		
	}else{
		WS.comment('年级选择没有数据')
	}
	
	return list.toString()
}

def String get_courses(Object jsonResponse){
	List list=new ArrayList()
	
	if(jsonResponse.courses.size>0){
		WS.comment('年级选择有数据')
		for(int x:(0..jsonResponse.courses.size-1)){
			WS.comment('id:'+jsonResponse.courses[x].id)
			WS.comment('chinese_name:'+jsonResponse.courses[x].chinese_name)
			
			def temp='{"id": "'+jsonResponse.courses[x].id+'",'+'"chinese_name": "'+jsonResponse.courses[x].chinese_name+'"}'
					
			list.add(temp)
		}
	}else{
		WS.comment('年级选择没有数据')
	}
	
	return list.toString()
}

