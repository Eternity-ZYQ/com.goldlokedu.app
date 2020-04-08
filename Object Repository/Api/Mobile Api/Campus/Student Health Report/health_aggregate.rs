<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>健康统计时间及班级筛选</description>
   <name>health_aggregate</name>
   <tag></tag>
   <elementGuidId>bf749170-0af5-4321-a6a4-d0106e337540</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/health_report/aggregate/class?from_date=${from_date}&amp;class_id=${class_id}&amp;_=${timestamp}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>5e771594-f2aa-4943-bd0a-77d65f6f6f48</id>
      <masked>false</masked>
      <name>class_id</name>
   </variables>
   <variables>
      <defaultValue>'1585622834241'</defaultValue>
      <description></description>
      <id>a1cbd96b-429b-4ec8-8373-67c26d12293e</id>
      <masked>false</masked>
      <name>timestamp</name>
   </variables>
   <variables>
      <defaultValue>'20200331T000000-0800'</defaultValue>
      <description></description>
      <id>4bd0e130-ca3c-4ed8-8445-f26d4fab46bd</id>
      <masked>false</masked>
      <name>from_date</name>
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
