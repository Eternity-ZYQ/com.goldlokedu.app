<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>已发短信列表</description>
   <name>sms_sent_list</name>
   <tag></tag>
   <elementGuidId>8aafb03b-39cf-40ad-8f5e-428664f1f094</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/hsi/mailbox/school_outbox?deliver_method=${deliver_method}&amp;from=${from}&amp;owner_id=${GlobalVariable.user_id}&amp;school_id=${GlobalVariable.user_school_id}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>4b2660da-95ea-4c77-b0fb-9fde9ded10ba</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>c21f6b61-de1c-4dc6-b365-d70b13d51f6f</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>'SMS'</defaultValue>
      <description></description>
      <id>7e86204a-7b1e-42d1-965d-eaa09d2a66a4</id>
      <masked>false</masked>
      <name>deliver_method</name>
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

&quot;请求服务器成功:200&quot;
if(WS.verifyResponseStatusCode(response, 200)){

	assertThat(response.getResponseText()).contains('total')
	



}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
