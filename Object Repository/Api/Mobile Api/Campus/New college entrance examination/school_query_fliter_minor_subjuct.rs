<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学校查询筛选再选科目</description>
   <name>school_query_fliter_minor_subjuct</name>
   <tag></tag>
   <elementGuidId>45d94056-ae0c-4566-b9e3-7fb8dcd18371</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/content/front/gaokao/bySubject?from=${from}&amp;minor_subject=${minor_subject}&amp;school_code_or_name=${school_code_or_name}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>857b8075-bb6c-4478-ab97-3587df0bea2d</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>'生物,政治'</defaultValue>
      <description></description>
      <id>805d6c23-2e4f-40dd-9461-5d23c8e50ece</id>
      <masked>false</masked>
      <name>minor_subject</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>6776bfd1-b373-4fea-9dd5-3967dd02055e</id>
      <masked>false</masked>
      <name>school_code_or_name</name>
   </variables>
   <variables>
      <defaultValue>50</defaultValue>
      <description></description>
      <id>76043038-e1d7-44f0-aa38-407ee68c2810</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.model.FailureHandling
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

}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
