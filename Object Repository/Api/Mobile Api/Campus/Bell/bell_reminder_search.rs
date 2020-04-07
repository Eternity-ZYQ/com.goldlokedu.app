<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>小铃铛列表</description>
   <name>bell_reminder_search</name>
   <tag></tag>
   <elementGuidId>27b8e208-8317-4c23-bfbb-28ba3f0afd7b</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;size\&quot;: 10,\n\t\&quot;from\&quot;: 0\n}&quot;,
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
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/reminder/search</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>d4080997-1510-4b42-b5dd-431b568f13d5</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>1aa51a7d-7f46-4aa5-a5c8-b060b26a665d</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
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
	
	&quot;返回体包含:total&quot;
	assertThat(response.getResponseText()).contains('total')
	
	
	save_message(response)
	
}



//保存第一条,为点击后改变成已读状态做数据准备
private void save_message(ResponseObject response){
	def jsonSlurper = new JsonSlurper()
	
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	def reminder_id=jsonResponse.data[0].reminder_id
	
	CustomKeywords.'public_method.Helper.addGlobalVariable'(&quot;reminder_id&quot;, reminder_id)
	
	//println(reminder_id)
	
	
}








</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
