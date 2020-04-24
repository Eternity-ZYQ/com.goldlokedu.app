<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>最新的公告内容</description>
   <name>new_one_notice</name>
   <tag></tag>
   <elementGuidId>23428ba7-27c5-4945-9905-807b0e4ad834</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/bulletin/board?klass_id=${class_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description>班级id</description>
      <id>42a0b944-3fe2-4716-9923-ab8c0500b452</id>
      <masked>false</masked>
      <name>class_id</name>
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

if(WS.verifyResponseStatusCode(response, 200, FailureHandling.OPTIONAL)){
	
	WS.containsString(response, 'bulletin_id', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'content', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'created_date', false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.containsString(response, 'is_deletable', false, FailureHandling.CONTINUE_ON_FAILURE)
	
	
}else if(WS.verifyResponseStatusCode(response, 404, FailureHandling.OPTIONAL)){

	WS.verifyElementPropertyValue(response, 'code', 404, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyElementPropertyValue(response, 'message', '本班级没有公告', FailureHandling.CONTINUE_ON_FAILURE)
	

}else{
	
	WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)
	WS.comment('接口异常')
}</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
