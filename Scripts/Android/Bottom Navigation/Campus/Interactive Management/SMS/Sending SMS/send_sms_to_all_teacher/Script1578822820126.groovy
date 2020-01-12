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
import time.SystemTime
"点击发送短信tap栏"
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/sending_sms_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)


"点击通讯录+按钮进入通讯录"
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/add_contacts_image"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

"点击教师通讯录tab栏"
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/teacher_contacts_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

"点击全选按钮"
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/Teacher Contacts/checkbox_btn"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

"点击确定按钮"
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/sure_btn"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)


"选择联系人教师栏是否出现"
Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/teacher_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

//获取教师联系人文本,后面短信确认页的时候对比
String teacher_contacts_text=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/teacher_contacts_text"), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

 SystemTime st= new SystemTime()
 String time=st.get_system_time()
 
 ""
 //CustomKeywords.