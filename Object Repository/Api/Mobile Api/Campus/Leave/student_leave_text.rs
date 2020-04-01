<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学生发起纯文本请假</description>
   <name>student_leave_text</name>
   <tag></tag>
   <elementGuidId>fdc5bdd6-ed30-40de-bdf6-74b8404a7439</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;start_date\&quot;: {\n\t\t\&quot;date\&quot;: \&quot;20200401T000000+0800\&quot;,\n\t\t\&quot;day_part\&quot;: \&quot;Afternoon\&quot;\n\t},\n\t\&quot;end_date\&quot;: {\n\t\t\&quot;date\&quot;: \&quot;20200401T235959+0800\&quot;,\n\t\t\&quot;day_part\&quot;: \&quot;Afternoon\&quot;\n\t},\n\t\&quot;leave_type\&quot;: \&quot;Sick\&quot;,\n\t\&quot;reason\&quot;: \&quot;哈哈哈\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/leave/leave</restUrl>
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
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
