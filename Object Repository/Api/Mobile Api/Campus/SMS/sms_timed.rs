<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发送短信--定时发送</description>
   <name>sms_timed</name>
   <tag></tag>
   <elementGuidId>f7285744-09d2-4a8a-b82b-b2b3a9cb922d</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;sender_id\&quot;: \&quot;${GlobalVariable.user_id}\&quot;,\n\t\&quot;address\&quot;: {\n\t\t${address}\n\t},\n\t\&quot;address_str\&quot;: [${address_str}],\n\t\&quot;need_reply\&quot;: true,\n  \t\&quot;scheduled_date\&quot;: \&quot;${sms_timed}\&quot;,\n\t\&quot;deliver_method\&quot;: [\&quot;SMS\&quot;],\n\t\&quot;payload\&quot;: {\n\t\t\&quot;content\&quot;: \&quot;${sms_content}\&quot;,\n\t\t\&quot;message_type\&quot;: \&quot;PlainText\&quot;\n\t},\n\t\&quot;sms_type\&quot;: \&quot;${sms_type}\&quot;,\n\t\&quot;signature\&quot;: \&quot;${sms_signature}\&quot;,\n  \t\&quot;signature_id\&quot;: \&quot;${sms_signature_id}\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/hsi/envelope</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>c0d0b9ad-4434-48a9-b41a-393e19e02347</id>
      <masked>false</masked>
      <name>sms_content</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>a449beab-b714-43a7-be70-3e19e3d5b1d4</id>
      <masked>false</masked>
      <name>sms_timed</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>e9e9701e-0987-4e76-913a-1746d80e15eb</id>
      <masked>false</masked>
      <name>sms_type</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>f8fd776f-4649-4fce-ab6d-1ab82aa78c5c</id>
      <masked>false</masked>
      <name>sms_signature</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>54eaecac-51d9-4c31-a3af-c906c57cfdc9</id>
      <masked>false</masked>
      <name>sms_signature_id</name>
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

WS.comment('发送定时短信请求体:'+request.httpBody)
WS.comment('发送定时短信接:'+response.responseText)

//请求接口成功
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
	
	&quot;文本result值:Success&quot;
	WS.verifyElementPropertyValue(response, 'result', 'Success', FailureHandling.CONTINUE_ON_FAILURE)

}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
