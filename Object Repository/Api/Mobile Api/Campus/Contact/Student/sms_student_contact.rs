<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>手机短信学生通讯录</description>
   <name>sms_student_contact</name>
   <tag></tag>
   <elementGuidId>714e12b9-0aea-4b83-99d2-475719f96a15</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/user_profile/api/upf/contact/studentByKlass?klass_id=${class_id}&amp;module=sms&amp;type=app</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>''</defaultValue>
      <description>班级id</description>
      <id>b6a4cfa5-7699-457d-b925-3d8f46663ab1</id>
      <masked>false</masked>
      <name>class_id</name>
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

WS.comment(&quot;获取班级内学生请求体body:&quot;+request.getBodyContent())

if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	
	WS.verifyElementPropertyValue(response, 'code', 200, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyElementPropertyValue(response, 'message', '操作成功', FailureHandling.CONTINUE_ON_FAILURE)
		

}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
