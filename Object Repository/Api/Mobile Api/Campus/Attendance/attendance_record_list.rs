<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>考勤记录列表--全部</description>
   <name>attendance_record_list</name>
   <tag></tag>
   <elementGuidId>f8399433-ecd4-4054-ba37-62b9314ecb40</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/attendance/frontend/record/view_klasses_statictics_by_day?check_date=2020-03-26&amp;check_school_id=${GlobalVariable.user_school_id}&amp;page=1</restUrl>
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


&quot;请求服务器成功:200&quot;
if(WS.verifyResponseStatusCode(response, 200)){

&quot;文本code值:200&quot;
WS.verifyElementPropertyValue(response, 'code', 200)



}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>