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
  &quot;text&quot;: &quot;{\n\t\&quot;start_date\&quot;: {\n\t\t\&quot;date\&quot;: \&quot;${date}\&quot;,\n\t\t\&quot;day_part\&quot;: \&quot;${day_part}\&quot;\n\t},\n\t\&quot;end_date\&quot;: {\n\t\t\&quot;date\&quot;: \&quot;${end_date}\&quot;,\n\t\t\&quot;day_part\&quot;: \&quot;${end_day_part}\&quot;\n\t},\n\t\&quot;leave_type\&quot;: \&quot;${leave_type}\&quot;,\n\t\&quot;reason\&quot;: \&quot;${reason}\&quot;\n}&quot;,
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
   <variables>
      <defaultValue>'20200401T000000+0800'</defaultValue>
      <description></description>
      <id>b6d93ec2-33a3-4284-a6aa-33c2136a9653</id>
      <masked>false</masked>
      <name>date</name>
   </variables>
   <variables>
      <defaultValue>'Afternoon'</defaultValue>
      <description></description>
      <id>556b292f-5300-4724-8284-393532604cf5</id>
      <masked>false</masked>
      <name>day_part</name>
   </variables>
   <variables>
      <defaultValue>'20200401T235959+0800'</defaultValue>
      <description></description>
      <id>9e4345a0-894c-4a64-8990-56ab2faed39e</id>
      <masked>false</masked>
      <name>end_date</name>
   </variables>
   <variables>
      <defaultValue>'Afternoon'</defaultValue>
      <description></description>
      <id>c7bbf090-6f1e-491f-a5c1-627de425ccdc</id>
      <masked>false</masked>
      <name>end_day_part</name>
   </variables>
   <variables>
      <defaultValue>'Sick'</defaultValue>
      <description></description>
      <id>39423fbd-dd93-4ea6-82f3-d04d6555c2f1</id>
      <masked>false</masked>
      <name>leave_type</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>3f63727d-5c3b-4ba2-8862-580f4c9c8888</id>
      <masked>false</masked>
      <name>reason</name>
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

if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	
	WS.containsString(response, &quot;leave_id&quot;, false, FailureHandling.CONTINUE_ON_FAILURE)
}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
