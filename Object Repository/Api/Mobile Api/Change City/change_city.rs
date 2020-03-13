<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>切换城市</description>
   <name>change_city</name>
   <tag></tag>
   <elementGuidId>96b227f1-48c9-451f-aa9d-c9eabc3d6157</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${GlobalVariable.MobileHost}/user_profile/general/available_district</restUrl>
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
WS.verifyResponseStatusCode(response, 200)

&quot;文本code值:200&quot;
def jsonSlurper = new JsonSlurper()

def jsonResponse = jsonSlurper.parseText(response.getResponseText())
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
