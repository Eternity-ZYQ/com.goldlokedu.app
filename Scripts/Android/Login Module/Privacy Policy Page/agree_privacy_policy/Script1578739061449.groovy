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

"判断隐私政策弹框存在"
Mobile.verifyElementExist(findTestObject("Object Repository/Android/Privacy Policy/privacy_policy_layout"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

"点击同意隐私政策按钮"
Mobile.tap(findTestObject("Object Repository/Android/Privacy Policy/privacy_policy_agree_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

"判断隐私政策弹框不存在"
Mobile.verifyElementNotExist(findTestObject("Object Repository/Android/Privacy Policy/privacy_policy_layout"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)