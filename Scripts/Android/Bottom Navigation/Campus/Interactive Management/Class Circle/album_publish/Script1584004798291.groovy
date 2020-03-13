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

"判断是创建按钮还是更多按钮"
if(Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_create_text"), GlobalVariable.G_short_timeout, FailureHandling.OPTIONAL)){
	is_create_btn()
		
}else if(Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_more_text"), GlobalVariable.G_short_timeout, FailureHandling.OPTIONAL)){
	is_more_btn()

}
















//是"创建"按钮,测试用例
private void is_create_btn(){
	"点击创建按钮"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_create_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"班级相册标题"
	Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_title_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"点击新建相册"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"获取当天时间"
	 def day=CustomKeywords.'time.SystemTime.get_day_time'()
	 
	"输入新相册名称:day"
	Mobile.setText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_add_name_edittext"), day, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"点击确定"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_add_sure_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"验证新建的相册名称存在"
	def name=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_name_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	Mobile.verifyMatch(name, day, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	
	
}



//是"更多"按钮测试用例
 private void is_more_btn(){
	 "点击更多按钮"
	 Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_more_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	 
	 "班级相册标题"
	Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_title_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"点击新建相册"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_add_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"获取当天时间"
	 def day=CustomKeywords.'time.SystemTime.get_day_time'()
	 
	"输入新相册名称:day"
	Mobile.setText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_add_name_edittext"), day, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"点击确定"
	Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_add_sure_btn"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	
	"验证新建的相册名称存在"
	def name=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Album/album_in_item_name_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
	Mobile.verifyMatch(name, day, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	 
	 
	 
	 
	 
 }


