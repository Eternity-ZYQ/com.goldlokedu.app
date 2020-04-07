<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>考勤记录列表---班级</description>
   <name>attendance_record_class_list</name>
   <tag></tag>
   <elementGuidId>000cc1da-55a4-4053-809e-1262eb71c36b</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/attendance/frontend/record/view_klasses_statictics_by_day?check_date=${check_date}&amp;check_klass_id=${class_id}&amp;page=${page}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'2020-03-26'</defaultValue>
      <description></description>
      <id>d172d1a6-b868-41e1-ab28-467e9a0eac14</id>
      <masked>false</masked>
      <name>check_date</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>2e06e17b-b28a-4500-9fed-9843d842ff4a</id>
      <masked>false</masked>
      <name>page</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>27b8f226-016d-47b0-8ff0-90096e0bc2c6</id>
      <masked>false</masked>
      <name>class_id</name>
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
WS.verifyElementPropertyValue(response, 'code', 200)



}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
