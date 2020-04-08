<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发布通知</description>
   <name>publish_notification - Copy</name>
   <tag></tag>
   <elementGuidId>babf01e0-6a6d-4fec-b860-e278a1c51fed</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;address_str\&quot;: [\&quot;${GlobalVariable.class_name}\&quot;, \&quot;${GlobalVariable.user_name}\&quot;],\n\t\&quot;school_id\&quot;: \&quot;${GlobalVariable.user_school_id}\&quot;,\n\t\&quot;sender_id\&quot;: \&quot;${GlobalVariable.user_id}\&quot;,\n\t\&quot;need_sms_remind\&quot;: false,\n\t\&quot;title\&quot;: \&quot;${title}\&quot;,\n\t\&quot;payload\&quot;: {\n\t\t\&quot;message_type\&quot;: \&quot;Html\&quot;,\n\t\t\&quot;content\&quot;: \&quot;${content}\&quot;\n\t},\n\t\&quot;address\&quot;: {\n\t\t\&quot;student_address\&quot;: {\n\t\t\t\&quot;klass_ids\&quot;: [\&quot;${GlobalVariable.class_id}\&quot;]\n\t\t},\n\t\t\&quot;teacher_address\&quot;: {\n\t\t\t\&quot;teacher_ids\&quot;: [\&quot;${GlobalVariable.user_id}\&quot;],\n\t\t\t\&quot;department_ids\&quot;: []\n\t\t},\n\t\t\&quot;groups_address\&quot;: {\n\t\t\t\&quot;group_ids\&quot;: [{\n\t\t\t\t\&quot;id_type\&quot;: \&quot;student\&quot;,\n\t\t\t\t\&quot;ids\&quot;: []\n\t\t\t},{\n\t\t\t\t\&quot;id_type\&quot;: \&quot;dorm\&quot;,\n\t\t\t\t\&quot;ids\&quot;: []\n\t\t\t}]\n\t\t}\n\t}\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/notification/notification</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/notification_data').getValue(1, 1)</defaultValue>
      <description>通知标题</description>
      <id>ce70993e-5d60-4181-86ba-54253eb91a51</id>
      <masked>false</masked>
      <name>title</name>
   </variables>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/notification_data').getValue(2, 1)</defaultValue>
      <description>通知内容</description>
      <id>de7afb10-4caf-40cf-b21a-f60ff7d8ffd8</id>
      <masked>false</masked>
      <name>content</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>ef139e8f-3f55-4073-a019-5128def8c253</id>
      <masked>false</masked>
      <name>address_str</name>
   </variables>
   <variables>
      <defaultValue>false</defaultValue>
      <description></description>
      <id>8a4e8a74-0e67-4319-9eed-bd018561e779</id>
      <masked>false</masked>
      <name>need_sms_remind</name>
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
	
	assertThat(response.getResponseText()).contains('notification_id')
	
}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
