<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>已绑定班牌列表</description>
   <name>is_bound_brand_list</name>
   <tag></tag>
   <elementGuidId>d3e09560-a1dd-4605-9fda-9d1f6336279e</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;size\&quot;: 10,\n  \t\&quot;from\&quot;: 0,\n\t\&quot;is_bound\&quot;: ${is_bound}\t\n}&quot;,
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
      <id>5a205d5e-3321-4def-bbcc-b7b3f2d962a9</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>true</defaultValue>
      <description></description>
      <id>b1214fbf-7eb6-448e-bfe2-cb9becff6d2f</id>
      <masked>false</masked>
      <name>is_bound</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>09b9256e-5970-4981-9a40-b1e4f1b1749b</id>
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

&quot;请求服务器成功:200&quot;
if(WS.verifyResponseStatusCode(response, 200)){

	assertThat(response.getResponseText()).contains('total')

}


</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
