<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>公告列表</description>
   <name>search_notice_list</name>
   <tag></tag>
   <elementGuidId>a04216ed-dbb1-42ac-92c0-38b70a12f417</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/bulletin/search?from=${from}&amp;klass_id=${class_id}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>9ea4ab60-af70-4c2d-8417-baa9c2b7c5e9</id>
      <masked>false</masked>
      <name>class_id</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>4b875d9b-fd46-4671-88c4-dd87668e3690</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>e6d445b3-dbdc-438b-9d8a-0de4c9cb34f0</id>
      <masked>false</masked>
      <name>from</name>
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


WS.comment('班级公告列表信息body:'+response.getResponseText())

if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
	WS.containsString(response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)

	
}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
