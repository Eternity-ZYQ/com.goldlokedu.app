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

'获取省份筛选项'
def province=get_random_province()

'发送筛选省份请求并验证'
send_and_verify_filter_province(from,size,province)

'获取层次筛选选项'
def level=get_level()

'发送筛选层次请求并验证'
send_and_verify_filter_level(from,size,level)

'筛选省市及层次并验证'
send_and_verify_filter_province_and_level(from,size,province,level)



def void send_and_verify_filter_province_and_level(int from,int size,String province,String level){
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/select_require_list_filter_province_and_level'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size,('degree'):level,('province'):province]), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	if(jsonResponse.data.size>0){
		
		WS.comment('筛选'+province+'及'+level+'后,有数据')
		for(int x:(0..jsonResponse.data.size-1)){
			def actual_level=jsonResponse.data[x].degree
			def actual_province=jsonResponse.data[x].province
			WS.verifyMatch(actual_level, level, false, FailureHandling.CONTINUE_ON_FAILURE)
			WS.verifyMatch(actual_province, province, false, FailureHandling.CONTINUE_ON_FAILURE)
		}
	}else{
		
		WS.comment('筛选'+province+'及'+level+'后,没有数据')
	}
}


//发送筛选层次请求并验证
def void send_and_verify_filter_level(int from,int size,String level){
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/select_require_list_filter_level'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size,('degree'):level]), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	if(jsonResponse.data.size>0){
		
		WS.comment('筛选'+level+'后,有数据')
		for(int x:(0..jsonResponse.data.size-1)){
			def actual_level=jsonResponse.data[x].degree
			WS.verifyMatch(actual_level, level, false, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
	}else{
		
		WS.comment('筛选'+level+'后,没有数据')
	}
}


def String get_level(){
	def level_array=['本科','专科']
	def num=(int)(Math.random()*1000)%level_array.size()
	
	return level_array[num]
}


//发送筛选省份请求并验证
def void send_and_verify_filter_province(int from,int size,String province){
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/select_require_list_filter_province'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size,('province'):province]), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	if(jsonResponse.data.size>0){
		
		WS.comment('筛选'+province+'后,有数据')
		for(int x:(0..jsonResponse.data.size-1)){
			def actual_province=jsonResponse.data[x].province
			WS.verifyMatch(actual_province, province, false, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
	}else{
		
		WS.comment('筛选'+province+'后,没有数据')
	}
	
	
}

//获取全部省份并随机返回一个省份
def String get_random_province(){
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/provinces'
	'发送获取省份接口'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	def num=(int)(Math.random()*1000)%jsonResponse.size
	
	return jsonResponse[num]
}


//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}