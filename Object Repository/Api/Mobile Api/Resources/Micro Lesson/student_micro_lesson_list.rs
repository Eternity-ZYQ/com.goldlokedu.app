<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>学生微课列表</description>
   <name>student_micro_lesson_list</name>
   <tag></tag>
   <elementGuidId>5051a45e-f317-41b8-aa66-f1bb82f09562</elementGuidId>
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
   <restUrl>${GlobalVariable.MobileHost}/classroom/front/micro_lectures?courses=${courses}&amp;from=${from}&amp;grades=${grades}&amp;size=${size}&amp;sorting_rules=${sorting_rules}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>5acd0530-8be9-4807-bcdf-4d43ed3c673a</id>
      <masked>false</masked>
      <name>courses</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>69f997fa-e3e3-491a-8c7f-7ce6614873d9</id>
      <masked>false</masked>
      <name>from</name>
   </variables>
   <variables>
      <defaultValue>0</defaultValue>
      <description></description>
      <id>abede19a-67ef-40ce-b2c3-7d4ed2b55761</id>
      <masked>false</masked>
      <name>size</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>11c5e0cd-42a8-48dd-a81c-14d8b4922daf</id>
      <masked>false</masked>
      <name>grades</name>
   </variables>
   <variables>
      <defaultValue>1</defaultValue>
      <description></description>
      <id>575ff2ef-5b32-4e75-9dcd-f587282d4e13</id>
      <masked>false</masked>
      <name>sorting_rules</name>
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
