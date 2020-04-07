<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发布动态文本和图片</description>
   <name>dynamic_publish_text_and_picture</name>
   <tag></tag>
   <elementGuidId>08b9d774-719e-46ed-a541-922c1b27325d</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;content\&quot;: \&quot;${content}\&quot;,\n\t\&quot;class_id\&quot;: \&quot;${class_id}\&quot;,\n  \t\&quot;pictures\&quot;: {\n\t\t\&quot;data\&quot;: [{\n\t\t\t\&quot;attachment_id\&quot;: \&quot;${dynamic_upload_or_download_picture_id}\&quot;,\n\t\t\t\&quot;size\&quot;: ${size},\n\t\t\t\&quot;content_type\&quot;: \&quot;${content_type}\&quot;\n\t\t}]\n\t}\n}&quot;,
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
      <id>5e342fe8-627d-49a9-b494-aaaf3be1ef88</id>
      <masked>false</masked>
      <name>dynamic_upload_or_download_picture_id</name>
   </variables>
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
      <defaultValue>101668</defaultValue>
      <description></description>
      <id>3387ecc2-1fdf-42c8-b504-25cac273ca12</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'image/jpeg'</defaultValue>
      <description></description>
      <id>0ce54bfd-d2b0-4a73-bf66-33e87d2b3ebc</id>
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
