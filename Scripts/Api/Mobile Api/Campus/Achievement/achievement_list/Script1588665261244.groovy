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
WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Achievement/achievement_list"), FailureHandling.CONTINUE_ON_FAILURE)


'获取教授及管理班级'
ResponseObject class_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/teacher_manager_and_teach_class"), FailureHandling.CONTINUE_ON_FAILURE)
def class_jsonResponse=get_jsonResponse(class_response)

if(class_jsonResponse.data.size>0){
	
	WS.comment('有管理和教授的班级')
	
	for(int x:(0..class_jsonResponse.data.size-1)){
		
		def class_id=class_jsonResponse.data[x].klass_id
		def class_name=class_jsonResponse.data[x].klass_full_name
		def page=1
		WS.comment('加载'+class_name+'成绩列表中...')
		
		WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Achievement/class_achievement_list', [('class_id'):class_id,('page'):page]), FailureHandling.CONTINUE_ON_FAILURE)
		
	}
	
	
}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}