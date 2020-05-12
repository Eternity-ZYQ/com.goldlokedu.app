<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>上传文件</description>
   <name>upload_file</name>
   <tag></tag>
   <elementGuidId>49e12fdf-1310-46e4-bde3-70eff74a5c50</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;contentType&quot;: &quot;multipart/form-data&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;,
  &quot;parameters&quot;: [
    {
      &quot;name&quot;: &quot;file&quot;,
      &quot;value&quot;: &quot;/Users/faz/Desktop/postman/timg.jpeg&quot;,
      &quot;type&quot;: &quot;File&quot;
    }
  ]
}</httpBodyContent>
   <httpBodyType>form-data</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>multipart/form-data</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.access_token}</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>X-File-Size</name>
      <type>Main</type>
      <value>${X-File-Size}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/homeschool_chat/teacher_parent/app/file</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>207554</defaultValue>
      <description></description>
      <id>0e023bb7-9339-4110-b9f5-efa2f56447b9</id>
      <masked>false</masked>
      <name>X-File-Size</name>
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

if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	
	WS.containsString(response, 'bucket_name', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'content_type', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'file_id', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'file_name', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'file_size', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'object_name', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'url', false, FailureHandling.CONTINUE_ON_FAILURE)
	
	
}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
