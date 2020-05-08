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

def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/college_entrance_exam_information_list'
'获取高考资讯默认列表'
ResponseObject response=WS.sendRequestAndVerify(findTestObject(path, [('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def jspnResponse=get_jsonResponse(response)

if(search_content==''){
	
	if(jspnResponse.data.size>0){
		WS.comment('高考资讯列表有数据')
		'获取第一条的标题作为搜索内容'
		search_content=jspnResponse.data[0].news.title
		
	}else{
	
		WS.comment('高考资讯列表没有数据')
		
	}
}

'搜索高考资讯'
search(search_content)


def void search(String search_content){
	
	
}


//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}