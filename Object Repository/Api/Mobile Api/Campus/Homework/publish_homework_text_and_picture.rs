<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发布图文的作业</description>
   <name>publish_homework_text_and_picture</name>
   <tag></tag>
   <elementGuidId>5218d26d-49b9-402c-9b21-ab8fea333803</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;content\&quot;: \&quot;${content}\&quot;,\n\t\&quot;course_id\&quot;: \&quot;${course_id}\&quot;,\n\t\&quot;picture_ids\&quot;: ${picture_ids},\n\t\&quot;class_ids\&quot;: ${class_ids}\n}&quot;,
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
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/homework/teacher/homework</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/homework_data').getValue(1, 1)</defaultValue>
      <description>发送的作业内容</description>
      <id>36ca6e4d-bb05-4d36-aff2-3abf1b7f2395</id>
      <masked>false</masked>
      <name>content</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>b1f24476-a1fe-4d7d-8f09-fdaffe4d08ef</id>
      <masked>false</masked>
      <name>course_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>90def1ad-69fc-47f3-8fa1-bd0d4ec134f6</id>
      <masked>false</masked>
      <name>picture_ids</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>36a7141b-d26d-43c9-b734-407347cd1c53</id>
      <masked>false</masked>
      <name>class_id</name>
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
if(WS.verifyResponseStatusCode(response, 200,FailureHandling.CONTINUE_ON_FAILURE)){

&quot;文本code值:200&quot;
WS.verifyElementPropertyValue(response, 'result', &quot;Success&quot;,FailureHandling.CONTINUE_ON_FAILURE)

}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
