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
  &quot;text&quot;: &quot;{\n\t\&quot;class_id\&quot;: \&quot;${class_id}\&quot;,\n\t\&quot;pictures\&quot;: [{\n\t\t\&quot;picture_id\&quot;: \&quot;${picture_id}\&quot;,\n\t\t\&quot;name\&quot;: \&quot;${name}\&quot;\n\t}],\n\t\&quot;album_id\&quot;: \&quot;${album_id}\&quot;\n}&quot;,
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
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>a698a7ff-2b64-4dcf-adab-da0d4bc0d47d</id>
      <masked>false</masked>
      <name>class_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>83110756-8b08-475f-a3ad-7133b50691d0</id>
      <masked>false</masked>
      <name>picture_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>d084f2f1-7d18-4b16-8566-4d095d4df153</id>
      <masked>false</masked>
      <name>name</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>62006bb4-b69a-45d0-a3e3-a13908433e60</id>
      <masked>false</masked>
      <name>album_id</name>
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


WS.comment('上传图片关联到班级相册body：'+response.getResponseText())
	
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
		
	WS.verifyElementPropertyValue(response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
