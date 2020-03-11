import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.junit.After

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

"班级圈标题"
String title=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_circle_title"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyMatch("班级圈",title, false, FailureHandling.CONTINUE_ON_FAILURE)



//"切换班级按钮"
//Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_btn"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
//"切换班级文字"
//String change_class_text=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Change Class/change_class_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
//Mobile.verifyMatch("切换班级",change_class_text, false, FailureHandling.CONTINUE_ON_FAILURE)




"班级tab控件"
Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_tab_btn"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
"班级tab标题控件"
Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_tab_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)




"班训标题图标"
Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_icon_image"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
"班训标题文字"
String class_training_title=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyMatch("班训",class_training_title, false, FailureHandling.CONTINUE_ON_FAILURE)



"公告标题图标"
Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_icon_image"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
"公告标题文字"
String notice=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyMatch("公告",notice, false, FailureHandling.CONTINUE_ON_FAILURE)




"相册标题图标"
Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_icon_image"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
"相册标题文字"
String album=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyMatch("相册",album, false, FailureHandling.CONTINUE_ON_FAILURE)



"动态标题图标"
Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_icon_image"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
"动态标题文字"
String dynamic=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyMatch("动态",dynamic, false, FailureHandling.CONTINUE_ON_FAILURE)




