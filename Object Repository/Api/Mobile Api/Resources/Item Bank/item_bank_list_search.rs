<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>文本搜索</description>
   <name>item_bank_list_search</name>
   <tag></tag>
   <elementGuidId>4d479367-5a3a-47b2-8dc7-1be8e509e1a5</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/classroom/front/examination_papers?content=${content}&amp;from=${from}&amp;size=${size}&amp;sorting_rules=${sorting_rules}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'语文'</defaultValue>
      <description></description>
      <id>e4ba3145-d951-46df-9626-590e5c6db367</id>
      <masked>false</masked>
      <name>content</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>b3fadd6a-cbda-4d0a-9a6c-85b263b9682b</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>d5e84722-410c-44c9-95cd-b4ab3a756c66</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'1'</defaultValue>
      <description></description>
      <id>35e229d7-59bd-42f9-9885-2bedf4d98ba0</id>
      <masked>false</masked>
      <name>sorting_rules</name>
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
