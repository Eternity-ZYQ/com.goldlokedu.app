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
import internal.GlobalVariable as GlobalVariable

'发送获取教师是否为班主任接口数据'
ResponseObject judge_adviser_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/judge_adviser",[('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)

//def judge_adviser_jsonResponse=get_jsonResponse(judge_adviser_response)
WS.comment('是否为班主任返回body:'+judge_adviser_response.getResponseText())

if(WS.verifyResponseStatusCode(judge_adviser_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	
	WS.verifyElementPropertyValue(judge_adviser_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyElementPropertyValue(judge_adviser_response, 'message', '操作成功', FailureHandling.CONTINUE_ON_FAILURE)
	
	return judge_adviser_response
	
}

return '接口返回非200,具体信息:'+judge_adviser_response.getResponseText()
