<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>新建体温监测</description>
   <name>create</name>
   <tag></tag>
   <elementGuidId>d9324b82-20ff-4159-b366-9754d86578ce</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;school_id\&quot;: \&quot;${GlobalVariable.user_school_id}\&quot;,\n\t\&quot;grade_id\&quot;: \&quot;${GlobalVariable.grade_id}\&quot;,\n\t\&quot;class_id\&quot;: \&quot;${GlobalVariable.class_id}\&quot;,\n\t\&quot;create_time\&quot;: \&quot;20200331T164041+0800\&quot;,\n\t\&quot;num_unfill\&quot;: 18,\n\t\&quot;num_abnormal\&quot;: 0,\n\t\&quot;details\&quot;: [{\n\t\t\&quot;user_id\&quot;: \&quot;${GlobalVariable.student_id}\&quot;,\n\t\t\&quot;user_name\&quot;: \&quot;${GlobalVariable.student_name}\&quot;,\n\t\t\&quot;temperature\&quot;: 36.5,\n\t\t\&quot;abnormal\&quot;: false,\n\t\t\&quot;leave\&quot;: false\n\t}]\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/health_report/temperature/create</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()


WS.verifyResponseStatusCode(response, 200)</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
