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
  &quot;text&quot;: &quot;{\n\t\&quot;start_date\&quot;: {\n\t\t\&quot;date\&quot;: \&quot;${date}\&quot;,\n\t\t\&quot;day_part\&quot;: \&quot;${day_part]\&quot;\n\t},\n\t\&quot;reason\&quot;: \&quot;${reason}\&quot;,\n\t\&quot;end_date\&quot;: {\n\t\t\&quot;day_part\&quot;: \&quot;${day_part01}\&quot;,\n\t\t\&quot;date\&quot;: \&quot;date01\&quot;\n\t},\n\t\&quot;attachments\&quot;: {\n\t\t\&quot;data\&quot;: [{\n  \t\t\t\&quot;file_id\&quot;:\&quot;88169cb4-7e86-446d-9194-50c54ffb47f6\&quot;,\n \t\t\t\&quot;file_name\&quot;:\&quot;/Users/faz/git/com.goldlokedu.app/Data Files/Image/Upload Test Image/timg(9).jpg\&quot;,\n  \t\t\t\&quot;url\&quot;:\&quot;/attachments/88169cb4-7e86-446d-9194-50c54ffb47f6\&quot;,\n  \t\t\t\&quot;file_size\&quot;:36924,\n  \t\t\t\&quot;content_type\&quot;:\&quot;image/jpeg\&quot;,\n \t\t\t\&quot;object_name\&quot;:\&quot;88169cb4-7e86-446d-9194-50c54ffb47f6\&quot;,\n \t\t\t\&quot;bucket_name\&quot;:\&quot;leave\&quot;\n}\n\n]\n\t},\n\t\&quot;leave_type\&quot;: \&quot;${leave_type}\&quot;\n}&quot;,
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
      <defaultValue>'Business'</defaultValue>
      <description></description>
      <id>e108ef69-2e78-4491-abef-33088fb4ccaa</id>
      <masked>false</masked>
      <name>leave_type</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>4d7609ff-dcce-4093-8050-13f896e66c6f</id>
      <masked>false</masked>
      <name>reason</name>
   </variables>
   <variables>
      <defaultValue>'20200401T000000+0800'</defaultValue>
      <description></description>
      <id>615345ce-f394-44b1-be74-f0db5b373ed7</id>
      <masked>false</masked>
      <name>date</name>
   </variables>
   <variables>
      <defaultValue>'Morning'</defaultValue>
      <description></description>
      <id>2c9b9565-2f7d-4951-bdbc-70d64d4346aa</id>
      <masked>false</masked>
      <name>day_part</name>
   </variables>
   <variables>
      <defaultValue>'Afternoon'</defaultValue>
      <description></description>
      <id>59270d13-0148-4bb8-98c2-c793ac1a136d</id>
      <masked>false</masked>
      <name>day_part01</name>
   </variables>
   <variables>
      <defaultValue>'20200401T235959+0800'</defaultValue>
      <description></description>
      <id>19d8cada-37eb-4d77-a62e-875a62bcdab9</id>
      <masked>false</masked>
      <name>date01</name>
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
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
