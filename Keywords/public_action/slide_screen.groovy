package public_action

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class slide_screen {


	//屏幕向左滑动到第二页
	@Keyword
	public void left_slide_screen(){
		"获取屏幕高度"
		def y=Mobile.getDeviceHeight()


		"获取屏幕宽度"
		def x=Mobile.getDeviceWidth()


		"左滑到第二页"
		Mobile.swipe(x-80,  900, 0, 0, FailureHandling.CONTINUE_ON_FAILURE)


	}


	//向上滑动屏幕
	@Keyword
	public void wipe_up(){
		"获取设备高度"
		def y=Mobile.getDeviceHeight()

		"获取设备宽度"
		def x=Mobile.getDeviceWidth()

		"向上滑动屏幕"
		Mobile.swipe(new BigDecimal(x/2).intValue(), new BigDecimal(y*2/3).intValue(), new BigDecimal(x/2).intValue(), new BigDecimal(y/3).intValue())


	}




}
