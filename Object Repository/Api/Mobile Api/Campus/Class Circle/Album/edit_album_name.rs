<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>修改相册名称</description>
   <name>edit_album_name</name>
   <tag></tag>
   <elementGuidId>bd9376d9-6571-4565-b26b-02846c82fd70</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;album_name\&quot;: \&quot;${album_name}\&quot;,\n\t\&quot;class_id\&quot;: \&quot;${class_id}\&quot;\n}&quot;,
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
   <restRequestMethod>PATCH</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/album/album/${album_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>d364a0c8-1cfc-4420-bf54-db864aa91efb</id>
      <masked>false</masked>
      <name>album_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>7b810de6-e041-4612-b56e-bc942886e627</id>
      <masked>false</masked>
      <name>album_name</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>337628d5-a96f-47a6-a87f-7d8698635037</id>
      <masked>false</masked>
      <name>class_id</name>
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

WS.comment('编辑相册名字返回数据body：'+response.getResponseText())
	
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
	WS.verifyElementPropertyValue(response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)
		
}
	



</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
