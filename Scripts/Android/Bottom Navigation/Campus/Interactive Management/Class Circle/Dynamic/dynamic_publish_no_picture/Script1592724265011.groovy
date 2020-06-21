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

'前提条件:进入班级圈模块' //返回管理和教授的班级数据
def jsonResponse=Mobile.callTestCase(findTestCase("Test Cases/Android/Bottom Navigation/Campus/Interactive Management/Class Circle/to_class_crcle"), null, FailureHandling.CONTINUE_ON_FAILURE)

if(jsonResponse.data.size>0){
	Mobile.comment('教师有管理和教授的班级,可进入班级圈')
	'获取默认tab的班级名称'
	class_name=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/class_tab_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击更多'
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_more_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击发布按钮'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_in_publish_layout'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'验证发布动态页面titile'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_publish_page_title'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	def hint='给'+class_name+'说点什么......'
	'验证动态编辑框hint内容'
	Mobile.verifyElementExist(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_publish_page_edittext', [('text'):hint]), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'生成发布动态的内容'
	String content=CustomKeywords.'time.SystemTime.get_system_time'()+'Android App发布的纯文本动态'
	'输入动态内容'
	Mobile.setText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_publish_page_edittext', [('text'):hint]), content, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	'点击发布'
	Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Dynamic/dynamic_publish_page_publish_btn'), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	def xpath='//android.widget.TextView[@text="'+content+'"]'
	'在班级动态中找到刚刚发布的内容'
	CustomKeywords.'public_action.findMobileElement.byXpath'(xpath)
}