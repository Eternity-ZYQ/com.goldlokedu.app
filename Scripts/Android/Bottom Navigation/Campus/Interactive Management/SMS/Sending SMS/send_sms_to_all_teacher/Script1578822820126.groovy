import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.junit.After as After
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
import time.SystemTime as SystemTime

'点击发送短信tap栏'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/sending_sms_text'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击通讯录+按钮进入通讯录'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/add_contacts_image'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击教师通讯录tab栏'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/teacher_contacts_text'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击全选按钮'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/Teacher Contacts/checkbox_btn'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击确定按钮'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/sure_btn'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'选择联系人教师栏是否出现'
Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/teacher_text'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

//获取教师联系人文本,后面短信确认页的时候对比
teacher_contacts_text = Mobile.getText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/teacher_contacts_text'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'清空手机短信内容'
Mobile.clearText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_content_edittext'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

//获取系统时间
String time1 = CustomKeywords.'time.SystemTime.get_system_time'()

'设置手机短信内容:系统时间+测试短信'
Mobile.setText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_content_edittext'), 
    time1 + '测试短信', GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'选择短信类型'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_type_option_text'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Selection Box/sure_btn'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'选择是否需要回复'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/need_reply_option_text'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Selection Box/sure_btn'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'选择署名'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_signature_option_text'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Selection Box/sure_btn'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击发送'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_send_text'), 
    GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)

//Mobile.delay(5)
'点击立即发送'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/SMS Confirmation Box/send_immediately_text'), 
    0, FailureHandling.CONTINUE_ON_FAILURE)

'获取toast内容:发送成功'
assert sms_toast_success_text == Mobile.getText(findTestObject('Object Repository/Android/Pubilc Element/toast_element'), GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)



