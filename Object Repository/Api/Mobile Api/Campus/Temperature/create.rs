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
  &quot;text&quot;: &quot;{\n\t\&quot;school_id\&quot;: \&quot;${GlobalVariable.user_school_id}\&quot;,\n\t\&quot;grade_id\&quot;: \&quot;${grade_id}\&quot;,\n\t\&quot;class_id\&quot;: \&quot;${class_id}\&quot;,\n\t\&quot;create_time\&quot;: \&quot;${create_time}\&quot;,\n\t\&quot;num_unfill\&quot;: ${num_unfill},\n\t\&quot;num_abnormal\&quot;: ${num_abnormal},\n\t\&quot;details\&quot;: [{\n\t\t\&quot;user_id\&quot;: \&quot;${student_id}\&quot;,\n\t\t\&quot;user_name\&quot;: \&quot;${student_name}\&quot;,\n\t\t\&quot;temperature\&quot;: ${temperature},\n\t\t\&quot;abnormal\&quot;: ${abnormal},\n\t\t\&quot;leave\&quot;: ${leave}\n\t}]\n}&quot;,
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
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>16dea603-8fd2-463e-a8b5-f1bc3ceeb3a4</id>
      <masked>false</masked>
      <name>grade_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>3abfa902-cc4d-4be2-9320-31fe4d251585</id>
      <masked>false</masked>
      <name>class_id</name>
   </variables>
   <variables>
      <defaultValue>'20200331T164041+0800'</defaultValue>
      <description></description>
      <id>66d14220-578d-4093-aec0-463ec1015191</id>
      <masked>false</masked>
      <name>create_time</name>
   </variables>
   <variables>
      <defaultValue>18</defaultValue>
      <description></description>
      <id>72b017ef-c2fe-4537-965f-ca3f3ff074ee</id>
      <masked>false</masked>
      <name>num_unfill</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>0862c406-0179-4d87-a839-8bcfa62fe916</id>
      <masked>false</masked>
      <name>num_abnormal</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>0351fe14-8279-4ed5-b71c-6f2fcda25686</id>
      <masked>false</masked>
      <name>student_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>4ae97907-4f08-4425-b3d9-5f76716cf587</id>
      <masked>false</masked>
      <name>student_name</name>
   </variables>
   <variables>
      <defaultValue>36.5</defaultValue>
      <description></description>
      <id>8a51c508-ef32-4851-ad83-5a8786b6ff75</id>
      <masked>false</masked>
      <name>temperature</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>e9305d76-1e02-48a1-aff9-881e1a706d66</id>
      <masked>false</masked>
      <name>abnormal</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>651826da-1ad5-472e-84ad-2fe61075faa1</id>
      <masked>false</masked>
      <name>leave</name>
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


WS.verifyResponseStatusCode(response, 200)</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
