import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.logging.Logger

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
def search_my_join_vote_list_jsonResponse=search_my_join_vote_list(0,10)

'有数据'
if(search_my_join_vote_list_jsonResponse.votes.size>0){
	WS.comment('我参与的投票列表有数据')
	
	for(int x:(0..search_my_join_vote_list_jsonResponse.votes.size-1)){
		
		'是否已经投过票和是否已经过期'
		if(search_my_join_vote_list_jsonResponse.votes[x].expired|search_my_join_vote_list_jsonResponse.votes[x].voted){
			
			WS.comment('该条投票已过期或您已经投票,不能进入投票详情页面投票')
			
		}else{
			'进入投票结果详情页面'	
			def voting_detail_jsonResponse=voting_detail(search_my_join_vote_list_jsonResponse.votes[x].vote_id)
			
			for(int y:(0..voting_detail_jsonResponse.vote_options.size-1)){
				if(voting_detail_jsonResponse.vote_options[y].content.picture_id!=null){
					WS.comment('加载图片中...')
					dowmload_picture(voting_detail_jsonResponse.vote_options[y].content.picture_id)
					
				}else{
					WS.comment('没有图片,不进行加载...')
				
				}
			}
			
			def voted_options=get_voted_options(voting_detail_jsonResponse)
			voting(voting_detail_jsonResponse.vote_id,voted_options)
			
			
			
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
	ResponseObject search_my_join_vote_list_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Vote/search_my_join_vote_list",[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def search_my_join_vote_list_jsonResponse=get_jsonResponse(search_my_join_vote_list_response)
	WS.comment('我参与的投票列表数据body:'+search_my_join_vote_list_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(search_my_join_vote_list_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(search_my_join_vote_list_response, 'total', false,FailureHandling.CONTINUE_ON_FAILURE)
		
		return search_my_join_vote_list_jsonResponse
		
	}
	
	return
	
	
	
}



//查询投票结果列表
def Object voting_detail(String vote_id){
	'发送投票结果接口'
	ResponseObject voting_detail_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Vote/voting_details",[('vote_id'):vote_id]), FailureHandling.CONTINUE_ON_FAILURE)
	def voting_detail_jsonResponse=get_jsonResponse(voting_detail_response)
	WS.comment('我参与的投票列表数据body:'+voting_detail_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(voting_detail_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.containsString(voting_detail_response, 'created', false,FailureHandling.CONTINUE_ON_FAILURE)
		
		return voting_detail_jsonResponse
	}
	
	return
}




//进行投票
def void voting(String vote_id,String voted_options){
	'发送投票接口'
	ResponseObject voting_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Vote/voting",[('vote_id'):vote_id,('voted_options'):voted_options]), FailureHandling.CONTINUE_ON_FAILURE)
	def voting_jsonResponse=get_jsonResponse(voting_response)
	WS.comment('进行投票的数据body:'+voting_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(voting_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.verifyElementPropertyValue(voting_response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
		
	}
	
	
	
	
}


//短信voted_options字段判断及组成
def String get_voted_options(Object jsonResponse){
	List list=new ArrayList()
	int voted_options_size=jsonResponse.vote_options.size
	int num=(int)(Math.random()*1000)%jsonResponse.max_option_amount
	switch (jsonResponse.max_option_amount) {
		case 1:
				int num1=(int)(Math.random()*1000)%voted_options_size
				list.add(jsonResponse.vote_options[num1].id)
				break
		case 2:
				int num1=(int)(Math.random()*1000)%voted_options_size
				list.add(jsonResponse.vote_options[num1].id)
				break	
		default: 
				for(int x:(0..num)){
					int num1=(int)(Math.random()*1000)%voted_options_size
					while(list.contains(jsonResponse.vote_options[num1].id)){					
					
					WS.comment('已经加入过了这个投票选项,重新选一个')		
					num1=(int)(Math.random()*1000)%voted_options_size		
								
					}					
					list.add(jsonResponse.vote_options[num1].id)
				}
				break
	}
	
	
	
	return list.toString()
	
	
}



//加载投票图片
def void dowmload_picture(String picture_id){
	'发送获取投票图片接口'
	ResponseObject dowmload_picture_response=WS.sendRequest(findTestObject("Object Repository/Api/Mobile Api/Campus/Vote/download_vote_picture",[('picture_id'):picture_id]), FailureHandling.CONTINUE_ON_FAILURE)
	//def dowmload_picture_jsonResponse=get_jsonResponse(vote_detail_response)
	//WS.comment('我参与的投票列表数据body:'+dowmload_picture_response.getResponseText())
	
	if(WS.verifyResponseStatusCode(dowmload_picture_response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		WS.comment('图片加载成功')
		
		
	}
	

}





