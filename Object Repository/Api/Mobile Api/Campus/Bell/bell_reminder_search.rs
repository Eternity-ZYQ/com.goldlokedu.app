<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>小铃铛列表</description>
   <name>bell_reminder_search</name>
   <tag></tag>
   <elementGuidId>27b8e208-8317-4c23-bfbb-28ba3f0afd7b</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;size\&quot;: ${size},\n\t\&quot;from\&quot;: ${from}\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.access_token}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/reminder/search</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>d4080997-1510-4b42-b5dd-431b568f13d5</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>1aa51a7d-7f46-4aa5-a5c8-b060b26a665d</id>
      <masked>false</masked>
      <name>from</name>
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

WS.comment('小铃铛列表数据body:'+response.getResponseText())

'判断接口请求是否成功'
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	
	'接口返回数据包含total'
	WS.containsString(response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
	
	
}











</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
