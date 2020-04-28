<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>轮询未读聊天记录</description>
   <name>poll_new_chat_record</name>
   <tag></tag>
   <elementGuidId>53c3e310-e90e-455b-973a-8a758af7d0ad</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/homeschool_chat/teacher_parent/app/poll?counter=${next_poll_counter}&amp;target_id=${student_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>381fac44-ee24-434e-be80-41181e3fb992</id>
      <masked>false</masked>
      <name>next_poll_counter</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>374d48c0-0070-47c6-8f66-80da39474379</id>
      <masked>false</masked>
      <name>student_id</name>
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

WS.comment('轮询最新信息:'+response.getResponseText())
&quot;请求服务器成功:200&quot;
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){

	WS.containsString(response, 'next_poll_counter', false, FailureHandling.CONTINUE_ON_FAILURE)
	

}


</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
