<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>搜索学生名字---无关手机号</description>
   <name>search_contact_student_by_name_unconnected_mobile</name>
   <tag></tag>
   <elementGuidId>5bb08974-faed-4d3a-9526-bdfd531f531d</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.access_token}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/user_profile/api/upf/contact/klass/search_student_by_name?name=${student_name}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/fix_data').getValue(3, 1)</defaultValue>
      <description>搜索的学生名字</description>
      <id>91e56145-f4ef-4e99-9be3-5932bbcc4c37</id>
      <masked>false</masked>
      <name>student_name</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

&quot;请求服务器成功:200&quot;
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){

	&quot;文本code值:200&quot;
	WS.verifyElementPropertyValue(response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyElementPropertyValue(response, 'message', '操作成功', FailureHandling.CONTINUE_ON_FAILURE)

	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	if(jsonResponse.data.size>0){
		
		WS.containsString(response, 'is_init', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(response, 'name', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(response, 'userId', false, FailureHandling.CONTINUE_ON_FAILURE)
		
	}

}








</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
