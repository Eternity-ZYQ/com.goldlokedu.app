<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>短信类型</description>
   <name>sms_type</name>
   <tag></tag>
   <elementGuidId>fd842b07-1965-4b8b-bea7-765cfe031e98</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/hsi/category?school_id=${GlobalVariable.user_school_id}</restUrl>
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

if(WS.verifyResponseStatusCode(response, 200)){
	&quot;返回体包含:data&quot;
	assertThat(response.getResponseText()).contains('data')
	
	
	
	
	
	&quot;保存信息&quot;
	save_message(response)
	
}




//保存信息
private void save_message(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	CustomKeywords.'public_method.Helper.addGlobalVariable'(&quot;sms_type&quot;, jsonResponse.data[0].category_id)
	//WS.comment(jsonResponse.data.size+&quot;&quot;)
	
	
	
	
	
}



















</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
