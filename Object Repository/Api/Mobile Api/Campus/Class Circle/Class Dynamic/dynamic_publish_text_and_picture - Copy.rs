<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发布动态文本和图片---不用于自动化</description>
   <name>dynamic_publish_text_and_picture - Copy</name>
   <tag></tag>
   <elementGuidId>444d5066-d241-4ed8-b5c9-029a51f4e98d</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;content\&quot;: \&quot;${content}\&quot;,\n\t\&quot;class_id\&quot;: \&quot;${class_id}\&quot;,\n  \t\&quot;pictures\&quot;: {\n\t\t\&quot;data\&quot;: [{\n\t\t\t\&quot;attachment_id\&quot;: \&quot;${picture_id}\&quot;,\n\t\t\t\&quot;size\&quot;: ${size},\n\t\t\t\&quot;content_type\&quot;: \&quot;${content_type}\&quot;\n\t\t}]\n\t}\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/moment/publish</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>fc23ec89-ad2a-49e5-8d22-c0282354154c</id>
      <masked>false</masked>
      <name>class_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>6182a8d3-db53-4d63-af3c-256c731348de</id>
      <masked>false</masked>
      <name>content</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>29f20655-7ba9-484c-9923-cb59e1fa581e</id>
      <masked>false</masked>
      <name>picture_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>4d311426-79df-4edf-a5e7-7980e37591a0</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>773d05a5-a2fd-47cb-9d87-dac1506946e3</id>
      <masked>false</masked>
      <name>content_type</name>
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

	assertThat(response.getResponseText()).contains('moment_id')
}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
