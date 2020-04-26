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


'获取我参与的投票列表'
def search_my_join_vote_list_jsonResponse=search_my_join_vote_list(from,size)

'有数据'
if(search_my_join_vote_list_jsonResponse.votes.size>0){
	WS.comment('我参与的投票列表有数据')
	
	for(int x:(0..search_my_join_vote_list_jsonResponse.votes.size-1)){
		
		'是否已经投过票和是否已经过期'
		if(search_my_join_vote_list_jsonResponse.votes[x].expired|search_my_join_vote_list_jsonResponse.votes[x].voted){
			
			'进入投票结果详情页面'	
			def vote_detail_jsonResponse=vote_detail(search_my_join_vote_list_jsonResponse.votes[x].vote_id)
			
			for(int y:(0..vote_detail_jsonResponse.vote_options.size-1)){
				if(vote_detail_jsonResponse.vote_options[y].content.picture_id!=null){
					WS.comment('加载图片中...')
					dowmload_picture(vote_detail_jsonResponse.vote_options[y].content.picture_id)
					
				}else{
					WS.comment('没有图片,不进行加载...')
				
				}
				
				
			}
			
		}else{
			WS.comment('该条投票没有过期,也没有进行投票,不能进入结果也页')
		
		}
			
	}
	
	
}
















//获取返回体json解析
def Object get_jsonResponse(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
	
}




//查询我参与的投票列表
def Object search_my_join_vote_list(int from,int size){
	'发送我参与的投票列表接口'
	ResponseObject search_my_join_vote_list_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Vote/search_my_join_vote_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def search_my_join_vote_list_jsonResponse=get_jsonResponse(search_my_join_vote_list_response)
	
	if(WS.verifyResponseStatusCode(search_my_join_vote_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
			
		return search_my_join_vote_list_jsonResponse	
	}
	
		return
	
	
	
}



//查询投票结果列表
def Object vote_detail(String vote_id){
	'发送投票结果接口'
	ResponseObject vote_detail_response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Vote/voted_details",[('vote_id'):vote_id]), FailureHandling.CONTINUE_ON_FAILURE)
	def vote_detail_jsonResponse=get_jsonResponse(vote_detail_response)
	
	if(WS.verifyResponseStatusCode(vote_detail_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
			
		return vote_detail_jsonResponse
	}
	
	return
}





//加载投票图片
def void dowmload_picture(String picture_id){
	'发送获取投票图片接口'
	WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Vote/download_vote_picture",[('picture_id'):picture_id]), FailureHandling.CONTINUE_ON_FAILURE)


}




