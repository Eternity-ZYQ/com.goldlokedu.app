<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>待审批列表</description>
   <name>approval_pending_list</name>
   <tag></tag>
   <elementGuidId>87cf8383-ad05-4a2f-b9a9-cbebfe184c1f</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;size\&quot;: ${size},\n\t\&quot;from\&quot;: ${from},\n\t\&quot;audit_states\&quot;: [\&quot;${audit_states}\&quot;],\n\t\&quot;order_by\&quot;: \&quot;${order_by}\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/leave/leave/audit_list</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>cabceed9-cca8-4adf-8722-785cc58b87c4</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>aea3a985-ca57-4509-a3b3-891477645747</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>'NotAudit'</defaultValue>
      <description></description>
      <id>109c433d-6541-461a-958a-eda622da227c</id>
      <masked>false</masked>
      <name>audit_states</name>
   </variables>
   <variables>
      <defaultValue>'CreateTime'</defaultValue>
      <description></description>
      <id>329b7823-29e3-42af-8afe-ec8c414c0eb7</id>
      <masked>false</masked>
      <name>order_by</name>
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
	
	def jsonSlurper = new JsonSlurper()
	
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	if(jsonResponse.data.size>0){
		
		for(int x:(0..jsonResponse.data.size-1)){
	
			WS.verifyEqual(jsonResponse.data[x].audit_state, 'NotAudit', FailureHandling.CONTINUE_ON_FAILURE)
		}
		
	}
	
}


</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
