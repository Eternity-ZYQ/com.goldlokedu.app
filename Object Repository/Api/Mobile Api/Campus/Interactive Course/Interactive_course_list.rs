<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>互动课列表</description>
   <name>Interactive_course_list</name>
   <tag></tag>
   <elementGuidId>80cb1ff0-d493-46f7-a0b9-01fe1d8131d1</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/live/interactRoom/list?page=${page}&amp;page_size=${page_size}&amp;type=${type}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'interact'</defaultValue>
      <description></description>
      <id>61f5493d-831e-42a2-88d7-6d25ba2bd2d7</id>
      <masked>false</masked>
      <name>type</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>2545db1a-e913-4ceb-91d7-76492157c409</id>
      <masked>false</masked>
      <name>page_size</name>
   </variables>
   <variables>
      <defaultValue>1</defaultValue>
      <description></description>
      <id>a81fa4d7-b37d-48d0-bc16-a608672a8e72</id>
      <masked>false</masked>
      <name>page</name>
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
