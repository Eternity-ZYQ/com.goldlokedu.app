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
  &quot;text&quot;: &quot;{\n  \t\&quot;teacher_status\&quot;:\&quot;${teacher_status}\&quot;,\n \t\&quot;teacher_abnormal_info\&quot;:\&quot;${teacher_abnormal_info}\&quot;,\n \t\&quot;family_members_status\&quot;:\&quot;${family_members_status}\&quot;,\n \t\&quot;family_members_abnormal_info\&quot;:\&quot;${family_members_abnormal_info\&quot;,\n \t\&quot;been_to_epidemic_area\&quot;:${been_to_epidemic_area},\n \t\&quot;epidemic_area\&quot;:\&quot;${epidemic_area}\&quot;,\n \t\&quot;bad_contact\&quot;:${bad_contact},\n  \t\&quot;in_local\&quot;:${in_local},\n  \t\&quot;location\&quot;:\&quot;${location}\&quot;,\n  \t\&quot;remark\&quot;:\&quot;${remark}\&quot;\n}&quot;,
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
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>4c2c10fd-d7e9-41f4-94f8-3eacb3f03981</id>
      <masked>false</masked>
      <name>teacher_status</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>4344fbce-d9b5-46be-9cf4-f0df501afa74</id>
      <masked>false</masked>
      <name>teacher_abnormal_info</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>e2df4ddc-e4da-406b-bde6-1414ca03a00f</id>
      <masked>false</masked>
      <name>family_members_status</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>e248cdce-f1c3-4bfc-9903-d679c61994e7</id>
      <masked>false</masked>
      <name>family_members_abnormal_info</name>
   </variables>
   <variables>
      <defaultValue>false</defaultValue>
      <description></description>
      <id>adc4ea5c-0c7a-4e91-ac0e-d7782becac7a</id>
      <masked>false</masked>
      <name>been_to_epidemic_area</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>bd4f1dad-253c-4917-bb40-12bd96ae3f19</id>
      <masked>false</masked>
      <name>epidemic_area</name>
   </variables>
   <variables>
      <defaultValue>false</defaultValue>
      <description></description>
      <id>a5367813-be4e-4a7d-ad44-90c39d7c4d06</id>
      <masked>false</masked>
      <name>bad_contact</name>
   </variables>
   <variables>
      <defaultValue>true</defaultValue>
      <description></description>
      <id>d32c42b5-8757-44c3-a8b3-0811dc7358d5</id>
      <masked>false</masked>
      <name>in_local</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>9bc32899-3a79-4188-8057-4ac9d81d74d9</id>
      <masked>false</masked>
      <name>location</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>db1eed68-9dad-4a7d-8154-bc6fab2fd785</id>
      <masked>false</masked>
      <name>remark</name>
   </variables>
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
