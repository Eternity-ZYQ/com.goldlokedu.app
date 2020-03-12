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

"点击班训模块修改按钮"
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_change_layout"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)


"查看顶部标题title是否为班训"
Mobile.verifyElementExist(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_title_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)


"获取当前时间"
String time=CustomKeywords.'time.SystemTime.get_system_time'()

"拼接班训内容:时间+测试班训"
String class_training_content=time+"测试班训"

"输入班训内容"
Mobile.setText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_edittext"), class_training_content, GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)

"点击保存"
Mobile.tap(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_save_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)



"返回班级圈页面,验证是否发布成功"
"获取班级圈页面班训的内容"
String content=Mobile.getText(findTestObject("Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/Class Circle/Class Training/class_training_content_text"), GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
"对比之前发布的班训是否一致"
Mobile.verifyMatch(class_training_content, content, false, FailureHandling.CONTINUE_ON_FAILURE)

















