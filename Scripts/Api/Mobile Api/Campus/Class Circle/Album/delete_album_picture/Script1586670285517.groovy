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

import java.lang.reflect.Array

import groovy.json.JsonSlurper


'获取教师关联班级信息'
ResponseObject class_information_response=WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/My/Individual/Teacher/teacher_related_class"), null, FailureHandling.CONTINUE_ON_FAILURE)
def class_information_jsonResponse=get_jsonResponse(class_information_response)


for(int x:(0..class_information_jsonResponse.data.size-1)){
	
	for(int y:(0..class_information_jsonResponse.data[x].klass.size-1)){
		
		def class_id=class_information_jsonResponse.data[x].klass[y].klass_id
		def class_name=class_information_jsonResponse.data[x].klass[y].klass_full_name
		WS.comment('class_id:'+class_id)
		ResponseObject get_album_list_response=WS.callTestCase(findTestCase('Test Cases/Api/Mobile Api/Campus/Class Circle/Album/album_list'), [('class_id'):class_id,('from'):from,('size'):size], FailureHandling.CONTINUE_ON_FAILURE)
		def get_album_list_jsonResponse=get_jsonResponse(get_album_list_response)
		
		'获取教师是否为班主任信息'
		ResponseObject judge_adviser_response=WS.callTestCase(findTestCase("Test Cases/Api/Mobile Api/My/Individual/Teacher/judge_adviser"), [('class_id'):class_id], FailureHandling.CONTINUE_ON_FAILURE)
		def judge_adviser_jsonResponse=get_jsonResponse(judge_adviser_response)
		
		'班主删除图片'
		if(judge_adviser_jsonResponse.data.adviser){
			'判断是否有相册'
			if(get_album_list_jsonResponse.albums.size>0){
				WS.comment(class_name+'有相册')
				
				def album_name=get_album_list_jsonResponse.albums[0].name
				'获取相册图片列表'
				def get_ablum_picture_list_jsonResponse=get_ablum_picture_list(get_album_list_jsonResponse.albums[0].album_id,0,get_album_list_jsonResponse.albums[0].size)
				
				if(get_ablum_picture_list_jsonResponse.pictures.size>0){
					
					for(int k:(0..get_ablum_picture_list_jsonResponse.pictures.size-1)){
						
						List album_pictures_list=new ArrayList<String>()
						'保存要删除的图片id'
						album_pictures_list.add('"'+get_ablum_picture_list_jsonResponse.pictures[k].picture_id+'"')
						delete_picture(album_pictures_list.toString(),class_id)
						
					}
					
				}else{
				
					WS.comment(class_name+'的'+album_name+'相册没有图片')
				}
				
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






//进入相册查看相册图片列表
def Object get_ablum_picture_list(String album_id,int from,int size){
	
	'获取教师关联班级信息'
	ResponseObject get_ablum_picture_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/ablum_picture_list",[('album_id'):album_id,('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	
	def get_ablum_picture_list_jsonResponse=get_jsonResponse(get_ablum_picture_list_response)
	WS.comment('相册图片列表数据body：'+get_ablum_picture_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(get_ablum_picture_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(get_ablum_picture_list_response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		return get_ablum_picture_list_jsonResponse
		
	}
	
	return
		
}


//删除图片
def void delete_picture(String album_pictures,String class_id){
	'删除相册图片'
	ResponseObject delete_picture_pictureresponse=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Album/delete_picture",[('album_pictures'):album_pictures,('class_id'):class_id]), FailureHandling.CONTINUE_ON_FAILURE)
	
	if(WS.verifyResponseStatusCode(delete_picture_pictureresponse, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(delete_picture_pictureresponse, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
	}
	
	
}








