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
import internal.GlobalVariable as GlobalVariable
import static org.assertj.core.api.Assertions.*
import groovy.json.JsonSlurper


'获取教师关联班级信息'
def class_information_jsonResponse=get_class_information()

for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		WS.comment('class_id:'+class_id)
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		if(judge_adviser(class_id)){
			
			WS.comment('是'+class_name+'的班主任')
			def album_name=CustomKeywords.'time.SystemTime.get_system_time'()+'添加的相册'
			add_album(album_name,class_id)
			
		}else{
		
			WS.comment('不是'+class_name+'的班主任')
				
		}
	}
		
}


























//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}



//获取教师关联班级信息
def Object get_class_information(){
	'获取教师关联班级信息'
	ResponseObject class_information_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/teacher_related_class"), FailureHandling.CONTINUE_ON_FAILURE)
	
	def class_information_jsonResponse=get_jsonResponse(class_information_response)
	WS.comment('班级信息'+class_information_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(class_information_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(class_information_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyElementPropertyValue(class_information_response, 'message', '操作成功', FailureHandling.CONTINUE_ON_FAILURE)
		
		return class_information_jsonResponse
		
	}
	
	return
	
}






//添加相册
def void add_album(String album_name,String class_id){
	'发送添加相册接口'
	ResponseObject add_album_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/add_album",[('album_name'):album_name,('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def add_album_jsonResponse=get_jsonResponse(add_album_response)
	WS.comment('添加相册返回数据body：'+add_album_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(add_album_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(add_album_response, 'album_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		
	}
	
	
}




//判断是否是班主任
def boolean judge_adviser(String class_id){
	'发送获取教师是否为班主任接口数据'
	ResponseObject judge_adviser_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/judge_adviser",[('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def judge_adviser_jsonResponse=get_jsonResponse(judge_adviser_response)
	WS.comment('是否为班主任返回body:'+judge_adviser_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(judge_adviser_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(judge_adviser_response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyElementPropertyValue(judge_adviser_response, 'message', '操作成功', FailureHandling.CONTINUE_ON_FAILURE)
		
		return judge_adviser_jsonResponse.data.adviser
		
	}
	
	return
	
}

