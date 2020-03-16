<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>登录接口</description>
   <name>teacher_login</name>
   <tag></tag>
   <elementGuidId>f2e6867b-c657-4ea4-9dcd-a801ffd5232e</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;client_id\&quot;: \&quot;C7E84412-AC52-4D14-9C02-37C8A241BDC4\&quot;,\n\t\&quot;password\&quot;: \&quot;${password}\&quot;,\n\t\&quot;account\&quot;: \&quot;${account}\&quot;\n}&quot;,
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
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/user_auth/login/password</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>findTestData('User Information/Account/teacher').getValue(1, 1)</defaultValue>
      <description>教师账号</description>
      <id>0cb87c45-32da-458b-b067-37a6c663d0e9</id>
      <masked>false</masked>
      <name>account</name>
   </variables>
   <variables>
      <defaultValue>findTestData('User Information/Account/teacher').getValue(2, 1)</defaultValue>
      <description>教师密码</description>
      <id>46a04cfd-83f9-4835-92ee-15c48db0623c</id>
      <masked>false</masked>
      <name>password</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager
import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.testdata.TestData as TestData

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

&quot;登录请求成功:200&quot;
if(WS.verifyResponseStatusCode(response, 200)){
	
	&quot;保存用户信息&quot;
	save_message(response)	
}












//保存用户信息
private void save_message(ResponseObject response){
	
	def jsonSlurper = new JsonSlurper()
	
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
		
	&quot;保存token&quot;
	CustomKeywords.'public_method.Helper.addGlobalVariable'(&quot;access_token&quot;, jsonResponse.access_token)
	
	&quot;保存user_id&quot;
	CustomKeywords.'public_method.Helper.addGlobalVariable'(&quot;user_id&quot;, jsonResponse.user_id)
	
	&quot;保存session_id&quot;
	CustomKeywords.'public_method.Helper.addGlobalVariable'(&quot;session_id&quot;, jsonResponse.session_id)
	
	&quot;保存user_school_id&quot;
	CustomKeywords.'public_method.Helper.addGlobalVariable'(&quot;user_school_id&quot;, jsonResponse.user_school_id)
	
	
	
	
	
}














def jsonSlurper = new JsonSlurper()

def jsonResponse = jsonSlurper.parseText(response.getResponseText())</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
