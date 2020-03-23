<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>上传的图片关联到班级相册</description>
   <name>album_picture_retaled_class</name>
   <tag></tag>
   <elementGuidId>39196000-915a-47f4-b59f-0edd4f926602</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;class_id\&quot;: \&quot;${GlobalVariable.class_id}\&quot;,\n\t\&quot;pictures\&quot;: [{\n\t\t\&quot;picture_id\&quot;: \&quot;8dd25a57-917a-47d9-8971-c630c32a6575\&quot;,\n\t\t\&quot;name\&quot;: \&quot;image_test.jpg\&quot;\n\t}],\n\t\&quot;album_id\&quot;: \&quot;${GlobalVariable.album_id}\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/album/picture</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
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
