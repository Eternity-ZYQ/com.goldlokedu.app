<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发布图片投票</description>
   <name>publish_vote_picture</name>
   <tag></tag>
   <elementGuidId>c4d19be6-114d-48a6-9054-3f5512712b27</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;title\&quot;: \&quot;${publish_vote_title}\&quot;,\n\t\&quot;vote_range\&quot;: \&quot;${GlobalVariable.user_name},${GlobalVariable.class_name}\&quot;,\n\t\&quot;expiration\&quot;: \&quot;${vote_expiration}\&quot;,\n\t\&quot;vote_options\&quot;: [{\n\t\t\&quot;sequential\&quot;: 0,\n\t\t\&quot;content\&quot;: {\n\t\t\t\&quot;content\&quot;: \&quot;1111\&quot;,\n\t\t\t\&quot;picture_id\&quot;: \&quot;${GlobalVariable.vote_picture_id}\&quot;\n\t\t}\n\t}],\n\t\&quot;vote_type\&quot;: 1,\n\t\&quot;vote_participants\&quot;: {\n\t\t\&quot;teacher_participants\&quot;: {\n\t\t\t\&quot;teacher_ids\&quot;: [\&quot;${GlobalVariable.user_id}\&quot;]\n\t\t},\n\t\t\&quot;student_participants\&quot;: {\n\t\t\t\&quot;class_ids\&quot;: [\&quot;${GlobalVariable.class_id}\&quot;]\n\t\t}\n\t},\n\t\&quot;method\&quot;: 0,\n\t\&quot;max_option_amount\&quot;: 1\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/voting/vote</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/vote_data').getValue(3, 1)</defaultValue>
      <description>投票标题</description>
      <id>d340e440-03d4-4315-b6e3-9f14449026c3</id>
      <masked>false</masked>
      <name>publish_vote_title</name>
   </variables>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/vote_data').getValue(2, 1)</defaultValue>
      <description>截止日期</description>
      <id>0659ed52-1855-4403-be73-e71be5f6f2f7</id>
      <masked>false</masked>
      <name>vote_expiration</name>
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
	
	WS.verifyElementPropertyValue(response, 'result', 'Success')
	
}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
