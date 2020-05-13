package time

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.keyword.datetime.DateTimeUtility as DateTimeUtility


import internal.GlobalVariable

public class SystemTime {


	@Keyword
	public String get_system_time(){
		Date date = new Date()

		SimpleDateFormat df = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss')

		String time = df.format(date)

		return time
	}




	@Keyword
	public String get_day_time(){
		Date date = new Date()

		SimpleDateFormat df = new SimpleDateFormat('yyyy-MM-dd')

		String time = df.format(date)

		return time
	}




	@Keyword
	public String get_future_time(int year){
		Date date = new Date()
		SimpleDateFormat df = new SimpleDateFormat('yyyy')
		String str_years = df.format(date)
		int years=Integer.parseInt(str_years)
		int Month=date.getMonth()+1
		int day=date.getDate()
		int hours=date.getHours()
		int minutes=date.getMinutes()
		int seconds=date.getSeconds()



		String time = ''+(years+year)+full_time(Month)+full_time(day)+'T'+full_time(hours)+full_time(minutes)+full_time(seconds)+'+0800'

		return time
	}


	public String full_time(int time){
		String str_time
		if(time<10){

			str_time='0'+time
		}else{

			str_time=''+time
		}


		return str_time
	}



	@Keyword
	public String get_future_day_time(int days){

		String str_years=new DateTimeUtility().getFutureDateTime(days, null, 'yyyy')
		int years=Integer.parseInt(str_years)
		String str_month=new DateTimeUtility().getFutureDateTime(days, null, 'MM')
		int Month=Integer.parseInt(str_month)
		String str_day=new DateTimeUtility().getFutureDateTime(days, null,'dd')
		int day=Integer.parseInt(str_day)

		String time = ''+years+full_time(Month)+full_time(day)+'T'+'235959'+'+0800'

		return time
	}

	@Keyword
	public String get_start_time(int days){
		String str_years=new DateTimeUtility().getFutureDateTime(days, null, 'yyyy')
		int years=Integer.parseInt(str_years)
		String str_month=new DateTimeUtility().getFutureDateTime(days, null, 'MM')
		int Month=Integer.parseInt(str_month)
		String str_day=new DateTimeUtility().getFutureDateTime(days, null,'dd')
		int day=Integer.parseInt(str_day)

		String time = ''+years+full_time(Month)+full_time(day)+'T'+'000000'+'+0800'

		return time
	}
}



