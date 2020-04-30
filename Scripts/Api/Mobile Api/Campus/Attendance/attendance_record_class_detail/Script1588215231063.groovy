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

'获取教师教授和管理的班级'
ResponseObject t_m_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/teacher_manager_and_teach_class"), FailureHandling.CONTINUE_ON_FAILURE)
def t_m_jsonResponse =get_jsonResponse(t_m_response)

if(t_m_jsonResponse.data.size>0){
	
	WS.comment('有管理和教授的班级')
	
	'获取每个班的考勤详情数据'
	for(int x:(0..t_m_jsonResponse.data.size-1)){
		def class_name=t_m_jsonResponse.data[x].klass_remark
		WS.comment('获取'+class_name+'考勤数据中...')
		
		def time=CustomKeywords.'time.SystemTime.get_day_time'()
		def class_id=t_m_jsonResponse.data[x].klass_id
		WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Attendance/attendance_record_class_detail', [('check_date'):time,('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
			
	}
	
}else{

	WS.comment('没有管理和教授的班级')
}











//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}