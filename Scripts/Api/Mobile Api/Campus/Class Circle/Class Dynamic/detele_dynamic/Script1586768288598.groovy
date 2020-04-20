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
ResponseObject class_information_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/teacher_related_class"), FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)

for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		WS.comment('class_name:'+class_name)
		WS.comment('class_id:'+class_id)
		'获取班级动态列表数据'
		ResponseObject search_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_dynamic_list",[('class_id'):class_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
		def search_dynamic_list_jsonResponse=get_jsonResponse(search_dynamic_list_response)
		
		if(search_dynamic_list_jsonResponse.data.size>0){
			WS.comment(class_name+"有动态")
			
			'获取教师是否为班主任信息'
			ResponseObject judge_adviser_response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/My/Individual/Teacher/judge_adviser', [('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
			def judge_adviser_jsonResponse=get_jsonResponse(judge_adviser_response)
			if(judge_adviser_jsonResponse.data.adviser){
				WS.comment('是'+class_name+'的班主任')

					'发送删除班级动态接口:删除第一条动态'
					WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/delete_class_dynamic",[('class_id'):class_id,('dynamic_moment_id'):search_dynamic_list_jsonResponse.data[0].moment_id]), FailureHandling.CONTINUE_ON_FAILURE)
					
				
			}else{
				WS.comment('不是'+class_name+'的班主任')
			
			}
			
		}else{
		
			WS.comment(class_name+"没有有动态")
		
		}
		
	}
	
	
}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}




