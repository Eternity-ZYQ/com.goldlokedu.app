<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学校查询</description>
   <name>school_query</name>
   <tag></tag>
   <elementGuidId>ab092248-3280-46fc-a121-0c637b0b67c3</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/content/front/gaokao/bySubject?from=${from}&amp;school_code_or_name=${school_code_or_name}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>11f7ca41-9f2d-47ec-a677-38d97ef19bb8</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>cbcfeead-c5a7-4f60-882a-df8463499931</id>
      <masked>false</masked>
      <name>school_code_or_name</name>
   </variables>
   <variables>
      <defaultValue>50</defaultValue>
      <description></description>
      <id>32b2a0be-d474-43cf-8ead-67c34e4e4e35</id>
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

}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
