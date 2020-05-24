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

"前置条件:处于首页，且弹窗未关闭"
Mobile.callTestCase(findTestCase("Test Cases/Android/Login Before Module/Login/Teacher Success/login_success"), null, FailureHandling.CONTINUE_ON_FAILURE)

'判断停课不停学弹窗出现'
boolean is_exist=Mobile.verifyElementExist(findTestObject("Object Repository/Android/No Class But Study/no_class_but_study_layout"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

'是否存在'
if(is_exist){
	'点击立即查看按钮'
	Mobile.tap(findTestObject("Object Repository/Android/No Class But Study/look_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'点击后，弹窗消失'
	Mobile.verifyElementNotExist(findTestObject("Object Repository/Android/No Class But Study/no_class_but_study_layout"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'进入弹窗详情页，查看详情页布局存在'
	Mobile.verifyElementExist(findTestObject("Object Repository/Android/No Class But Study/image_background"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'点击左上叫返回图标'
	Mobile.tap(findTestObject("Object Repository/Android/No Class But Study/back_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'弹窗消失'
	Mobile.verifyElementNotExist(findTestObject("Object Repository/Android/No Class But Study/no_class_but_study_layout"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	'处于首页，有小铃铛图标'
	Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/small_bell_image"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
}