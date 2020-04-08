<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>编辑更新未上报数据</description>
   <name>updata_record</name>
   <tag></tag>
   <elementGuidId>3fc83efb-07da-4f64-879d-63f276eb52a1</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;school_id\&quot;: \&quot;${GlobalVariable.user_school_id}\&quot;,\n\t\&quot;grade_id\&quot;: \&quot;${grade_id}\&quot;,\n\t\&quot;class_id\&quot;: \&quot;${class_id}\&quot;,\n\t\&quot;create_time\&quot;: \&quot;${create_time}\&quot;,\n\t\&quot;num_unfill\&quot;: ${num_unfill},\n\t\&quot;num_abnormal\&quot;: ${num_abnormal},\n\t\&quot;details\&quot;: [{\n\t\t\&quot;user_id\&quot;: \&quot;${student_id}\&quot;,\n\t\t\&quot;user_name\&quot;: \&quot;${student_name}\&quot;,\n\t\t\&quot;temperature\&quot;: ${temperature},\n\t\t\&quot;abnormal\&quot;: ${abnormal},\n\t\t\&quot;leave\&quot;: ${leave}\n\t}],\n\t\&quot;class_name\&quot;: \&quot;${class_name}\&quot;,\n\t\&quot;sort_num\&quot;: ${sort_num},\n\t\&quot;record_id\&quot;: \&quot;${record_id}\&quot;,\n\t\&quot;submit_status\&quot;: \&quot;${submit_status}\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.MobileHost}/health_report/temperature/update</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>0ca9d46d-c5ac-4648-a9ec-c9cbf5ad0322</id>
      <masked>false</masked>
      <name>grade_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>4be72a18-0649-4699-8cfc-334bfd63a322</id>
      <masked>false</masked>
      <name>class_id</name>
   </variables>
   <variables>
      <defaultValue>'20200331T164041+0800'</defaultValue>
      <description></description>
      <id>e71b52e2-d64e-4e63-973d-c339274b4cb3</id>
      <masked>false</masked>
      <name>create_time</name>
   </variables>
   <variables>
      <defaultValue>18</defaultValue>
      <description></description>
      <id>aa9c4871-437b-4226-b7da-6b0904637baf</id>
      <masked>false</masked>
      <name>num_unfill</name>
   </variables>
   <variables>
      <defaultValue>1</defaultValue>
      <description></description>
      <id>1b072ff6-1830-400d-a119-58cbfc5d20a6</id>
      <masked>false</masked>
      <name>num_abnormal</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>cb79655a-8cc4-403b-9011-6376e3f9d3e8</id>
      <masked>false</masked>
      <name>student_id</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>0cf95c2e-674d-444a-9c04-97186b8e1867</id>
      <masked>false</masked>
      <name>student_name</name>
   </variables>
   <variables>
      <defaultValue>38</defaultValue>
      <description></description>
      <id>1bbb602a-c7fb-40ad-8dc7-ef66251b9a41</id>
      <masked>false</masked>
      <name>temperature</name>
   </variables>
   <variables>
      <defaultValue>true</defaultValue>
      <description></description>
      <id>e78e41d3-5fd6-41fa-9be2-816c4356aa3a</id>
      <masked>false</masked>
      <name>abnormal</name>
   </variables>
   <variables>
      <defaultValue>false</defaultValue>
      <description></description>
      <id>6ed06548-2251-4689-89ed-899e2dc9d320</id>
      <masked>false</masked>
      <name>leave</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>69f6e68c-018a-4ce8-b03c-83be453185c3</id>
      <masked>false</masked>
      <name>class_name</name>
   </variables>
   <variables>
      <defaultValue>6.09</defaultValue>
      <description></description>
      <id>40e0c9e2-5059-475f-8006-ec5a37e19167</id>
      <masked>false</masked>
      <name>sort_num</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>ccffee59-f58a-4603-98cc-7f52bf982099</id>
      <masked>false</masked>
      <name>record_id</name>
   </variables>
   <variables>
      <defaultValue>'ADVISOR'</defaultValue>
      <description></description>
      <id>08e7179e-16c6-47dd-b665-5aae53ec413c</id>
      <masked>false</masked>
      <name>submit_status</name>
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


WS.verifyResponseStatusCode(response, 200)

</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
