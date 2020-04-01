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

"选择地区"
WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Change City/change_city"), FailureHandling.CONTINUE_ON_FAILURE)

"登录"
WS.sendRequestAndVerify(findTestObject("Api/Mobile Api/Login And Logout/teacher_login"), FailureHandling.CONTINUE_ON_FAILURE)

"获取app菜单"
WS.sendRequestAndVerify(findTestObject("Api/Mobile Api/Menu/ios_app_menu"),FailureHandling.CONTINUE_ON_FAILURE)

"获取小铃铛列表:10条"
WS.sendRequestAndVerify(findTestObject("Api/Mobile Api/Campus/Bell/bell_reminder_search"),FailureHandling.CONTINUE_ON_FAILURE)


CustomKeywords