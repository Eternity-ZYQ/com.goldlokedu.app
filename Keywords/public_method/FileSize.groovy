package public_method

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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

import internal.GlobalVariable
import java.nio.channels.FileChannel

public class FileSize {
	@Keyword
	public  long getFileLength(String fileName){
		File file = new File(fileName);
		if (!file.exists() || !file.isFile()){
			System.out.println("文件不存在！！！");
			return -1;
		}
		//return  file.length();
		return  file;
	}



	@Keyword
	public long getFileSize(String fileName){
		FileChannel fc= null;
	
		File f= new File(fileName);
		if (f.exists() && f.isFile()){
			FileInputStream fis= new FileInputStream(f);
			fc= fis.getChannel();
			//logger.info(fc.size());
			//return fc.size();
			return fc.size()


	}


}


}
