<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>加载文件</description>
   <name>dowmload_file</name>
   <tag></tag>
   <elementGuidId>8b58b76f-478d-47b0-a817-7bffe078b523</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.access_token}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/homeschool_chat/teacher_parent/app/file/${file_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>ae96bba5-0dac-4b7b-a16f-e021f6e40074</id>
      <masked>false</masked>
      <name>file_id</name>
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
	
	WS.comment('文件加载成功')
	
}else{

	WS.comment('文件加载失败')

}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
