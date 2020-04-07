<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>未绑定班牌列表</description>
   <name>not_bound_brand_list</name>
   <tag></tag>
   <elementGuidId>fac1a55d-6f00-481b-91b8-4bf0437dc886</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;size\&quot;: 10,\n\t\&quot;from\&quot;: 0,\n\t\&quot;is_bound\&quot;: ${is_bound}\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/brand_mdm_service/binding/school</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>21fd25d4-9d3e-4eb4-aeef-a30fd88a644f</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>f58fbcec-139a-486a-aa46-0e413bd141d2</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>false</defaultValue>
      <description></description>
      <id>4d8bb39d-e175-4fa2-8eff-4984005a4494</id>
      <masked>false</masked>
      <name>is_bound</name>
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

&quot;请求服务器成功:200&quot;
if(WS.verifyResponseStatusCode(response, 200)){

	assertThat(response.getResponseText()).contains('total')

}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
