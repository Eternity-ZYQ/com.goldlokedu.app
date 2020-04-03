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


'登录用例:'

'账号置空登录'
account_empty()

'密码置空'
password_empty()

'账号密码错误'
account_password_error()

'输入正确的账号密码:'
Success_login_teacher();






//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}


//账号置空
def void account_empty(){
	TestData data=findTestData("Data Files/User Information/Account/teacher");
	
	'账号为空'
	ResponseObject response=WS.sendRequest(findTestObject('Api/Mobile Api/Login And Logout/login',[('account'):'',('password'):data.getValue("password", 1)]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(response)
	WS.comment(''+jsonResponse)
	
	WS.verifyResponseStatusCode(response, 500, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyElementPropertyValue(response, 'code', 100)
	WS.verifyElementPropertyValue(response, 'message', '系统内部错误')
	
}



//密码置空
def void password_empty(){
	TestData data=findTestData("Data Files/User Information/Account/teacher");
	
	'账号为空'
	ResponseObject response=WS.sendRequest(findTestObject('Api/Mobile Api/Login And Logout/login',[('account'):data.getValue("account", 1),('password'):'']), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(response)
	WS.comment(''+jsonResponse)
	
	WS.verifyResponseStatusCode(response, 400, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyElementPropertyValue(response, 'code', 102)
	WS.verifyElementPropertyValue(response, 'message', '账号或密码错误')
	
}


//账号密码错误
def void account_password_error(){
	TestData data=findTestData("Data Files/User Information/Account/teacher");
	
	'账号为空'
	ResponseObject response=WS.sendRequest(findTestObject('Api/Mobile Api/Login And Logout/login',[('account'):'18344261111',('password'):'123456a']), FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonResponse=get_jsonResponse(response)
	WS.comment(''+jsonResponse)
	
	WS.verifyResponseStatusCode(response, 400, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyElementPropertyValue(response, 'code', 102)
	WS.verifyElementPropertyValue(response, 'message', '账号或密码错误')
	
}



//教师成功登录
def void Success_login_teacher(){
	TestData data=findTestData("Data Files/User Information/Account/teacher");
	
	'输入正确的账号密码'
	ResponseObject response=WS.sendRequest(findTestObject('Api/Mobile Api/Login And Logout/login',[('account'):data.getValue("account", 1),('password'):data.getValue("password", 1)]), FailureHandling.CONTINUE_ON_FAILURE)
	
	
	"返回码是200"
	if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	"保存用户信息"		
	save_message( response)
	
	}
	
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