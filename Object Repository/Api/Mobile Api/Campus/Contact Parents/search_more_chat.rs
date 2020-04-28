<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>加载更多数据</description>
   <name>search_more_chat</name>
   <tag></tag>
   <elementGuidId>243dca12-5c6a-4c86-9e58-b5569237ecdb</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/homeschool_chat/teacher_parent/app/search?from_counter=${from_counter}&amp;target_id=${student_id}&amp;to_counter=${to_counter}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>0cc31686-7947-4f68-9ae6-173f1cdc33e3</id>
      <masked>false</masked>
      <name>from_counter</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>4a0754a2-0bbc-46bc-b16e-e6e12c2b5133</id>
      <masked>false</masked>
      <name>student_id</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>404af10a-e215-48bf-b528-1a3db946e2d9</id>
      <masked>false</masked>
      <name>to_counter</name>
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

if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	if(jsonResponse.size>0){
		
		WS.containsString(response, 'id', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(response, 'teacher_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(response, 'student_id', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(response, 'message_type', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(response, 'origin', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(response, 'message', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(response, 'counter', false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.containsString(response, 'create_time', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		
	}
	
	
	
}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
