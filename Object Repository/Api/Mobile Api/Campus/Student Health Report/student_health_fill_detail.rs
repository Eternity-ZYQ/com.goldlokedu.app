<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学生健康填报已填详情</description>
   <name>student_health_fill_detail</name>
   <tag></tag>
   <elementGuidId>b4e54300-801f-463f-a576-83a88eb278f4</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/health_report/detail/students/${GlobalVariable.student_id}?from_date=${from_date}&amp;_=${timestamp}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'1585636450729'</defaultValue>
      <description></description>
      <id>b06f4d19-d326-4b92-a761-b308ae7c5f13</id>
      <masked>false</masked>
      <name>timestamp</name>
   </variables>
   <variables>
      <defaultValue>'20200331T000000-0800'</defaultValue>
      <description></description>
      <id>f0e4e098-6844-4154-a7c6-46dbf49106e6</id>
      <masked>false</masked>
      <name>from_date</name>
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

	assertThat(response.getResponseText()).contains('name')

}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
