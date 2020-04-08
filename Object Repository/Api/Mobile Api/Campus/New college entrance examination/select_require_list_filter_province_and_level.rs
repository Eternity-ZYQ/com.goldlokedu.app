<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>选课列表筛选城市及层次</description>
   <name>select_require_list_filter_province_and_level</name>
   <tag></tag>
   <elementGuidId>69a81c1e-d3c2-4261-8977-300635b566f5</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/content/front/gaokao/bySchool?degree=本科&amp;from=0&amp;province=广东&amp;size=20</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'本科'</defaultValue>
      <description></description>
      <id>6dcf83ad-6c09-4b52-a85e-70814904d585</id>
      <masked>false</masked>
      <name>degree</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>3118c382-4f16-4d5d-8027-fda94d1f9861</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>20</defaultValue>
      <description></description>
      <id>edd8ad69-0845-44e0-885f-eb1a6c542d38</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'广东'</defaultValue>
      <description></description>
      <id>048ad919-a775-408d-a32b-b625b2f11358</id>
      <masked>false</masked>
      <name>province</name>
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
