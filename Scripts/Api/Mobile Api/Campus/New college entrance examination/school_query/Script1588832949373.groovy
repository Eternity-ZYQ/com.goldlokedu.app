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

'获取学院代码'
def school_code=get_school_code()

'进入学校查,并获取随机一个专业名称'
def major_name=send_and_verify_school_query(from,size,school_code)

'搜索专业'
school_query_search(from,size,school_code,major_name)

'获取需要筛选的层次'
def level=get_level()

'筛选层次'
school_query_filter_level(from,size,school_code,level)

'获取需要筛选的首选科目'
def main_subject=get_main_subject()

'筛选首选科目'
school_query_filter_main_subject(from,size,school_code,main_subject)

'获取再选科目筛选项'
def minor_subject=get_minor_subject()

'筛选再选科目'
school_query_filter_minor_subject(from,size,school_code,minor_subject)

def void school_query_filter_minor_subject(int from,int size,int school_code,String minor_subject){
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/school_query_fliter_minor_subjuct'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size,('school_code_or_name'):school_code,('minor_subject'):minor_subject]), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	if(jsonResponse.data.size>0){
		WS.comment('有专业数据')
		for(int x:(0..jsonResponse.data.size-1)){
			def actual_minor_subject=jsonResponse.data[x].minor_subject_constraint
			def actual_school_code=jsonResponse.data[x].school_code
			WS.comment('真实值:'+actual_school_code+',期望值:'+school_code)
			WS.comment('真实值:'+actual_minor_subject+',期望值:'+minor_subject)
			WS.verifyEqual(actual_school_code, school_code, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
	}else{
		WS.comment('没有专业数据')
	
	}
}

def void school_query_filter_main_subject(int from,int size,int school_code,String main_subject){
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/school_query_filter_main_subjuct'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size,('school_code_or_name'):school_code,('main_subject'):main_subject]), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	if(jsonResponse.data.size>0){
		WS.comment('有专业数据')
		for(int x:(0..jsonResponse.data.size-1)){
			def actual_main_subject=jsonResponse.data[x].main_subject_constraint
			def actual_school_code=jsonResponse.data[x].school_code
			WS.comment('真实值:'+actual_school_code+',期望值:'+school_code)
			WS.comment('真实值:'+actual_main_subject+',期望值:'+main_subject)
			WS.verifyEqual(actual_school_code, school_code, FailureHandling.CONTINUE_ON_FAILURE)
			WS.verifyMatch(actual_main_subject, '.*'+main_subject+'.*',true, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
	}else{
		WS.comment('没有专业数据')
	
	}
}

def void school_query_filter_level(int from,int size,int school_code,String level){
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/school_query_filter_level'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size,('school_code_or_name'):school_code,('degree'):level]), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	if(jsonResponse.data.size>0){
		WS.comment('有专业数据')
		for(int x:(0..jsonResponse.data.size-1)){
			def actual_level=jsonResponse.data[x].degree
			def actual_school_code=jsonResponse.data[x].school_code
			WS.comment('真实值:'+actual_school_code+',期望值:'+school_code)
			WS.comment('真实值:'+actual_level+',期望值:'+level)
			WS.verifyEqual(actual_school_code, school_code, FailureHandling.CONTINUE_ON_FAILURE)
			WS.verifyEqual(actual_level, level, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
	}else{
		WS.comment('没有专业数据')
	
	}
}


def String get_minor_subject(){
	def array_minor_subject=['none','yes']  //none代表未定(不提科目要求);yes代表已定
	def array_yes_next=['化学','生物','政治','地理']
	def minor=''
	def num=(int)(Math.random()*1000)%array_minor_subject.size() //是否已定随机数
	def str=array_minor_subject[num]
	def num1=2   //已选最多选2个
	
	if(WS.verifyEqual(str, 'none', FailureHandling.OPTIONAL)){
		WS.comment('再选科目未定')
		minor=str
	}else if(WS.verifyEqual(str, 'yes', FailureHandling.OPTIONAL)){
		WS.comment('再选科目已定')	
		def num3=(int)(Math.random()*1000)%num1+1    //已选选选几个随机数
		def str1=''
		for(int x:(0..num3-1)){
			def num4=(int)(Math.random()*1000)%array_yes_next.size()
			WS.comment('str1进入while之前:'+str1)
			while(WS.verifyNotMatch(str1, '.*'+array_yes_next[num4]+'.*', true, FailureHandling.OPTIONAL)){
				if(str1==''){
					
				str1=str1+array_yes_next[num4]
				}else{
				
				str1=str1+','+array_yes_next[num4]
				}
				
				WS.comment('str1:'+str1)
			}
		}
		minor=str1	
	}
	
	return minor
}


def String get_level(){
	def array_level=['本科','专科']
	def num=(int)(Math.random()*1000)%array_level.size()
	return array_level[num]
		
}

def String get_main_subject(){
	def array_main_subject=['物理','历史']
	def num=(int)(Math.random()*1000)%array_main_subject.size()
	return array_main_subject[num]
	
}

//搜索专业名称
def void school_query_search(int from,int size,int school_code,String major_name){
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/school_query_search'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size,('school_code_or_name'):school_code,('major_name'):major_name]), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	if(jsonResponse.data.size>0){
		WS.comment('搜索到了专业:'+major_name)
		for(int x:(0..jsonResponse.data.size-1)){
			
			def actual_major_name=jsonResponse.data[x].major_name
			WS.comment('真实值:'+actual_major_name+',期望值:'+major_name)
			WS.verifyMatch(actual_major_name, major_name, false, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
	}else{
		WS.comment('搜索不到:'+major_name)
	
	}
	
}


def String send_and_verify_school_query(int from,int size,int school_code){
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/school_query'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size,('school_code_or_name'):school_code]), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	def major_name=''
	if(jsonResponse.data.size>0){
		WS.comment('有专业数据')
		for(int x:(0..jsonResponse.data.size-1)){
			
			def actual_school_code=jsonResponse.data[x].school_code
			WS.comment('真实值:'+actual_school_code+',期望值:'+school_code)
			WS.verifyEqual(actual_school_code, school_code, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
		
		def num=(int)(Math.random()*1000)%jsonResponse.data.size
		major_name=jsonResponse.data[num].major_name
	}else{
		WS.comment('没有专业数据')
	
	}
	
	return major_name
	
}


//获取默认列表,并返回第一条数据的院校代码
def int get_school_code(){
	'接口对象路径'
	def path='Object Repository/Api/Mobile Api/Campus/New college entrance examination/select_require_list'
	'获取新高考默认列表'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject(path,[('from'):from,('size'):size]), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse=get_jsonResponse(response)
	def code=0
	if(jsonResponse.data.size>0){
		WS.comment('有学校数据')
		code=jsonResponse.data[0].school_code
		
	}else{
	
		WS.comment('没有学校数据')
	}
	
	return code
	
}

//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}
