package com.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import helper.Webdriverbase;
import helper.Wrapperdriver;

public class JobLogsPage extends Webdriverbase {

	Wrapperdriver wrapperdriver;
	public static Properties prop;


	public By Home = By.xpath("//span[text()='Home']");

	public By FilterBar = By.xpath("//span[text()='Filter']//parent::div//child::div//input[@role=\"input\"]");

	public By InactiveApps = By.xpath("//span[text()=\"Inactive Apps\"]");

	public By JoblogsClick = By.xpath("//div[@title='Job Logs']");

	public By Message = By.xpath(
			"//div[text()='message']//parent::div[@class='grid-sort__indexDark__3sdD2cwpQEYYGnVe8LDBrc header-ellipsis-visible__indexDark__2qEXSV7B4ngBjl_Q0k_Dd7 qa-grid-filter-container']//following-sibling::div");

	public By searchMessage = By.xpath("//input[@id=\"name.$eq\"]");

	public By ApplyFilter = By.xpath("//button[text()=\"Apply\"]");
	
	public By JobLink= By.xpath("//div[@data-key=\"message\"]//child::a[@title=\"BTDAVRO-HGETWSXWINN\"]");
	

	public JobLogsPage() {
		PageFactory.initElements(Driver, this);
		wrapperdriver = new Wrapperdriver(Driver);	}

	public void downloadDataFile() throws IOException {
		wrapperdriver.clickOnWebElement(Home);
		wrapperdriver.clearElement(FilterBar);
		wrapperdriver.sendKeysToWebelement(FilterBar, "Job Logs");
		wrapperdriver.clickOnWebElement(InactiveApps);
		wrapperdriver.clickOnWebElement(JoblogsClick);

//		wrapperdriver.clickOnWebElement(Message);
	//	wrapperdriver.sendKeysToWebelement(searchMessage, "ap_inbound");
	//	wrapperdriver.clickOnWebElement(ApplyFilter);
		
		wrapperdriver.clickOnWebElement(JobLink);
		
		prop = new Properties();
		prop.load(new FileInputStream(System.getProperty("user.dir") + "/properties/config.properties"));
		
		prop.getProperty("JobLogsFile");
		
		String srcFilePath = "/Users/tejaswini.patne/Downloads/BTDAVRO-HGETWSXWINN.csv";
		String destFilePath = "/Users/tejaswini.patne/Desktop/Automation5/joblogs/BTDAVRO-HGETWSXWINN.CSV";
		

		File source_file = new File(srcFilePath);

		File destination_file = new File(destFilePath);

		
			Files.copy(source_file.toPath(), destination_file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			byte[] encode = 	Base64.encodeBase64(srcFilePath.getBytes());
			System.out.println(new String(encode));
			
			
			System.out.println("File moved successfully");
			
			System.out.println(destination_file);
			
			
			


			


	}
}
