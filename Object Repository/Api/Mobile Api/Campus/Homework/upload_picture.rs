<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>上传作业图片</description>
   <name>upload_picture</name>
   <tag></tag>
   <elementGuidId>898c5da9-87f7-4de0-bf5b-a6419411567b</elementGuidId>
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
      &quot;value&quot;: &quot;Data Files/Image/Upload Test Image/timg (3).jpeg&quot;,
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
      <value>72313</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/homework/pictures</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
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

//def size=CustomKeywords.'public_method.FileSize.getFileSize'(&quot;Data Files/Image/Upload Test Image/timg (3).jpeg&quot;)
//
//WS.comment(size+&quot;&quot;)

&quot;请求服务器成功:200&quot;
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){

	WS.containsString(response, 'file_id', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'bucket_name', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'content_type', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'file_name', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'file_size', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'object_name', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'url', false, FailureHandling.CONTINUE_ON_FAILURE)

}


</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
