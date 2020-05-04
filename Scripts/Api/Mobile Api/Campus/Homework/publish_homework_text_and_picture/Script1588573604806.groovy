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

'获取科目'
def course_id=set_course_name_and_get_id()

'选择发布通讯录'
def class_ids=get_class_id_list()

'作业内容'
def content=get_homework_content()

'上传图片'
def picture_ids=get_picture_id_list(1)

'发布作业'
publish_homework_text_and_picture(content,class_ids,course_id,picture_ids)







def void publish_homework_text_and_picture(String content,String class_ids,String course_id,String picture_ids){
	
	WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Homework/publish_homework_text_and_picture', [('content'):content,('class_ids'):class_ids,('course_id'):course_id,('picture_ids'):picture_ids]), FailureHandling.CONTINUE_ON_FAILURE)
	
}


def String get_homework_content(){
	def content='没有科目的作业'
	
	//WS.comment(course_name)
	if(course_name!=''){
		
		content=CustomKeywords.'time.SystemTime.get_system_time'()+'发布的'+course_name+'作业：明晚放学别走！此处有图'
	}
	
	return content
}

def String set_course_name_and_get_id(){
	
	ResponseObject response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Homework/school_manager_course"), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse =get_jsonResponse(response)
	def num=(int)(Math.random()*1000)%jsonResponse.data.size+1  //随机取一个数
	course_name=jsonResponse.data[num].name
	def course_id=jsonResponse.data[num].course_id
	
	return course_id
}


def String get_class_id_list(){
	'发送接口获取通讯录'
	ResponseObject response=WS.sendRequestAndVerify(findTestObject("Object Repository/Api/Mobile Api/Campus/Contact/Teacher/homework_contact_grade_and_class"), FailureHandling.CONTINUE_ON_FAILURE)
	def jsonResponse =get_jsonResponse(response)
	
	List list=new ArrayList<String>()
	if(jsonResponse.data.data.size>0){
		
		WS.comment('作业通讯录列表有联系人')
		
		for(int x:(0..jsonResponse.data.data.size-1)){
		
			def grade_name=jsonResponse.data.data[x].name
			
			if(jsonResponse.data.data[x].children.size>0){
				
				for(int y:(0..jsonResponse.data.data[x].children.size-1)){
					
					def class_id=jsonResponse.data.data[x].children[y].id
					list.add('"'+class_id+'"')
				}
				
			}else{
			
				WS.comment(grade_name+'下没有班级')
			}
		
		}
	}else{
	
		WS.comment('作业通讯录列表为空')
	
	}
	
	return list.toString()
	
}


def String get_picture_id_list(int count){
	
	List list=new ArrayList<String>()
	for(int x:(0..count-1)){
		
		ResponseObject response=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Mobile Api/Campus/Homework/upload_picture'), FailureHandling.CONTINUE_ON_FAILURE)
		def jsonResponse=get_jsonResponse(response)
		list.add('"'+jsonResponse.file_id+'"')
		
	}
	
	return list.toString()
}





//获取返回体json解析
def Object get_jsonResponse(ResponseObject response) {
	
	def jsonSlurper = new JsonSlurper()

	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	return jsonResponse
}