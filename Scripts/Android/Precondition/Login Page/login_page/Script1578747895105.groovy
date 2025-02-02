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

"前置条件------打开已存在的app到默认普宁城市的登录页面"
Mobile.callTestCase(findTestCase("Test Cases/Android/Precondition/Choose City/to_choose_city_page"), null, FailureHandling.CONTINUE_ON_FAILURE)



"选择地区"
//地区选择普宁
//Mobile.callTestCase(findTestCase("Test Cases/Android/Login Module/Choose City/choose_city_puning"), null, FailureHandling.STOP_ON_FAILURE)
//地区选择揭西
Mobile.callTestCase(findTestCase("Test Cases/Android/Login Module/Choose City/choose_city_jiexi"), null, FailureHandling.STOP_ON_FAILURE)
//地区选择普宁其他
//Mobile.callTestCase(findTestCase("Test Cases/Android/Login Module/Choose City/choose_city_puningqita"), null, FailureHandling.STOP_ON_FAILURE)
//地区选择潮阳
//Mobile.callTestCase(findTestCase("Test Cases/Android/Login Module/Choose City/choose_city_chaoyang"), null, FailureHandling.STOP_ON_FAILURE)
//地区选择潮南
//Mobile.callTestCase(findTestCase("Test Cases/Android/Login Module/Choose City/choose_city_chaonan"), null, FailureHandling.STOP_ON_FAILURE)
