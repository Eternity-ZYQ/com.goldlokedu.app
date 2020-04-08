<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学校查询搜索专业</description>
   <name>school_query_search</name>
   <tag></tag>
   <elementGuidId>5414be0a-a208-455f-8394-51d2e4c94032</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/content/front/gaokao/bySubject?from=${from}&amp;major_name=${major_name}&amp;school_code_or_name=${school_code_or_name}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>e546bb29-4fee-453f-8bc0-46f619c5fae7</id>
      <masked>false</masked>
      <name>form</name>
   </variables>
   <variables>
      <defaultValue>50</defaultValue>
      <description></description>
      <id>e6115786-b736-4ab6-a7b2-248f95d81e77</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>'计算机类'</defaultValue>
      <description></description>
      <id>e8394f17-4241-48b9-98c1-b8944223fd64</id>
      <masked>false</masked>
      <name>major_name</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>613c4f1a-b779-4f96-ae8e-8efa837dfbc3</id>
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
if(WS.verifyResponseStatusCode(response, 200)){

	assertThat(response.getResponseText()).contains('total')

}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
