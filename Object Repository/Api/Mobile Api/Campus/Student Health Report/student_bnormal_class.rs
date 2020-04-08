<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>班级学生异常说明</description>
   <name>student_bnormal_class</name>
   <tag></tag>
   <elementGuidId>2c6f0aff-2591-4245-9a69-fa6562ed9c3d</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/health_report/detail/abnormal/statistics/class/${GlobalVariable.class_id}?from_date=${from_date}&amp;_=${timestamp}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'1585636450732'</defaultValue>
      <description></description>
      <id>c18853d4-4ffa-414b-83ce-3f2bd9acc90c</id>
      <masked>false</masked>
      <name>timestamp</name>
   </variables>
   <variables>
      <defaultValue>'20200331T000000-0800'</defaultValue>
      <description></description>
      <id>4075f2ee-724d-4540-a419-97bbbfc4267a</id>
      <masked>false</masked>
      <name>from_date</name>
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

	assertThat(response.getResponseText()).contains('abnormal_students')

}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
