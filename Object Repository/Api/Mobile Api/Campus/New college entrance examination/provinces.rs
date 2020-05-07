<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>城市筛选列表</description>
   <name>provinces</name>
   <tag></tag>
   <elementGuidId>36e92035-1787-45c9-84b1-fe67874074eb</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/content/front/gaokao/provinces</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
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

	String[] arrayResponse = [&quot;北京&quot;, &quot;天津&quot;, &quot;河北&quot;, &quot;河南&quot;, &quot;山西&quot;, &quot;内蒙古&quot;, &quot;辽宁&quot;, &quot;吉林&quot;, &quot;黑龙江&quot;, &quot;上海&quot;, &quot;江苏&quot;, &quot;浙江&quot;, &quot;安徽&quot;, &quot;福建&quot;, &quot;江西&quot;, &quot;山东&quot;, &quot;湖北&quot;, &quot;湖南&quot;, &quot;广东&quot;, &quot;海南&quot;, &quot;广西&quot;, &quot;四川&quot;, &quot;重庆&quot;, &quot;贵州&quot;, &quot;云南&quot;, &quot;西藏&quot;, &quot;陕西&quot;, &quot;甘肃&quot;, &quot;青海&quot;, &quot;新疆&quot;, &quot;宁夏&quot;]
	
}







</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
