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

'前置条件:进入手机短信页面'
Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/SMS/to_sms_page"), null, FailureHandling.CONTINUE_ON_FAILURE)

'点击发送短信tap栏'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/sending_sms_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击通讯录+按钮进入通讯录'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/add_contacts_image'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击教师通讯录tab栏'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/teacher_contacts_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击全选按钮'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/Contacts/checkbox_btn'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击学生讯录tab栏'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/student_contacts_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击全选按钮'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/Contacts/checkbox_btn'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击确定按钮'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Contacts Select Page/sure_btn'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

teacher_contacts_text = Mobile.getText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/teacher_contacts_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.comment('手机短信页面选择联系人,教师:'+teacher_contacts_text)

'选择联系人学生栏是否出现'
Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/student_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

//获取学生联系人文本,后面短信确认页的时候对比
student_contacts_text = Mobile.getText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/student_contacts_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.comment('手机短信页面选择联系人,学生:'+student_contacts_text)

'清空手机短信内容'
Mobile.clearText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_content_edittext'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

//获取系统时间
String time1 = CustomKeywords.'time.SystemTime.get_system_time'()

'设置手机短信内容:系统时间+测试短信'
Mobile.setText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_content_edittext'),
	time1 + '测试定时短信', GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'选择短信类型'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_type_option_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Selection Box/sure_btn'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'选择是否需要回复'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/need_reply_option_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Selection Box/sure_btn'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'选择署名'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_signature_option_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击确认'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Selection Box/sure_btn'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'获取定时短信按钮checked属性'
def is_checked=Mobile.getAttribute(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/timed_send_tooglebtn'),'checked', GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'没开启定时发送就开启'
if(Mobile.verifyEqual(is_checked, 'false', FailureHandling.OPTIONAL)){
	
	'点击定时短信按钮'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/timed_send_tooglebtn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
}

'点击发送时间'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/send_time_aptions_text'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

def s_width=Mobile.getAttribute(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Select Time/year'), 'width', GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
def s_height=Mobile.getAttribute(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Select Time/year'), 'height', GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
def s_x=Mobile.getAttribute(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Select Time/year'), 'x', GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
def s_y=Mobile.getAttribute(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Select Time/year'), 'y', GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
def width=Integer.parseInt(s_width)
def height=Integer.parseInt(s_height)
def x=Integer.parseInt(s_x)
def y=Integer.parseInt(s_y)


'滑动年份'
Mobile.swipe((int)(x+width/2), (int)(y+height-10), (int)(x+width/2), (int)(y+height/5), FailureHandling.CONTINUE_ON_FAILURE)

'点击确定'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/Select Time/sure_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)


'点击发送'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_send_text'),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'点击立即发送'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/SMS Confirmation Box/send_immediately_text',[('send_btn_text'):'定时发送']),
	GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'验证发送成功'
CustomKeywords.'public_action.verifyToast.VerifyToastElementExistByText'('发送成功')




