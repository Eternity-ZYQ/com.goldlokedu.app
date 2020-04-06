<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发送短信</description>
   <name>sms_sending</name>
   <tag></tag>
   <elementGuidId>f8456689-38da-41d6-8ece-83201902b40a</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;sender_id\&quot;: \&quot;${GlobalVariable.user_id}\&quot;,\n\t\&quot;address\&quot;: {\n\t\t${address}\n\t},\n\t\&quot;address_str\&quot;: [${address_str}],\n\t\&quot;need_reply\&quot;: true,\n\t\&quot;deliver_method\&quot;: [\&quot;SMS\&quot;],\n\t\&quot;payload\&quot;: {\n\t\t\&quot;content\&quot;: \&quot;${sms_content}\&quot;,\n\t\t\&quot;message_type\&quot;: \&quot;PlainText\&quot;\n\t},\n\t\&quot;sms_type\&quot;: \&quot;${GlobalVariable.sms_type}\&quot;,\n  \t\&quot;signature\&quot;: \&quot;${GlobalVariable.sms_signature}\&quot;,\n  \t\&quot;signature_id\&quot;: \&quot;${GlobalVariable.sms_signature_id}\&quot;\n  \t\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/hsi/envelope</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description>短信内容</description>
      <id>5eab658b-3b29-4883-b297-477a7b07fe5a</id>
      <masked>false</masked>
      <name>sms_content</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>f70bfcb1-345a-4a9c-aa88-dedeb409ddd4</id>
      <masked>false</masked>
      <name>address</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>e3d1e609-6b21-4311-b8a6-6bddf8453f8c</id>
      <masked>false</masked>
      <name>address_str</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

WS.comment('输出请求体'+request.httpBody)
//&quot;请求服务器成功:200&quot;
//if(WS.verifyResponseStatusCode(response, 200)){
//
//	
//	
//	
//&quot;文本code值:200&quot;
//WS.verifyElementPropertyValue(response, 'result', &quot;Success&quot;)
//
//
//
//}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
