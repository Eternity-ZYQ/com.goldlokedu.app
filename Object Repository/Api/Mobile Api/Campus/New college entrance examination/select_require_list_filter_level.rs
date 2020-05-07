<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>select_require_list_filter_level</name>
   <tag></tag>
   <elementGuidId>443c918e-e137-4989-b37a-0859afe4b1e3</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/content/front/gaokao/bySchool?degree=${degree}&amp;from=${from}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'本科'</defaultValue>
      <description></description>
      <id>8186f130-b543-40f8-ad3f-6c61650de85e</id>
      <masked>false</masked>
      <name>degree</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>f8a12008-1b6d-4d28-a69f-0e24d14db6ed</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>20</defaultValue>
      <description></description>
      <id>a0b13006-b4f3-4ee6-99c7-4fba1c3a6afd</id>
      <masked>false</masked>
      <name>size</name>
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

&quot;请求服务器成功:200&quot;
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){

	WS.containsString(response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)

}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
