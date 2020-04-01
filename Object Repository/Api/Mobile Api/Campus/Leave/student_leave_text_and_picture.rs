<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学生请假带图片</description>
   <name>student_leave_text_and_picture</name>
   <tag></tag>
   <elementGuidId>cfac539b-ab02-4217-ab25-e6f2d04d8838</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;start_date\&quot;: {\n\t\t\&quot;date\&quot;: \&quot;20200401T000000+0800\&quot;,\n\t\t\&quot;day_part\&quot;: \&quot;Morning\&quot;\n\t},\n\t\&quot;reason\&quot;: \&quot;测试\&quot;,\n\t\&quot;end_date\&quot;: {\n\t\t\&quot;day_part\&quot;: \&quot;Afternoon\&quot;,\n\t\t\&quot;date\&quot;: \&quot;20200401T235959+0800\&quot;\n\t},\n\t\&quot;attachments\&quot;: {\n\t\t\&quot;data\&quot;: [{\n  \t\t\t\&quot;file_id\&quot;:\&quot;88169cb4-7e86-446d-9194-50c54ffb47f6\&quot;,\n \t\t\t\&quot;file_name\&quot;:\&quot;/Users/faz/git/com.goldlokedu.app/Data Files/Image/Upload Test Image/timg(9).jpg\&quot;,\n  \t\t\t\&quot;url\&quot;:\&quot;/attachments/88169cb4-7e86-446d-9194-50c54ffb47f6\&quot;,\n  \t\t\t\&quot;file_size\&quot;:36924,\n  \t\t\t\&quot;content_type\&quot;:\&quot;image/jpeg\&quot;,\n \t\t\t\&quot;object_name\&quot;:\&quot;88169cb4-7e86-446d-9194-50c54ffb47f6\&quot;,\n \t\t\t\&quot;bucket_name\&quot;:\&quot;leave\&quot;\n}\n\n]\n\t},\n\t\&quot;leave_type\&quot;: \&quot;Business\&quot;\n}&quot;,
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
