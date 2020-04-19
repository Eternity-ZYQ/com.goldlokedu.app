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
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static org.assertj.core.api.Assertions.*
import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable


ResponseObject get_album_list_response=get_album_list(class_id,from,size)	

return get_album_list_response





//获取相册列表
def Object get_album_list(String class_id,int from,int size){
	'获取教师关联班级信息'
	ResponseObject get_album_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/album_list",[('class_id'):class_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	
	//def get_album_list_jsonResponse=get_jsonResponse(get_album_list_response)
	WS.comment('相册列表数据body：'+get_album_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(get_album_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		
		WS.containsString(get_album_list_response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return get_album_list_response
		
	}
	
	return
	
	
}


