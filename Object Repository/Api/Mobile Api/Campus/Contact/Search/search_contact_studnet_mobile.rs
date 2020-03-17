<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>打电话搜索学生手机号</description>
   <name>search_contact_studnet_mobile</name>
   <tag></tag>
   <elementGuidId>4bb763bc-eda5-4b56-99e5-2f3fae2b0d6e</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/user_profile/api/upf/contact/name/contact_information?id=${GlobalVariable.user_school_id}&amp;name=${student_mobile}&amp;type=app</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/fix_data').getValue(1, 1)</defaultValue>
      <description>搜索教师名字</description>
      <id>2c013988-c716-490d-a9e5-8698ab227453</id>
      <masked>false</masked>
      <name>teacher_name</name>
   </variables>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/fix_data').getValue(1, 2)</defaultValue>
      <description>搜索教师手机号</description>
      <id>e2d2d966-bbd6-4a51-9d0e-c5b8e4fe1acd</id>
      <masked>false</masked>
      <name>teacher_mobile</name>
   </variables>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/fix_data').getValue(2, 1)</defaultValue>
      <description>搜索学生名字</description>
      <id>24f36f78-c3f9-44ac-a75a-9cb3d2aede54</id>
      <masked>false</masked>
      <name>student_name</name>
   </variables>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/fix_data').getValue(2, 2)</defaultValue>
      <description>搜索学生手机号</description>
      <id>981c9606-1917-4e4f-8550-6bdba2b903a3</id>
      <masked>false</masked>
      <name>student_mobile</name>
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

&quot;文本code值:200&quot;
WS.verifyElementPropertyValue(response, 'code', 200)



}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
