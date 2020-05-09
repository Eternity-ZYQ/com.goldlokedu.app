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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import groovy.json.JsonSlurper as JsonSlurper


TestData data=findTestData("Data Files/User Information/Account/teacher");
	
	'输入正确的账号密码'
	ResponseObject response=WS.sendRequest(findTestObject('Api/Mobile Api/Login And Logout/login',[('account'):data.getValue("account", 1),('password'):data.getValue("password", 1)]), FailureHandling.CONTINUE_ON_FAILURE)
	
	
	"返回码是200"
	if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	"保存用户信息"		
	save_message( response)
	
	}




//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}



//成功后保存信息
def void save_message(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
		
	"保存token"
	CustomKeywords.'public_method.Helper.addGlobalVariable'("access_token", jsonResponse.access_token)
	
	"保存user_id"
	CustomKeywords.'public_method.Helper.addGlobalVariable'("user_id", jsonResponse.user_id)
	
	"保存session_id"
	CustomKeywords.'public_method.Helper.addGlobalVariable'("session_id", jsonResponse.session_id)
	
	"保存user_school_id"
	CustomKeywords.'public_method.Helper.addGlobalVariable'("user_school_id", jsonResponse.user_school_id)
	
	"保存user_name"
	CustomKeywords.'public_method.Helper.addGlobalVariable'("user_name", jsonResponse.user_name)
	
	//WS.comment(GlobalVariable.user_name)
	
	
	
}