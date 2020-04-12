<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>相册图片列表</description>
   <name>ablum_picture_list</name>
   <tag></tag>
   <elementGuidId>b5593cb4-69bb-42e8-a209-2ccd84a56686</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/album/album/pictures?album_id=${album_id}&amp;from=${from}&amp;size=${size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description>相册id</description>
      <id>5a27ac64-0aab-4bed-a462-bb6c9733e9b6</id>
      <masked>false</masked>
      <name>album_id</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>8427d9bb-80bd-42e5-a887-b94dc00677e8</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>10</defaultValue>
      <description></description>
      <id>510da04e-544a-4b3e-9d92-5e49d08857f3</id>
      <masked>false</masked>
      <name>size</name>
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
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
