package public_action
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.Date

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectBuilder
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.touch.TouchActions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.mobile.keyword.internal.MobileSearchEngine
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.TestObjectXpath
import com.kms.katalon.core.mobile.helper.MobileCommonHelper
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class findMobileElement {
	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@Keyword
	def isElementPresent_Mobile(TestObject to, int timeout){
		try {
			KeywordUtil.logInfo("Finding element with id:" + to.getObjectId())

			WebElement element = MobileElementCommonHelper.findElement(to, timeout)

			if (element != null) {
				KeywordUtil.markPassed("Object " + to.getObjectId() + " is present")
			}
			return true
		} catch (Exception e) {
			KeywordUtil.markFailed("Object " + to.getObjectId() + " is not present")
		}
		return false;

	}

	/**
	 * Get mobile driver for current session
	 * @return mobile driver for current session
	 */
	@Keyword
	def WebDriver getCurrentSessionMobileDriver() {
		return MobileDriverFactory.getDriver();
	}
	

	/**
	 * 获取移动端元素通过xpath
	 * @return 返回找到的元素
	 */
	@Keyword
	def WebElement byXpath(String xpath){
		try {
			KeywordUtil.logInfo("Finding element with xpath:" + xpath)
			AppiumDriver driver = MobileDriverFactory.getDriver()
			WebElement element = driver.findElementByXPath(xpath)

			if (element != null) {
				KeywordUtil.markPassed("Finding element with xpath: " + xpath + " is present")
				
				return element
			}
			
		} catch (Exception e) {
			KeywordUtil.markFailed("Finding element with xpath: " + xpath + " is not present")		
			return
		}
		
	}
	
	
	/**
	 * 获取移动端元素通过xpath
	 * @return 返回找到的元素,以TestObject形式
	 */
	@Keyword
	def TestObject byXpathBackTestObject(String xpath){
		try {
			KeywordUtil.logInfo("Finding element with xpath:" + xpath)
			AppiumDriver driver = MobileDriverFactory.getDriver()
			WebElement element = driver.findElementByXPath(xpath)

			if (element != null) {
				KeywordUtil.markPassed("Finding element with xpath: " + xpath + " is present")
				TestObject 
				return element
			}
			
		} catch (Exception e) {
			KeywordUtil.markFailed("Finding element with xpath: " + xpath + " is not present")
			return
		}
		
	}
	
	

	
	
}