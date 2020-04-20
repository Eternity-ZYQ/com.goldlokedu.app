<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>班主任删除班级动态</description>
   <name>delete_class_dynamic</name>
   <tag></tag>
   <elementGuidId>a5567a75-2f8d-4ab8-92c6-73fc51f5d119</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;&quot;,
  &quot;contentType&quot;: &quot;text/plain&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.access_token}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>DELETE</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/moment/class_moments/${class_id}/${dynamic_moment_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>bb13ce1c-9cf6-454c-af46-262023c051df</id>
      <masked>false</masked>
      <name>class_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>6111ecb4-106d-4081-ac2e-503c08092a53</id>
      <masked>false</masked>
      <name>dynamic_moment_id</name>
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

WS.comment('删除班级动态返回数据body:'+response.getResponseText())
	
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
			
	WS.verifyElementPropertyValue(response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
