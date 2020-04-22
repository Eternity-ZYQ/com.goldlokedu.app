<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>上传动态图片</description>
   <name>upload_dynamic_picture</name>
   <tag></tag>
   <elementGuidId>a14e1f13-c115-4dcb-805c-df7051f6c4f2</elementGuidId>
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
      &quot;value&quot;: &quot;Data Files/Image/Upload Test Image/timg (2).jpeg&quot;,
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
      <name>x-file-size</name>
      <type>Main</type>
      <value>${x-file-size}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/moment/attachments</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>101668</defaultValue>
      <description></description>
      <id>56751a42-110d-41d7-a7f0-ab61b9638ba1</id>
      <masked>false</masked>
      <name>x-file-size</name>
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

WS.comment('上传动态图片返回信息:'+response.getResponseText())
	
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
	WS.containsString(response, 'attachment_id', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'content_type', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'size', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'uri', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		
	}

//def size=CustomKeywords.'public_method.FileSize.getFileSize'(&quot;Data Files/Image/Upload Test Image/timg (2).jpeg&quot;)
//
//WS.comment(size+&quot;&quot;)</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
