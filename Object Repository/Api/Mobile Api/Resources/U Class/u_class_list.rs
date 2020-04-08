<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>优课列表</description>
   <name>u_class_list</name>
   <tag></tag>
   <elementGuidId>72362d88-f598-4ffd-93dc-3f335d96e83e</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/onlineclass/detailpage/search_course?from=${from}&amp;popularity_sort=${popularity_sort}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>b01c59b8-8f46-4474-9f02-a5527c16ef41</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>7b604073-e034-4660-95f4-4e28ca73f200</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'desc'</defaultValue>
      <description></description>
      <id>94aeec32-211b-41ca-85b3-765a3e9b1be1</id>
      <masked>false</masked>
      <name>popularity_sort</name>
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
