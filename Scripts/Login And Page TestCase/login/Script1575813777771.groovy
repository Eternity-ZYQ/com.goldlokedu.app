import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

"输入用户名称"
Mobile.setText(findTestObject('Login Page Element/userName'),username,1)


"输入密码"
Mobile.setText(findTestObject('Login Page Element/password'),password,1)

//"暂停2秒"
//Mobile.delay(2)

//"隐藏键盘"
//Mobile.hideKeyboard(FailureHandling.STOP_ON_FAILURE)

//用点击完成按钮代替隐藏键盘功能
"点击完成按钮,隐藏键盘"
Mobile.tap(findTestObject('Object Repository/Keyboard Element/keyboard_english_done'), 0)


"点击登录按钮"
Mobile.tap(findTestObject('Object Repository/Login Page Element/login_btn'),1)





