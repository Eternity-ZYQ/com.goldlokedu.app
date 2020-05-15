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

'学生默认作业列表接口路径'
def path='Object Repository/Api/Mobile Api/Campus/Homework/student_homework_list'
'加载缩略图接口路径'
def path1='Object Repository/Api/Mobile Api/Campus/Homework/download_picture_thumb'

'获取默认学生作业列表'
ResponseObject list_response=WS.sendRequestAndVerify(findTestObject(path, [('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def list_jsonResponse=get_jsonResponse(list_response)

if(list_jsonResponse.data.size>0){
	
	WS.comment('学生作业列表有数据')
	for(int x:(0..list_jsonResponse.data.size-1)){
		def date_key=list_jsonResponse.data[x].date_key
		WS.comment('即将查看'+date_key+'的作业')
		
		if(list_jsonResponse.data[x].homework_list.size>0){
			WS.comment(date_key+'没有作业数据')
			
			for(int y:(0..list_jsonResponse.data[x].homework_list.size-1)){
				def course=list_jsonResponse.data[x].homework_list[y].course.course_name
				def homework_id=list_jsonResponse.data[x].homework_list[y].homework_id
				def pictures=list_jsonResponse.data[x].homework_list[y].pictures
				def content=list_jsonResponse.data[x].homework_list[y].content
				WS.comment('日期:'+date_key+',科目:'+course+',内容:'+content)
				if(pictures!=null&&pictures.size>0){
					WS.comment('有图片,即将加载...')
					for(int i:(0..pictures.size-1)){
						def picture_id=pictures[i].picture_id
						'获取作业图片'
						WS.sendRequestAndVerify(findTestObject(path1, [('picture_id'):picture_id]), FailureHandling.CONTINUE_ON_FAILURE)
						
					}
				}else{
					WS.comment('没有图片')
				}
			}
		}else{
		
			WS.comment(date_key+'有作业数据')
		}
	}
}else{
	WS.comment('学生作业列表没有数据')

}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}