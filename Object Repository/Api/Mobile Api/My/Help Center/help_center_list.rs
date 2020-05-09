<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>帮助中心列表</description>
   <name>help_center_list</name>
   <tag></tag>
   <elementGuidId>a21b954e-596a-47ff-89f3-f92f7c71a99c</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;size\&quot;: ${size},\n\t\&quot;with_content\&quot;: ${with_content},\n\t\&quot;from\&quot;: ${from},\n\t\&quot;show_in_app\&quot;: ${show_in_app}\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/service_desk/front/question/search</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>eaf671d3-afd4-4eaa-bdbc-ce009daf0da8</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>false</defaultValue>
      <description></description>
      <id>77eefca9-4cd7-431d-9203-f58d15b2a3c8</id>
      <masked>false</masked>
      <name>with_content</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>d400c0a2-f6b7-4adc-ae82-17f5ef7d1581</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>true</defaultValue>
      <description></description>
      <id>68d39e30-d05c-431b-a481-fe84bb863c2b</id>
      <masked>false</masked>
      <name>show_in_app</name>
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
	&quot;返回体包含total&quot;
	WS.containsString(response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)

}






</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
