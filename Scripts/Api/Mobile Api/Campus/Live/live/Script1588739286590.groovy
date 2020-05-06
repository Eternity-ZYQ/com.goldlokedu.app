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

def page=1
def page_size=10
def type='live'
def requese_site='app'

'获取直播列表'
ResponseObject live_response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Live/live_course_list', [('page'):page,('page_size'):page_size,('type'):type,('requese_site'):requese_site]), FailureHandling.CONTINUE_ON_FAILURE)
def live_jsonReponse=get_jsonResponse(live_response)

if(live_jsonReponse.data.records.size>0){
	WS.comment('直播课列表有数据')
	
	for(int x:(0..live_jsonReponse.data.records.size-1)){
		def live_id=live_jsonReponse.data.records[x].id
		def live_name=live_jsonReponse.data.records[x].interact_name
		WS.comment('即将进入'+live_name+'详情页面...')
		'进入详情页面'
		ResponseObject live_detail_response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Live/live_course_detail', [('live_id'):live_id]), FailureHandling.CONTINUE_ON_FAILURE)
		def live_detail_jsonResponse=get_jsonResponse(live_detail_response)
		
		def status=live_detail_jsonResponse.data.status
		if(WS.verifyEqual(status,'PLAYING', FailureHandling.OPTIONAL)){
			WS.comment(live_name+'正在直播中,即将获取直播流...')
			'获取直播流地址'
			WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Live/get_live_url', [('live_id'):live_id]), FailureHandling.CONTINUE_ON_FAILURE)
			
		}else if(WS.verifyEqual(status,'EXPIRED', FailureHandling.OPTIONAL)){
		
			WS.comment(live_name+'直播已结束')
			
		}else if(WS.verifyEqual(status,'UNPLAY', FailureHandling.OPTIONAL)){
		
			WS.comment(live_name+'直播未开始')
		}
		
		
	}
	
}else{

	WS.comment('直播课列表有数据')

}



//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}





