<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>搜索</description>
   <name>courseware_list_filter_text</name>
   <tag></tag>
   <elementGuidId>3a7e70c9-3a86-474e-a636-489d1b3577a6</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/classroom/front/course_wares?content=${content}&amp;from=${from}&amp;size=${size}&amp;sorting_rules=${sorting_rules}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'英语'</defaultValue>
      <description></description>
      <id>982607ed-0a22-4794-93be-47d8e39383d8</id>
      <masked>false</masked>
      <name>content</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>a19aa955-b16e-46c0-bbe2-dd0eadfe6a86</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>9ed8751a-8404-4db7-9a7f-2303fa211350</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'1'</defaultValue>
      <description></description>
      <id>4acef616-9fb9-457c-a9f9-bce7047bac33</id>
      <masked>false</masked>
      <name>sorting_rules</name>
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
