<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发布文字投票</description>
   <name>publish_vote_text</name>
   <tag></tag>
   <elementGuidId>723ab931-3676-4cee-83f9-4eeb60d2a1aa</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;max_option_amount\&quot;: ${max_option_amount},\n\t\&quot;vote_type\&quot;: ${vote_type},\n\t\&quot;vote_range\&quot;: ${vote_range},\n\t\&quot;title\&quot;: \&quot;${title}\&quot;,\n\t\&quot;expiration\&quot;: \&quot;${expiration}\&quot;,\n\t\&quot;vote_options\&quot;: ${vote_options},\n\t\&quot;vote_participants\&quot;: {${vote_participants}},\n\t\&quot;method\&quot;: ${method}\n}&quot;,
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
      <defaultValue>findTestData('User Information/Test Data/vote_data').getValue(1, 1)</defaultValue>
      <description>投票标题</description>
      <id>17769e63-1c39-45ca-ac8f-204123a4c687</id>
      <masked>false</masked>
      <name>title</name>
   </variables>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/vote_data').getValue(2, 1)</defaultValue>
      <description>截止日期</description>
      <id>c9526216-4b7f-4658-a625-9e60670cdd20</id>
      <masked>false</masked>
      <name>expiration</name>
   </variables>
   <variables>
      <defaultValue>1</defaultValue>
      <description></description>
      <id>b7273cd7-c080-4c99-91fe-4c695c4016ca</id>
      <masked>false</masked>
      <name>max_option_amount</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>305405c6-ea36-4331-a445-79097785e1fe</id>
      <masked>false</masked>
      <name>vote_type</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>6e709fd0-f575-42bf-81c4-9b4187653012</id>
      <masked>false</masked>
      <name>vote_range</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>e360f8f7-ae8a-4cae-a5b8-b62e58d57f5e</id>
      <masked>false</masked>
      <name>vote_participants</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description>单选还是多选</description>
      <id>c0c7ef30-82f4-42aa-a580-a7e3b95c5763</id>
      <masked>false</masked>
      <name>method</name>
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

WS.comment('发送文本投票body:'+response.getResponseText())

if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	
	WS.verifyElementPropertyValue(response, 'result', 'Success',FailureHandling.CONTINUE_ON_FAILURE)
	
}

</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
