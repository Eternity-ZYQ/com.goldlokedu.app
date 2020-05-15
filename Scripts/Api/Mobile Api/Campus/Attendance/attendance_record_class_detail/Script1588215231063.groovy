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
		ResponseObject class_detail_response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Attendance/attendance_record_class_detail', [('check_date'):time,('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
		def class_detail_jsonResponse =get_jsonResponse(class_detail_response)
		
		'是否班级内有学生'
		if(class_detail_jsonResponse.data.detail.size>0){
			
			WS.comment(class_name+'有学生,即将查看是否有考勤图片...')
			
			for(int y:(0..class_detail_jsonResponse.data.detail.size-1)){
				def student_name=class_detail_jsonResponse.data.detail[y].student_name
				WS.comment('查看'+student_name+',是否有考勤图片...')
				
				'是否有设置考勤规则'
				if(class_detail_jsonResponse.data.detail[y].detail.size>0){
					'查看每个考勤打卡点是否有照片'
					for(int i:(0..class_detail_jsonResponse.data.detail[y].detail.size-1)){
						
						if(class_detail_jsonResponse.data.detail[y].detail[i].pic!=''){
							WS.comment('有照片,即将获取')

							def pic =class_detail_jsonResponse.data.detail[y].detail[i].pic
							WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Attendance/dowmload_picture", [('pic'):pic]), FailureHandling.CONTINUE_ON_FAILURE)
						}else{
						
							WS.comment('没有照片')
						}					
					}
				}else{
				
					WS.comment('没有考勤打卡点')
				}
			}		
		}else{
		
			WS.comment(class_name+'没有学生')
		}
		
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