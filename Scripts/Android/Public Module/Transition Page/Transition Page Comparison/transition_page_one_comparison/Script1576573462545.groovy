import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import freemarker.core.BuiltInsForStringsMisc.booleanBI as booleanBI
import internal.GlobalVariable as GlobalVariable

//'调整图像对比相似度百分比'
//MobileDriverFactory.getDriver().setSetting(Setting.IMAGE_MATCH_THRESHOLD, 0.6)


'通过图像空间查看过渡页是否一致'
boolean isPresent = Mobile.verifyElementExist(findTestCase("Object Repository/Android/Transition Page Element/transition_page_one_layout"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

"判断是都相同"
assert isPresent==true

