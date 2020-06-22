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

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

'获取个人动态列表数据'
ResponseObject search_personal_dynamic_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_personal_dynamic_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
def search_personal_dynamic_list_jsonResponse=get_jsonResponse(search_personal_dynamic_list_response)


if(search_personal_dynamic_list_jsonResponse.data.size>0){
	WS.comment('我的动态有数据')
	
	for(int k:(0..search_personal_dynamic_list_jsonResponse.data.size-1)){
			
		if(search_personal_dynamic_list_jsonResponse.data[k].pictures!=null&&search_personal_dynamic_list_jsonResponse.data[k].pictures.data.size>0){
			WS.comment('第'+(k+1)+'条动态有图片,准备加载图片...')
			
			for(int i:(0..search_personal_dynamic_list_jsonResponse.data[k].pictures.data.size-1)){
				
				WS.comment('第'+(k+1)+'条动态的第'+(i+1)+'张图片加载中...')
				
				'发送下载动态图片接口'
				WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/dowmload_picture",[('picture_id'):search_personal_dynamic_list_jsonResponse.data[k].pictures.data[i].attachment_id]), FailureHandling.CONTINUE_ON_FAILURE)
				
				
			}		
		}else{
		
			WS.comment('第'+(k+1)+'条动态没有图片,不需要加载图')
		}		
	}
		
}



'获取学年接口数据'
ResponseObject get_years_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/year"), FailureHandling.CONTINUE_ON_FAILURE)
'获取学年'
def get_yearsjsonResponse=get_jsonResponse(get_years_response)

def size=get_yearsjsonResponse.data.size
if(size>0){
	WS.comment('存在'+size+'个学年')
	
	for(int x:(0..size-1)){
		
		'获取动态个人列表数据--筛选学年'
		ResponseObject search_personal_dynamic_list_filter_year_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/search_personal_dynamic_list_filter_year",[('from'):from,('size'):size,('school_year'):get_yearsjsonResponse.data[x].code]), FailureHandling.CONTINUE_ON_FAILURE)
		def search_personal_dynamic_list_filter_yearjsonResponse=get_jsonResponse(search_personal_dynamic_list_filter_year_response)
		
		if(search_personal_dynamic_list_filter_yearjsonResponse.data.size>0){
			WS.comment('我的动态有数据')
			
			for(int k:(0..search_personal_dynamic_list_filter_yearjsonResponse.data.size-1)){
					
				if(search_personal_dynamic_list_filter_yearjsonResponse.data[k].pictures!=null&&search_personal_dynamic_list_filter_yearjsonResponse.data[k].pictures.data.size>0){
					WS.comment('第'+(k+1)+'条动态有图片,准备加载图片...')
					
					for(int i:(0..search_personal_dynamic_list_filter_yearjsonResponse.data[k].pictures.data.size-1)){
						
						WS.comment('第'+(k+1)+'条动态的第'+(i+1)+'张图片加载中...')
						
						'发送下载动态图片接口'
						WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Class Circle/Class Dynamic/dowmload_picture",[('picture_id'):search_personal_dynamic_list_filter_yearjsonResponse.data[k].pictures.data[i].attachment_id]), FailureHandling.CONTINUE_ON_FAILURE)
						
						
					}
				}else{
				
					WS.comment('第'+(k+1)+'条动态没有图片,不需要加载图')
				}
			}
				
		}
		
		
	}	
}else{
	WS.comment('没有学年数据')

}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}










