<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学校查询筛选首选科目</description>
   <name>school_query_filter_main_subjuct</name>
   <tag></tag>
   <elementGuidId>1f69f0d7-e1d5-4046-a31c-ff4efcd84f2f</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/content/front/gaokao/bySubject?from=${from}&amp;main_subject=${main_subject}&amp;school_code_or_name=${school_code_or_name}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>ae1dd9b1-a629-41f1-b0b4-61a834a72878</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>'物理'</defaultValue>
      <description></description>
      <id>6305cf62-2861-417e-85a5-0b0a35397647</id>
      <masked>false</masked>
      <name>main_subject</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>c0b23f52-ee19-41a6-9654-e9e0ad7519a9</id>
      <masked>false</masked>
      <name>school_code_or_name</name>
   </variables>
   <variables>
      <defaultValue>50</defaultValue>
      <description></description>
      <id>eccd99b7-d7b6-4f04-ab74-8b69ef7a7cf3</id>
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
