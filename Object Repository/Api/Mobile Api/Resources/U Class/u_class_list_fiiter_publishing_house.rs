<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>筛选教材</description>
   <name>u_class_list_fiiter_publishing_house</name>
   <tag></tag>
   <elementGuidId>01ce8418-82be-4e61-8ca0-7e28961d8427</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/onlineclass/detailpage/search_course?from=${from}&amp;popularity_sort=${popularity_sort}&amp;publishing_house=${publishing_house}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>e417e6bb-580d-4a92-913c-75dec11c217b</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>b8e59b54-502f-467a-ae29-63b2aec3d8ef</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'desc'</defaultValue>
      <description></description>
      <id>b9007497-06db-44fe-b0d8-392f8080cabb</id>
      <masked>false</masked>
      <name>popularity_sort</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>4e8aade1-2c1e-49e7-a008-2e726a594ea8</id>
      <masked>false</masked>
      <name>publishing_house</name>
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

assertThat(response.getResponseText()).contains(&quot;total&quot;)

}


</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
