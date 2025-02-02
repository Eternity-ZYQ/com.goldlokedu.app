import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.SimpleDateFormat as SimpleDateFormat
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
import com.katalon.plugin.keyword.calendar.SetDateCalendarKeyword as SetDateCalendarKeyword

//"获取当前时间"
//time=getDate()
//
//"合成发送文本"
//conent=time + '测试短信'

"获取发送内容"
TestData data=findTestData("null")
 content=data.getValue("sms_content", 1)


'设置内容'
Mobile.setText(findTestObject('Object Repository/Android/Bottom Bavigation/Campus/Interactive Management/SMS/Sending SMS/sms_content_edittext'), 
    content, GlobalVariable.G_Timeout, FailureHandling.CONTINUE_ON_FAILURE) 

//获取当前系统时间
String getDate() {
    Date date = new Date()

    SimpleDateFormat df = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss')

    String time = df.format(date)

    return time
}

