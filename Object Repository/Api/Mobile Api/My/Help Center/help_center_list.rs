<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>帮助中心列表</description>
   <name>help_center_list</name>
   <tag></tag>
   <elementGuidId>a21b954e-596a-47ff-89f3-f92f7c71a99c</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;size\&quot;: 10,\n\t\&quot;with_content\&quot;: false,\n\t\&quot;from\&quot;: 0,\n\t\&quot;show_in_app\&quot;: true\n}&quot;,
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
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/service_desk/front/question/search</restUrl>
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
	&quot;返回体包含total&quot;
	assertThat(response.getResponseText()).contains('total')
	
	&quot;保存数据&quot;
	save_message(response)

}




//保存数据
private void save_message(ResponseObject response){
	def jsonSlurper = new JsonSlurper()
	
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	
	if(jsonResponse.total>0){
	&quot;保存question_id&quot;
	CustomKeywords.'public_method.Helper.addGlobalVariable'(&quot;question_id&quot;, jsonResponse.data[0].question_id)
	//WS.comment('Your text here')
	
	
	}
	
	
}



</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
