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

'获取教师关联班级信息'
def class_information_jsonResponse=get_class_information()


for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		WS.comment('class_id:'+class_id)
		def get_album_list_jsonResponse=get_album_list(class_id,0,10)	
		
		'班主任上传图片'	
		if(judge_adviser(class_id)){
			'有相册'
			if(get_album_list_jsonResponse.albums.size>0){
				'上传照片'
				def upload_album_picture_jsonResponse=upload_album_picture(class_id)
				
				'关联到班级相册'
				album_picture_retaled_class(class_id,upload_album_picture_jsonResponse.picture_id,upload_album_picture_jsonResponse.name,get_album_list_jsonResponse.albums[0].album_id)
				
			}else{
				
				WS.comment(class_name+'没有相册')
			
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



//获取相册列表
def Object get_album_list(String class_id,int from,int size){
	'获取教师关联班级信息'
	ResponseObject get_album_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/album_list",[('class_id'):class_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def get_album_list_jsonResponse=get_jsonResponse(get_album_list_response)
	WS.comment('相册列表数据body：'+get_album_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(get_album_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		
		WS.containsString(get_album_list_response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return get_album_list_jsonResponse
		
	}
	
	return
	
	
}


//上传相册图片
def Object upload_album_picture(String class_id){
	'获取教师关联班级信息'
	ResponseObject upload_album_picture_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/upload_album_picture",[('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def upload_album_picture_jsonResponse=get_jsonResponse(upload_album_picture_response)
	WS.comment('相册列表数据body：'+upload_album_picture_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(upload_album_picture_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		
		WS.containsString(upload_album_picture_response, 'picture_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return upload_album_picture_jsonResponse
		
	}
	
	return
	
	
	
}



//上次的图片关联到班级
def void album_picture_retaled_class(String class_id,String picture_id,String name,String album_id){
	'获取教师关联班级信息'
	ResponseObject album_picture_retaled_class_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/album_picture_retaled_class",[('class_id'):class_id,('picture_id'):picture_id,('name'):name,('album_id'):album_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	//def album_picture_retaled_class_jsonResponse=get_jsonResponse(album_picture_retaled_class_response)
	WS.comment('相册列表数据body：'+album_picture_retaled_class_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(album_picture_retaled_class_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		
		WS.verifyElementPropertyValue(album_picture_retaled_class_response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
		
		
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














