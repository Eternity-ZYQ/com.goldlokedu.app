<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学生考勤详情</description>
   <name>attendance_record_student_detail</name>
   <tag></tag>
   <elementGuidId>daf94682-0b3c-4ee5-8d72-d85a8c974f98</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.access_token}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/attendance/general/record/student_detail?append_prefix=${append_prefix}&amp;check_date=${check_date}&amp;student_id=${student_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'1'</defaultValue>
      <description></description>
      <id>ef7e63a2-1dde-42b3-9a7e-ec18537665c7</id>
      <masked>false</masked>
      <name>append_prefix</name>
   </variables>
   <variables>
      <defaultValue>'2020-04-01'</defaultValue>
      <description></description>
      <id>19dd3f4b-28d8-40cf-84db-676a177b7707</id>
      <masked>false</masked>
      <name>check_date</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>2dd30abc-75af-42e4-addc-051fa2b7aff3</id>
      <masked>false</masked>
      <name>student_id</name>
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

	WS.verifyElementPropertyValue(response, 'code', 200)

}




</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
