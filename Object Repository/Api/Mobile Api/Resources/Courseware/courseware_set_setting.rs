<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>设置筛选</description>
   <name>courseware_set_setting</name>
   <tag></tag>
   <elementGuidId>9a6a1fd0-b0c2-494a-9456-40a76bfa9e88</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;courses\&quot;: [{\n\t\t\&quot;id\&quot;: \&quot;13\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;语文\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;14\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;数学\&quot;\n\t}, {\n\t\t\&quot;chinese_name\&quot;: \&quot;英语\&quot;,\n\t\t\&quot;id\&quot;: \&quot;41\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;16\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;物理\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;17\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;化学\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;18\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;生物\&quot;\n\t}, {\n\t\t\&quot;chinese_name\&quot;: \&quot;思想政治\&quot;,\n\t\t\&quot;id\&quot;: \&quot;26\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;11\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;品德与社会\&quot;\n\t}, {\n\t\t\&quot;chinese_name\&quot;: \&quot;信息技术\&quot;,\n\t\t\&quot;id\&quot;: \&quot;61\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;15\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;科学\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;25\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;美术\&quot;\n\t}, {\n\t\t\&quot;chinese_name\&quot;: \&quot;书法\&quot;,\n\t\t\&quot;id\&quot;: \&quot;120\&quot;\n\t}, {\n\t\t\&quot;chinese_name\&quot;: \&quot;综合实践活动\&quot;,\n\t\t\&quot;id\&quot;: \&quot;130\&quot;\n\t}],\n\t\&quot;grades\&quot;: [{\n\t\t\&quot;id\&quot;: \&quot;2\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;二年级\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;4\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;四年级\&quot;\n\t}, {\n\t\t\&quot;chinese_name\&quot;: \&quot;五年级\&quot;,\n\t\t\&quot;id\&quot;: \&quot;5\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;6\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;六年级\&quot;\n\t}, {\n\t\t\&quot;chinese_name\&quot;: \&quot;八年级\&quot;,\n\t\t\&quot;id\&quot;: \&quot;8\&quot;\n\t}, {\n\t\t\&quot;chinese_name\&quot;: \&quot;必修2\&quot;,\n\t\t\&quot;id\&quot;: \&quot;102\&quot;\n\t}, {\n\t\t\&quot;chinese_name\&quot;: \&quot;必修3\&quot;,\n\t\t\&quot;id\&quot;: \&quot;103\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;104\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;必修4\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;204\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;选修2\&quot;\n\t}, {\n\t\t\&quot;id\&quot;: \&quot;205\&quot;,\n\t\t\&quot;chinese_name\&quot;: \&quot;选修3\&quot;\n\t}]\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.access_token}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/classroom/front/setting</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
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

&quot;文本code值:200&quot;
WS.verifyElementPropertyValue(response, 'result', &quot;Success&quot;)

}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
