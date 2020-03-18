<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>添加短信模板</description>
   <name>add_sms_template</name>
   <tag></tag>
   <elementGuidId>a31cb664-10f7-4af2-b127-dbb84ecbe086</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;content\&quot;: \&quot;${content}\&quot;,\n\t\&quot;title\&quot;: \&quot;${title}\&quot;,\n\t\&quot;sender_id\&quot;: \&quot;${GlobalVariable.user_id}\&quot;,\n\t\&quot;school_id\&quot;: \&quot;${GlobalVariable.user_school_id}\&quot;\n}&quot;,
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
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/hsi/template</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/fix_data').getValue(6, 1)</defaultValue>
      <description>添加模板内容</description>
      <id>043f6e6b-852f-4175-93fb-429054f4cd63</id>
      <masked>false</masked>
      <name>content</name>
   </variables>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/fix_data').getValue(5, 1)</defaultValue>
      <description>添加模板标题</description>
      <id>14f0aeca-9d3a-44da-bcab-fef5644e73a2</id>
      <masked>false</masked>
      <name>title</name>
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

	
	
assertThat(response.getResponseText()).contains('owner_id')


}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
