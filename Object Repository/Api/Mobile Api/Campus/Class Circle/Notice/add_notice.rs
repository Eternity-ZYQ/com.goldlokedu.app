<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发布公告</description>
   <name>add_notice</name>
   <tag></tag>
   <elementGuidId>826df8cf-3212-4472-9a47-4befd01ef43a</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;attachments\&quot;: {\n\t\t\&quot;data\&quot;: []\n\t},\n\t\&quot;klass_id\&quot;: \&quot;${GlobalVariable.class_id}\&quot;,\n\t\&quot;kind_id\&quot;: \&quot;Normal\&quot;,\n\t\&quot;content\&quot;: \&quot;${}\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/bulletin/add</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/fix_data').getValue(8, 1)</defaultValue>
      <description>发布公告的内容</description>
      <id>38a067ca-1e45-4c0a-b573-b58c66e0eb6d</id>
      <masked>false</masked>
      <name>content</name>
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
}


</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
