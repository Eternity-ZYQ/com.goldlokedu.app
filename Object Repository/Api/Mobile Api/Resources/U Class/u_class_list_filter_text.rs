<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>搜索筛选</description>
   <name>u_class_list_filter_text</name>
   <tag></tag>
   <elementGuidId>90918e80-c091-4c1f-a97b-8472384f142a</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/onlineclass/detailpage/search_course?from=${from}&amp;popularity_sort=${popularity_sort}&amp;search_text=${search_text}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>aa329722-661c-4e2e-b187-b9839a3406ff</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>3fdc7a6d-ffff-453b-9703-2ed89eb81bc2</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'desc'</defaultValue>
      <description></description>
      <id>e78a1461-f7c3-4a88-a864-f4fc5b3d766a</id>
      <masked>false</masked>
      <name>popularity_sort</name>
   </variables>
   <variables>
      <defaultValue>'一年级'</defaultValue>
      <description></description>
      <id>c6d6feb9-4d8c-4b94-bc0c-f0ef0061f067</id>
      <masked>false</masked>
      <name>search_text</name>
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
