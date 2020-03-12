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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.util.KeywordUtil
'点击发送短信tap栏'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/sending_sms_text'), 
    GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)



//t1=new Thread(new Runnable(){
//	@Override
//	public void run() {
//		text = Mobile.getText(findTestObject('Object Repository/Android/Pubilc Element/toast_element'),
//		GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE)
//		println "这是我获取的toast内容"+text
//	}})
//
//t1.start()
//
//Mobile.delay(2)


'点击发送'
Mobile.tap(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_send_text'), 
    GlobalVariable.G_short_timeout, FailureHandling.CONTINUE_ON_FAILURE)
//
//AppiumDriver driver = MobileDriverFactory.getDriver()
//def toast = driver.findElementByXPath("//android.widget.Toast[@text='亲,请选择接收人~']")
//println("Toast element: " + toast)
//if (toast == null) {
//	KeywordUtil.markFailed('ERROR: Toast object not found!')
//}


//assert sms_toast_no_contact == text