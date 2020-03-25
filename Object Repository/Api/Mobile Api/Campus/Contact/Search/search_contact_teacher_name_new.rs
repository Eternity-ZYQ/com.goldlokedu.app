<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>通知或投票联系人搜索,不分有无手机号及是否激活</description>
   <name>search_contact_teacher_name_new</name>
   <tag></tag>
   <elementGuidId>937fe6be-a5f0-488b-bddd-6837b6916667</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/user_profile/api/upf/contact/app/teacher/contact_information_only_name?module=vote&amp;name=${search_teacher_name}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>findTestData('User Information/Test Data/fix_data').getValue(1, 1)</defaultValue>
      <description>搜索教师的名字</description>
      <id>24d6d8de-5c4e-4a8a-a107-574f070bff3e</id>
      <masked>false</masked>
      <name>search_teacher_name</name>
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
	
	WS.verifyElementPropertyValue(response, 'code', 200)
	
}



</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
