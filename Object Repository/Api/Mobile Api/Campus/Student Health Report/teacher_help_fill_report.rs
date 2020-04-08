<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>教师帮未填写的学生填写报告</description>
   <name>teacher_help_fill_report</name>
   <tag></tag>
   <elementGuidId>3a458e03-98c6-4b9a-96e2-cd6d9fda6720</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n \t\&quot;student_status\&quot;:\&quot;${student_status}\&quot;,\n \t\&quot;student_abnormal_info\&quot;:\&quot;${student_abnormal_info}\&quot;,\n  \t\&quot;parent_status\&quot;:\&quot;${parent_status}\&quot;,\n \t\&quot;parent_abnormal_info\&quot;:\&quot;${parent_abnormal_info}\&quot;,\n  \t\&quot;been_to_epidemic_area\&quot;:${been_to_epidemic_area},\n  \t\&quot;epidemic_area\&quot;:\&quot;${epidemic_area}\&quot;,\n  \t\&quot;bad_contact\&quot;:${bad_contact},\n  \t\&quot;in_local\&quot;:${in_local},\n  \t\&quot;location\&quot;:\&quot;${location}\&quot;,\n  \t\&quot;remark\&quot;:\&quot;${remark} \&quot;,\n  \t\&quot;user_id\&quot;:\&quot;${student_id}\&quot;,\n  \t\&quot;class_id\&quot;:\&quot;${class_id}\&quot;,\n  \t\&quot;class_name\&quot;:\&quot;${class_name}\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/health_report/student/fill_report/${student_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>a6c56c34-5eb6-487e-bd35-549e18d8451b</id>
      <masked>false</masked>
      <name>student_id</name>
   </variables>
   <variables>
      <defaultValue>'abnormal'</defaultValue>
      <description></description>
      <id>32d3c878-c957-41fa-8032-1d5db6c13b1e</id>
      <masked>false</masked>
      <name>student_status</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>90d8cf53-9e77-4101-a370-3b3d1fde30e4</id>
      <masked>false</masked>
      <name>student_abnormal_info</name>
   </variables>
   <variables>
      <defaultValue>'health'</defaultValue>
      <description></description>
      <id>4e003b27-9d7d-4bbb-a0c4-4ea7a4fa9dfc</id>
      <masked>false</masked>
      <name>parent_status</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>4f1218bf-81d5-47cc-94cf-4943482f35d1</id>
      <masked>false</masked>
      <name>parent_abnormal_info</name>
   </variables>
   <variables>
      <defaultValue>false</defaultValue>
      <description></description>
      <id>78ab66c0-2616-46b2-a760-657967a21930</id>
      <masked>false</masked>
      <name>been_to_epidemic_area</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>15235348-8832-4acd-9581-abb1b9ee1e95</id>
      <masked>false</masked>
      <name>epidemic_area</name>
   </variables>
   <variables>
      <defaultValue>false</defaultValue>
      <description></description>
      <id>8652d959-720d-4740-ad37-f1289d2907c5</id>
      <masked>false</masked>
      <name>bad_contact</name>
   </variables>
   <variables>
      <defaultValue>false</defaultValue>
      <description></description>
      <id>7c16e203-5d47-4f19-b1db-7c16c13f1745</id>
      <masked>false</masked>
      <name>in_local</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>672f4da3-6832-4f80-805b-3209439c0301</id>
      <masked>false</masked>
      <name>location</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>6bd1b971-441e-4d09-ac87-af8a5f186fde</id>
      <masked>false</masked>
      <name>remark</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>a534feb8-0909-425e-9f4e-07998b0912af</id>
      <masked>false</masked>
      <name>class_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>3783c814-0ced-407c-b8ce-8c18b2b97102</id>
      <masked>false</masked>
      <name>class_name</name>
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
