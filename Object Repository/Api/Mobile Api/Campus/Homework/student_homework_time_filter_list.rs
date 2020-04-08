<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学生作业列表时间筛选</description>
   <name>student_homework_time_filter_list</name>
   <tag></tag>
   <elementGuidId>4d94377a-9e5b-4ab9-a1bd-a89d1f0bfa34</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/homework/student/homework/list?from=${from}&amp;from_date=${from_date}&amp;size=${size}&amp;to_date=${to_date}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>141bdf7e-8431-49ea-a6f7-42251131acfa</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>2763dbea-1632-418a-8fda-d53623aa8524</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'20190401T000000-0800'</defaultValue>
      <description></description>
      <id>4902e6b4-f76f-4ac9-bdd3-b6913b96ae36</id>
      <masked>false</masked>
      <name>from_date</name>
   </variables>
   <variables>
      <defaultValue>'20200401T235959-0800'</defaultValue>
      <description></description>
      <id>cc4588fc-5b74-4918-af32-6d84ef63b056</id>
      <masked>false</masked>
      <name>to_date</name>
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

	assertThat(response.getResponseText()).contains('total')

}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
