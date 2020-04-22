<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>获取我的动态列表--学年筛选</description>
   <name>search_personal_dynamic_list_filter_year</name>
   <tag></tag>
   <elementGuidId>272ec4a8-e59d-4306-aec2-fa7d81aeee59</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/moment/personal?from=${from}&amp;size=${size}&amp;school_year=${school_year}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>400de11f-5044-49e3-98b8-a07690c6a5e6</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>'10'</defaultValue>
      <description></description>
      <id>60725432-dd0f-427a-ba2d-be6fd8663448</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>9d0ce63e-8aac-491e-a628-95b754e04625</id>
      <masked>false</masked>
      <name>school_year</name>
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

WS.comment('个人动态筛选学年后列表信息body:'+response.getResponseText())
	
if(WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)){
		
	WS.containsString(response, 'total', false, FailureHandling.CONTINUE_ON_FAILURE)
		
		
}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
