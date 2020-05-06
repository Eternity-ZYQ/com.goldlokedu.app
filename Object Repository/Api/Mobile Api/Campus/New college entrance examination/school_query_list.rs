<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学校查询列表---默认或搜索</description>
   <name>school_query_list</name>
   <tag></tag>
   <elementGuidId>8ebe4c2b-2376-4458-b17a-86aa2227e12b</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/content/front/gaokao/bySchool?from=${from}&amp;school_code_or_name=${school_code_or_name}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>2ef545c4-9959-4b03-a1c7-ae05c925a86e</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>bf279108-7656-40e2-9410-7fe8c9b5b723</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>02971083-e6f2-4e4f-8f7d-36ebb254764c</id>
      <masked>false</masked>
      <name>school_code_or_name</name>
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
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){

	WS.containsString(response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
