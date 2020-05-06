<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>直播课列表</description>
   <name>live_course_list</name>
   <tag></tag>
   <elementGuidId>8f4169d0-8bc9-48fa-9132-0487f3a6d29e</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/live/interactRoom/list?page=${page}&amp;page_size=${page_size}&amp;request_site=${requese_site}&amp;type=${type}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>1</defaultValue>
      <description></description>
      <id>64adc801-0266-4f2b-b795-bef6f6ea14ac</id>
      <masked>false</masked>
      <name>page</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>796ff825-e99d-4ced-96cd-9d17ad8a2b24</id>
      <masked>false</masked>
      <name>page_size</name>
   </variables>
   <variables>
      <defaultValue>'app'</defaultValue>
      <description></description>
      <id>fc4242e7-7bf8-485c-a563-197dc3836a54</id>
      <masked>false</masked>
      <name>requese_site</name>
   </variables>
   <variables>
      <defaultValue>'live'</defaultValue>
      <description></description>
      <id>4581cfec-d40d-41e3-be7d-f9afc0ee3873</id>
      <masked>false</masked>
      <name>type</name>
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

}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
