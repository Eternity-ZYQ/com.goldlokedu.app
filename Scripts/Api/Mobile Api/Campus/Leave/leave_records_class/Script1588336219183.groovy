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


'获取教师关联班级信息'
ResponseObject class_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/teacher_related_class"), FailureHandling.CONTINUE_ON_FAILURE)
def class_jsonRespinse=get_jsonResponse(class_response)

'是否有数据'
if(class_jsonRespinse.data.size>0){
	
	WS.comment(GlobalVariable.user_name+'关联'+class_jsonRespinse.data.size+'个年级')
	
	for(int x:(0..class_jsonRespinse.data.size-1)){
		def grade_name=class_jsonRespinse.data[x].grade_name
		WS.comment('年级：'+grade_name)
		
		if(class_jsonRespinse.data[x].klass.size>0){
			
			for(int y:(0..class_jsonRespinse.data[x].klass.size-1)){
				
				def class_name=class_jsonRespinse.data[x].klass[y].klass_full_name
				def class_id=class_jsonRespinse.data[x].klass[y].klass_id
				WS.comment('班级：'+class_name)
				'获取筛选班级的请假记录列表数据'
				WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Leave/leave_records_class', [('from'):from,('size'):size,('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
				
			}
			
		}else{
			WS.comment(grade_name+'下没有班级')
		}
		
	}
	
}else{
	
	WS.comment('教师没有关联数据，不能查看请假记录列表')

}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}