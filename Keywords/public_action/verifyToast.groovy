package public_action

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver

public class verifyToast {

	//寻找任意toast,并放回对象
	@Keyword
	public WebElement findToastElement(){

		try {
			AppiumDriver driver = MobileDriverFactory.getDriver()
			WebElement toast = driver.findElementByXPath("//android.widget.Toast")

			println("Toast element: " + toast.getText())
			if (toast != null) {
				KeywordUtil.markPassed("Toast "  + " is present")
			}
			return toast
		} catch (Exception e) {

			KeywordUtil.markFailed("Toast " + " is not present")

		}

		return

	}

	//通过给的toast内容验证相应Toast对象是否存在
	@Keyword
	public Boolean VerifyToastElementExistByText(String text){


		try {
			AppiumDriver driver = MobileDriverFactory.getDriver()
			def Xpath="//android.widget.Toast"+"[@text='"+text+"']"
			WebElement toast = driver.findElementByXPath(Xpath)
			println("Toast element: " + toast.getText())
			if (toast != null) {
				KeywordUtil.markPassed("Toast "  + " is present")
				return true
			}

		} catch (Exception e) {

			KeywordUtil.markFailed("Toast " + " is not present")
			return false
		}

		return

	}

}
