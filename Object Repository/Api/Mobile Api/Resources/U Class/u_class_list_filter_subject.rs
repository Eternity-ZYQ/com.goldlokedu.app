<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>筛选学科</description>
   <name>u_class_list_filter_subject</name>
   <tag></tag>
   <elementGuidId>39d821ef-d029-4c73-b930-9951996b8107</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/onlineclass/detailpage/search_course?from=${from}&amp;popularity_sort=${popularity_sort}&amp;size=${size}&amp;subject_code=${subject_code}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>1cd319b5-0460-4985-944c-7f16a324af14</id>
      <masked>false</masked>
      <name>subject_code</name>
   </variables>
   <variables>
      <defaultValue>'desc'</defaultValue>
      <description></description>
      <id>a5e7587f-94a7-443c-be1d-9c69b247de87</id>
      <masked>false</masked>
      <name>popularity_sort</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>19091134-e4d5-4ef4-bdc3-9670cf7fe656</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>6c51e67d-72a0-4de0-a20e-04469cad3e70</id>
      <masked>false</masked>
      <name>size</name>
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
