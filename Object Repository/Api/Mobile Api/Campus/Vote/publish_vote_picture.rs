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
  &quot;text&quot;: &quot;{\n\t\&quot;title\&quot;: \&quot;${title}\&quot;,\n\t\&quot;vote_range\&quot;: \&quot;${vote_range}\&quot;,\n\t\&quot;expiration\&quot;: \&quot;${expiration}\&quot;,\n\t\&quot;vote_options\&quot;: [{\n\t\t\&quot;sequential\&quot;: ${sequential},\n\t\t\&quot;content\&quot;: {\n\t\t\t\&quot;content\&quot;: \&quot;${content}\&quot;,\n\t\t\t\&quot;picture_id\&quot;: \&quot;${picture_id}\&quot;\n\t\t}\n\t}],\n\t\&quot;vote_type\&quot;: ${vote_type},\n\t\&quot;vote_participants\&quot;: {\n\t\t\&quot;teacher_participants\&quot;: {\n\t\t\t\&quot;teacher_ids\&quot;: [\&quot;${teacher_ids}\&quot;]\n\t\t},\n\t\t\&quot;student_participants\&quot;: {\n\t\t\t\&quot;class_ids\&quot;: [\&quot;${class_ids}\&quot;]\n\t\t}\n\t},\n\t\&quot;method\&quot;: ${method},\n\t\&quot;max_option_amount\&quot;: ${max_option_amount}\n}&quot;,
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
      <name>title</name>
   </variables>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/vote_data').getValue(2, 1)</defaultValue>
      <description>截止日期</description>
      <id>0659ed52-1855-4403-be73-e71be5f6f2f7</id>
      <masked>false</masked>
      <name>expiration</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>f71d4432-dc68-45f3-8bde-939249fefbf5</id>
      <masked>false</masked>
      <name>vote_range</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>719f5e3d-387d-41e1-99ff-2e1dd1450835</id>
      <masked>false</masked>
      <name>sequential</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>d8562e86-eba1-4a2b-8a3f-d887ea3989ee</id>
      <masked>false</masked>
      <name>content</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>6042fbb1-1f5b-46d1-86bb-2d0fd7cb6ee1</id>
      <masked>false</masked>
      <name>picture_id</name>
   </variables>
   <variables>
      <defaultValue>1</defaultValue>
      <description></description>
      <id>987a7120-dcd7-436c-ae48-dd08e6f62fc2</id>
      <masked>false</masked>
      <name>vote_type</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>13d0c53e-2f48-4311-b48d-9b441ec5f046</id>
      <masked>false</masked>
      <name>teacher_ids</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>85b30e7f-7051-4d6c-b875-d4a069f8de83</id>
      <masked>false</masked>
      <name>class_ids</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>d878cace-7ff9-4592-9467-2714c685ef39</id>
      <masked>false</masked>
      <name>method</name>
   </variables>
   <variables>
      <defaultValue>1</defaultValue>
      <description></description>
      <id>acf7947d-07c6-48ff-82ab-ac7fb6de30b9</id>
      <masked>false</masked>
      <name>max_option_amount</name>
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
