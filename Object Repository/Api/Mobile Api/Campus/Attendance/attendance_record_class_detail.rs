<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>班级考勤详情数据</description>
   <name>attendance_record_class_detail</name>
   <tag></tag>
   <elementGuidId>4f0e494a-631b-482b-8b2b-32cd9799d84a</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/attendance/general/record/klass_detail?check_date=${check_date}&amp;check_klass_id=${class_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'2020-03-26'</defaultValue>
      <description></description>
      <id>c7f9298a-569c-47e3-b31a-de595c8d149e</id>
      <masked>false</masked>
      <name>check_date</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>97a7457c-1417-479b-b272-f4dc9947bf86</id>
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
