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

def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/school_query_list'
'获取新高考默认列表'
WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)

def search_school='北京大学'
def search_school_code='10001'

'搜索学校名称'
WS.sendRequestAndVerify(findTestObject(path, [('from'):from,('size'):size,('school_code_or_name'):search_school]), FailureHandling.CONTINUE_ON_FAILURE)
WS.sendRequestAndVerify(findTestObject(path, [('from'):from,('size'):size,('school_code_or_name'):search_school_code]), FailureHandling.CONTINUE_ON_FAILURE)



