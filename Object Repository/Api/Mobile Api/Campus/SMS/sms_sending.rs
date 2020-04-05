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
  &quot;text&quot;: &quot;{\n\t\&quot;sender_id\&quot;: \&quot;${GlobalVariable.user_id}\&quot;,\n\t\&quot;address\&quot;: {\n\t\t\&quot;teacher_address\&quot;: {\n\t\t\t\&quot;teacher_ids\&quot;: [\&quot;${teacher_ids}\&quot;],\n\t\t\t\&quot;department_ids\&quot;: []\n\t\t},\n\t\t\&quot;groups_address\&quot;: {\n\t\t\t\&quot;group_ids\&quot;: [{\n\t\t\t\t\&quot;id_type\&quot;: \&quot;student\&quot;,\n\t\t\t\t\&quot;ids\&quot;: []\n\t\t\t}, {\n\t\t\t\t\&quot;id_type\&quot;: \&quot;dorm\&quot;,\n\t\t\t\t\&quot;ids\&quot;: []\n\t\t\t}]\n\t\t},\n\t\t\&quot;student_address\&quot;: {\n\t\t\t\&quot;student_ids\&quot;: [],\n\t\t\t\&quot;klass_ids\&quot;: []\n\t\t}\n\t},\n\t\&quot;address_str\&quot;: [\&quot;${GlobalVariable.user_name}\&quot;],\n\t\&quot;need_reply\&quot;: true,\n\t\&quot;deliver_method\&quot;: [\&quot;SMS\&quot;],\n\t\&quot;payload\&quot;: {\n\t\t\&quot;content\&quot;: \&quot;${sms_content}\&quot;,\n\t\t\&quot;message_type\&quot;: \&quot;PlainText\&quot;\n\t},\n\t\&quot;sms_type\&quot;: \&quot;${GlobalVariable.sms_type}\&quot;,\n  \t\&quot;signature\&quot;: \&quot;${GlobalVariable.sms_signature}\&quot;,\n  \t\&quot;signature_id\&quot;: \&quot;${GlobalVariable.sms_signature_id}\&quot;\n  \t\n}&quot;,
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
      <description>接收短信的教师的id</description>
      <id>bf52e260-2d51-4867-bd93-c7e178513f21</id>
      <masked>false</masked>
      <name>teacher_ids</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description>教师部门id</description>
      <id>df2e2af8-484f-4b6a-afc3-cc6cd244b723</id>
      <masked>false</masked>
      <name>department_ids</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description>分组学生id</description>
      <id>1edb0746-f071-437e-8b0c-fdffd27222ce</id>
      <masked>false</masked>
      <name>group_student_ids</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description>分组组id</description>
      <id>05455eaa-41a0-4a6e-b81a-2917d4379023</id>
      <masked>false</masked>
      <name>group_dorm_ids</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description>学生id</description>
      <id>999cbf14-1412-4f5e-bef2-994865860c95</id>
      <masked>false</masked>
      <name>student_ids</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description>班级id</description>
      <id>1f2bb032-6725-4c9e-b648-8d921bbf6548</id>
      <masked>false</masked>
      <name>klass_ids</name>
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

WS.comment(''+request.httpBody)
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
