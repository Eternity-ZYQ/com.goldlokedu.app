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

'获取教授和管理的班级'
ResponseObject class_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/teacher_manager_and_teach_class"), FailureHandling.CONTINUE_ON_FAILURE)
def class_jsonResponse=get_jsonResponse(class_response)

if(class_jsonResponse.data.size>0){
	
	WS.comment('有管理和教授的班级数据')
	'拿第一个班级的信息'
	def class_name=class_jsonResponse.data[0].klass_full_name
	def class_id=class_jsonResponse.data[0].klass_id
	def location='化学实验室'+class_name+'的班牌'
	
	'编辑或添加班牌进行绑定'
	WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Brand/binging_brand', [('class_id'):class_id,('location'):location,('device_token'):device_token]), FailureHandling.CONTINUE_ON_FAILURE)
	
}else{

	WS.comment('没有管理和教授的班级，不能进行班牌绑定')
}







//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}
