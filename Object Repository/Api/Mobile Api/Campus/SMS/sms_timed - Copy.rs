<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>发送短信---定时发送---不作为接口自动化测试</description>
   <name>sms_timed - Copy</name>
   <tag></tag>
   <elementGuidId>fbed49fd-0b2a-49ff-8f13-839d499b98a9</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;sender_id\&quot;: \&quot;${GlobalVariable.user_id}\&quot;,\n\t\&quot;address\&quot;: {\n\t\t\&quot;teacher_address\&quot;: {\n\t\t\t\&quot;teacher_ids\&quot;: [\&quot;${GlobalVariable.user_id}\&quot;],\n\t\t\t\&quot;department_ids\&quot;: []\n\t\t},\n\t\t\&quot;groups_address\&quot;: {\n\t\t\t\&quot;group_ids\&quot;: [{\n\t\t\t\t\&quot;id_type\&quot;: \&quot;student\&quot;,\n\t\t\t\t\&quot;ids\&quot;: []\n\t\t\t}, {\n\t\t\t\t\&quot;id_type\&quot;: \&quot;dorm\&quot;,\n\t\t\t\t\&quot;ids\&quot;: []\n\t\t\t}]\n\t\t},\n\t\t\&quot;student_address\&quot;: {\n\t\t\t\&quot;student_ids\&quot;: [],\n\t\t\t\&quot;klass_ids\&quot;: []\n\t\t}\n\t},\n\t\&quot;address_str\&quot;: [\&quot;${GlobalVariable.user_name}\&quot;],\n\t\&quot;need_reply\&quot;: true,\n  \t\&quot;scheduled_date\&quot;: \&quot;${GlobalVariable.sms_timed}\&quot;,\n\t\&quot;deliver_method\&quot;: [\&quot;SMS\&quot;],\n\t\&quot;payload\&quot;: {\n\t\t\&quot;content\&quot;: \&quot;${sms_content}\&quot;,\n\t\t\&quot;message_type\&quot;: \&quot;PlainText\&quot;\n\t},\n\t\&quot;sms_type\&quot;: \&quot;${GlobalVariable.sms_type}\&quot;,\n  \t\&quot;signature\&quot;: \&quot;${GlobalVariable.sms_signature}\&quot;,\n  \t\&quot;signature_id\&quot;: \&quot;${GlobalVariable.sms_signature_id}\&quot;\n}&quot;,
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
      <id>2ef01424-ae98-4720-ac2d-b81bc516ee6f</id>
      <masked>false</masked>
      <name>sms_content</name>
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

	
	
&quot;文本code值:200&quot;
WS.verifyElementPropertyValue(response, 'result', &quot;Success&quot;)



}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
