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

//存在问题,若退出按钮没初见在屏幕上,需要向上拉,直到退出按钮出现在屏幕上
'判断按钮在不在屏幕上'
boolean isPresent = Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/My/Logout/logout_btn'), 
    GlobalVariable.G_Timeout, FailureHandling.OPTIONAL)

'不存在则上拉一下屏幕'
if (!(isPresent)) {
    Mobile.callTestCase(findTestCase('Android/Pubilc Action/wipe_up'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

'点击退出'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/My/Logout/logout_btn'), GlobalVariable.G_Timeout, 
    FailureHandling.CONTINUE_ON_FAILURE)

