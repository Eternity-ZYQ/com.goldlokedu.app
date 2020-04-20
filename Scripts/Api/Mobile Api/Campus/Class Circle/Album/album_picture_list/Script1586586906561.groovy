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
ResponseObject class_information_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/My/Individual/Teacher/teacher_related_class"), FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)


for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		WS.comment('class_id:'+class_id)
		
		ResponseObject get_album_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/album_list",[('class_id'):class_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)	
		def get_album_list_jsonResponse=get_jsonResponse(get_album_list_response)
		
		'判断是否有相册'
		if(get_album_list_jsonResponse.albums.size>0){
			WS.comment(class_name+'有相册')
			
			def album_name=get_album_list_jsonResponse.albums[0].name
			'获取相册图片列表'
			ResponseObject get_ablum_picture_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/ablum_picture_list",[('album_id'):get_album_list_jsonResponse.albums[0].album_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
			def get_ablum_picture_list_jsonResponse=get_jsonResponse(get_ablum_picture_list_response)
			
			if(get_ablum_picture_list_jsonResponse.pictures.size>0){
				
				for(int k:(0..get_ablum_picture_list_jsonResponse.pictures.size-1)){
					
					'加载相册图片'
					WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/dowmload_album_picture",[('picture_id'):get_ablum_picture_list_jsonResponse.pictures[k].picture_id]), FailureHandling.CONTINUE_ON_FAILURE)
					
				}
				
			}else{
			
				WS.comment(class_name+'的'+album_name+'相册没有图片')
			}
			
		}else{
		
			WS.comment(class_name+'没有相册')
		
		}
		
		
	}
	
	
}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}










