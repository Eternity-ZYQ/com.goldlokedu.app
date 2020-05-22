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

'获取区域接口'
ResponseObject response= WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Change City/change_city"), FailureHandling.CONTINUE_ON_FAILURE)
def jsonResponse=get_jsonResponse(response)
def city_name='' //区域名称

if(jsonResponse.data.size>0){
	
	outer:for(int x:(0..jsonResponse.data.size-1)){
			
			if(jsonResponse.data[x].children.size>0){
				
				for(int y:(0..jsonResponse.data[x].children.size-1)){
					def url=jsonResponse.data[x].children[y].url
					Mobile.comment('接口获取到的url:'+url+',本地的url:'+GlobalVariable.MobileHost)
					if(Mobile.verifyEqual(url, GlobalVariable.MobileHost, FailureHandling.OPTIONAL)){
						Mobile.comment('匹配成功')
						'拿到地区名称'
						city_name=jsonResponse.data[x].children[y].district_name
						break outer
					}
				}
			}
		}
	
}

'点击切换城市按钮'
Mobile.tap(findTestObject('Object Repository/Android/Login Page Element/change_city_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.comment('选择的城市为:'+city_name)
'点击地区,进行选择地区'
Mobile.tap(findTestObject('Object Repository/Android/City Element/city_name', [('city_name'):city_name]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)






//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}
