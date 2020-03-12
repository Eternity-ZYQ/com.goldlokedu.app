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

'清除账号'
Mobile.clearText(findTestObject('Android/Login Page Element/username_text'), GlobalVariable.G_short_timeout)

"从文件中取出账号密码"
TestData td=findTestData("Data Files/User Information/Account/teacher")
account=td.getValue("account", 1)
password=td.getValue("password", 1)

'输入账号'
Mobile.setText(findTestObject('Android/Login Page Element/username_text'), account, GlobalVariable.G_short_timeout)

'输入密码'
Mobile.setText(findTestObject('Android/Login Page Element/password_text'), password, GlobalVariable.G_short_timeout)

'点击登录按钮登录'
Mobile.tap(findTestObject('Android/Login Page Element/login_btn'), GlobalVariable.G_short_timeout)

Mobile.delay(2)