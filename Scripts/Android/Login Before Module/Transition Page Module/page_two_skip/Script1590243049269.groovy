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

"例前置条件:安装并打开app并同意隐私政策"
Mobile.callTestCase(findTestCase("Test Cases/Android/Login Before Module/Privacy Policy Page/agree_privacy_policy"), [:], FailureHandling.STOP_ON_FAILURE)

"左滑页面到第二个过渡页"
CustomKeywords.'public_action.slide_screen.left_slide_screen'()

"点击第二页跳转"
Mobile.tap(findTestObject('Object Repository/Android/Transition Page Element/transition_page_two_skip'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)