<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>动态评论回复</description>
   <name>dynamic_comment_reply</name>
   <tag></tag>
   <elementGuidId>adecf26a-4980-4b6e-879b-e200456a04f9</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;replied_user\&quot;: {\n\t\t\&quot;creator_name\&quot;: \&quot;${creator_name}\&quot;,\n\t\t\&quot;creator\&quot;: \&quot;${creator}\&quot;\n\t},  \n\t\&quot;content\&quot;: \&quot;${content}\&quot;,\n\t\&quot;moment_id\&quot;: \&quot;${dynamic_moment_id}\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/moment/comment</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>c9f65c71-de26-4327-8055-2b90493a2043</id>
      <masked>false</masked>
      <name>dynamic_moment_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>f2e09251-efd9-423c-9490-d0ec1c2e5557</id>
      <masked>false</masked>
      <name>creator_name</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>b0d42e6d-b4d6-4d2c-b4b3-dc25ea529b37</id>
      <masked>false</masked>
      <name>creator</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>dda8deb3-f3db-473b-8ca1-7b2516f0e2ef</id>
      <masked>false</masked>
      <name>content</name>
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

	assertThat(response.getResponseText()).contains('comment_id')
}
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
