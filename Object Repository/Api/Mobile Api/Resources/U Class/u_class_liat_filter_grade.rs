<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>筛选年级</description>
   <name>u_class_liat_filter_grade</name>
   <tag></tag>
   <elementGuidId>0f293b16-93ef-4aec-8800-f2b1cada1a6a</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/onlineclass/detailpage/search_course?from=${from}&amp;grade_code=${class_filter_grade_code}&amp;popularity_sort=${popularity_sort}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>228e664c-ad20-473a-9153-3c33a95b2622</id>
      <masked>false</masked>
      <name>class_filter_grade_code</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>80a0409e-6804-4f7a-a51b-0de28e1cf9c6</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>1297dcd0-377a-432f-bdcb-31e23a6e98c5</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'desc'</defaultValue>
      <description></description>
      <id>ae72c4de-709b-4516-96af-b45436dac05d</id>
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
