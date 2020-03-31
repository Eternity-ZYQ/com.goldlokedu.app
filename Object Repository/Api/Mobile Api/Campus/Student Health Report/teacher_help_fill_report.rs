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
  &quot;text&quot;: &quot;{\n \t\&quot;student_status\&quot;:\&quot;abnormal\&quot;,\n \t\&quot;student_abnormal_info\&quot;:\&quot;Yyy\&quot;,\n  \t\&quot;parent_status\&quot;:\&quot;health\&quot;,\n \t\&quot;parent_abnormal_info\&quot;:\&quot;\&quot;,\n  \t\&quot;been_to_epidemic_area\&quot;:false,\n  \t\&quot;epidemic_area\&quot;:\&quot;\&quot;,\n  \t\&quot;bad_contact\&quot;:false,\n  \t\&quot;in_local\&quot;:true,\n  \t\&quot;location\&quot;:\&quot;\&quot;,\n  \t\&quot;remark\&quot;:\&quot;Hhhh \&quot;,\n  \t\&quot;user_id\&quot;:\&quot;${GlobalVariable.student_id}\&quot;,\n  \t\&quot;class_id\&quot;:\&quot;${GlobalVariable.class_id}\&quot;,\n  \t\&quot;class_name\&quot;:\&quot;${GlobalVariable.class_name}\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/health_report/student/fill_report/${GlobalVariable.student_id}</restUrl>
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
