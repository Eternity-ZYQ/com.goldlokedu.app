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
ResponseObject class_information_response=WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/My/Individual/Teacher/teacher_related_class"), null, FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)

for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		WS.comment('class_id:'+class_id)
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		
		'获取班级相册列表'
		ResponseObject get_album_list_response=WS.callTestCase(findTestCase('Test Cases/Api/Mobile Api/Campus/Class Circle/Album/album_list'), [('class_id'):class_id,('from'):from,('size'):size], FailureHandling.CONTINUE_ON_FAILURE)
		def get_album_list_jsonResponse=get_jsonResponse(get_album_list_response)
		
		if(get_album_list_jsonResponse.albums.size>0){
			
			'获取教师是否为班主任信息'
			ResponseObject judge_adviser_response=WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/My/Individual/Teacher/judge_adviser"), [('class_id'):class_id], FailureHandling.CONTINUE_ON_FAILURE)
			def judge_adviser_jsonResponse=get_jsonResponse(judge_adviser_response)
			
			if(judge_adviser_jsonResponse.data.adviser){
				
				WS.comment('是'+class_name+'的班主任')
				def album_name=CustomKeywords.'time.SystemTime.get_system_time'()+'编辑的相册'
				edit_album(album_name,class_id,get_album_list_jsonResponse.albums[0].album_id)
				
			}else{
			
				WS.comment('不是'+class_name+'的班主任')
					
			}
			
		}
		
	}
		
}









//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}






//编辑相册名称
def void edit_album(String album_name,String class_id,String album_id){
	'发送添加相册接口'
	ResponseObject edit_album_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/edit_album_name",[('album_name'):album_name,('class_id'):class_id,('album_id'):album_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def edit_album_jsonResponse=get_jsonResponse(edit_album_response)
	WS.comment('添加相册返回数据body：'+edit_album_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(edit_album_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(edit_album_response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
	}
	
	
}





