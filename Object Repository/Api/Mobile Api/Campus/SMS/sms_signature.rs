<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>短信署名</description>
   <name>sms_signature</name>
   <tag></tag>
   <elementGuidId>0889d3bc-4d8c-4e23-96fa-1983003c604f</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/hsi/signature?sender_id=${GlobalVariable.user_id}</restUrl>
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


if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	&quot;返回体包含:data&quot;
	WS.containsString(response, 'data', false, FailureHandling.CONTINUE_ON_FAILURE)
	
	def jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	if(jsonResponse.data.size>0){
	WS.containsString(response, 'signature_id', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'name', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'selected', false, FailureHandling.CONTINUE_ON_FAILURE)

		
	}
	
	

}







</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
