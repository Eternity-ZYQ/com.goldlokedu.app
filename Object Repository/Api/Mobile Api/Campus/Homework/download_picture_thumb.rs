<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>加载作业图片--缩略图</description>
   <name>download_picture_thumb</name>
   <tag></tag>
   <elementGuidId>a45fa099-2e57-4b65-b51b-1fdced020ddb</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/homework/pictures/${picture_id}?thumb=${thumb}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>384ad622-ae63-41d2-a187-7297f4210ba2</id>
      <masked>false</masked>
      <name>picture_id</name>
   </variables>
   <variables>
      <defaultValue>true</defaultValue>
      <description></description>
      <id>4d29c90d-a77d-452d-b38a-18f4e9d6f7d7</id>
      <masked>false</masked>
      <name>thumb</name>
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


if(WS.verifyResponseStatusCode(response, 200,FailureHandling.CONTINUE_ON_FAILURE)){
	
	WS.comment('缩略图加载成功')
}else{

	WS.comment('缩略图加载失败')
}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
