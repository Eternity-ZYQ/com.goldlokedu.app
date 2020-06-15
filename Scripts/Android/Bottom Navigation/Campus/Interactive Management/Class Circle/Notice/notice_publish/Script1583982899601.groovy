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


//"判断是发布按钮还是更多按钮"
if(Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_pubilsh"), GlobalVariable.G_short_timeout, FailureHandling.OPTIONAL)){
	//是"发布"按钮
	is_notice_pubile()
	
	
	
	
}else if(Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_more_text"), GlobalVariable.G_short_timeout, FailureHandling.OPTIONAL)){
	//是"更多"按钮
	is_notice_more()



}





//"发布"按钮的测用例
private void is_notice_pubile(){
	"点击发布按钮"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_pubilsh"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	
	"组成公告文本:系统时间+测试公告"
	def notice_content=CustomKeywords.'time.SystemTime.get_system_time'()+"测试公告"
	
	"输入公告文本"
	Mobile.setText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_content_edittext"), notice_content, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"点击保存"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_pubilsh_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"验证返回班级圈页面中,公告展示内容是否一致"
	def notice_get_content=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_content_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	Mobile.verifyMatch(notice_content, notice_get_content, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	
	
	
	
	
	
}

//"更多"按钮的测试用例
private void is_notice_more(){
	"点击更多按钮"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_more_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"班级标题是否存在"
	Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_class_name_title_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"点击发布通知按钮"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_publish_iamge"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	
	"组成公告文本:系统时间+测试公告"
	def notice_content=CustomKeywords.'time.SystemTime.get_system_time'()+"测试公告"
	
	"输入公告文本"
	Mobile.setText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_content_edittext"), notice_content, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"点击保存"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_pubilsh_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"验证返回班级圈页面中,公告展示内容是否一致"
	def notice_get_content=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Notice/notice_in_content_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	Mobile.verifyMatch(notice_content, notice_get_content, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	
	
	
}




