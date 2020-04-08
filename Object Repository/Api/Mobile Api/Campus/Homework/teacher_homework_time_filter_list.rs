<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>教师作业时间筛选列表</description>
   <name>teacher_homework_time_filter_list</name>
   <tag></tag>
   <elementGuidId>9accb384-cbdf-4643-8847-b95115abe8fc</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/homework/teacher/homework?from=0&amp;size=10&amp;from_date=20200127T000000-0800&amp;to_date=20200327T235959-0800</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'20200127T000000-0800'</defaultValue>
      <description></description>
      <id>368c5da0-a17f-4e9b-ace8-9ea35f8de00c</id>
      <masked>false</masked>
      <name>from_date</name>
   </variables>
   <variables>
      <defaultValue>'20200327T235959-0800'</defaultValue>
      <description></description>
      <id>1c209b51-6bf5-4f43-be7f-8f8143f15e40</id>
      <masked>false</masked>
      <name>to_date</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>7a8dd609-a598-415e-a931-aa7921da3996</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>a8ccb579-420f-44e5-bfb4-1862f27fd649</id>
      <masked>false</masked>
      <name>from</name>
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
