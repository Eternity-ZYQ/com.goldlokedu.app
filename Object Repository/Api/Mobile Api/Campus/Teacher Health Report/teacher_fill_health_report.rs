<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>填写教师健康填报</description>
   <name>teacher_fill_health_report</name>
   <tag></tag>
   <elementGuidId>d670feab-f609-40cc-a12f-84c306493013</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \t\&quot;teacher_status\&quot;:\&quot;abnormal\&quot;,\n \t\&quot;teacher_abnormal_info\&quot;:\&quot;烧\&quot;,\n \t\&quot;family_members_status\&quot;:\&quot;health\&quot;,\n \t\&quot;family_members_abnormal_info\&quot;:\&quot;\&quot;,\n \t\&quot;been_to_epidemic_area\&quot;:false,\n \t\&quot;epidemic_area\&quot;:\&quot;\&quot;,\n \t\&quot;bad_contact\&quot;:false,\n  \t\&quot;in_local\&quot;:true,\n  \t\&quot;location\&quot;:\&quot;\&quot;,\n  \t\&quot;remark\&quot;:\&quot;几时上课呢\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/health_report/teacher/fill_report</restUrl>
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
if(WS.verifyResponseStatusCode(response, 200,FailureHandling.OPTIONAL)){

	WS.verifyElementPropertyValue(response, 'result', &quot;Success&quot;)

}else if(WS.verifyResponseStatusCode(response, 409,FailureHandling.OPTIONAL)){

	WS.verifyElementPropertyValue(response, 'message', &quot;您已经填报过了，请勿重复填报！&quot;)


}



</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
