<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学校是否开通短信业务</description>
   <name>can_send</name>
   <tag></tag>
   <elementGuidId>7106de5f-6b67-4157-9fea-bfec58527850</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/business/check/general/school/${GlobalVariable.user_school_id}</restUrl>
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
	
	&quot;验证返回体正确:can_send&quot;
	assertThat(response.getResponseText()).contains('can_send')
	&quot;保存信息&quot;
	save_message(response)
}


//保存信息
private void save_message(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	&quot;保存can_send&quot;
	CustomKeywords.'public_method.Helper.addGlobalVariable'(&quot;can_send&quot;, jsonResponse.can_send)
	
	
}







</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
