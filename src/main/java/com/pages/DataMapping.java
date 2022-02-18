package com.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import helper.Webdriverbase;
import helper.Wrapperdriver;

public class DataMapping extends Webdriverbase {

	public Wrapperdriver wrapperdriver;

	public By Home = By.xpath(
			"//*[@class=\"drawerContent__SideBarDark__2k2LWKZ_ThQkeAdH7ClCaY\"]//*[@class='material-icons qa-home-icon']");

	public By FilterBar = By.xpath("//span[text()='Filter']//parent::div//child::div//input[@role=\"input\"]");

	public By InactiveApps = By.xpath("//span[text()=\"Inactive Apps\"]");

	public By DataMapFilter = By.xpath("//div[@title='Data Mappings']");

	public By ProjectName = By.xpath(
			"//div[text()=\"Project Name\"]//parent::div[@class=\"grid-sort__indexDark__3sdD2cwpQEYYGnVe8LDBrc header-ellipsis-visible__indexDark__2qEXSV7B4ngBjl_Q0k_Dd7 qa-grid-filter-container\"]//following-sibling::div");

	public By Apply = By.xpath("//button[text()=\"Apply\"]");

	public By APinbound = By.xpath("//div[@data-key=\"Project Name\"]");

	public By ProjectType = By.xpath("//*[@id=\"name\"]");
	

	public By FileType = By.xpath("//*[@id=\"p_file_type\"]");

	public By Fileseperator = By.xpath("//*[@data-qa-field-selector=\"Fileseperator\"]/div");

	public By Filter = By.xpath("//*[@id=\"p_filter\"]");

	public By DatePattern = By.xpath("//div[@id=\"p_date_pattern\"]");

	public By FilterApp = By.xpath("//div[@id=\"p_filter_app\"]");
	
	public By ToleranceValue = By.xpath("//div[@id=\"p_payment_tolerance_value\"]");
	
	public static Properties prop;


	public DataMapping(WebDriver driver) throws IOException {

		PageFactory.initElements(Driver, this);
		wrapperdriver = new Wrapperdriver(Driver);
		
		prop = new Properties();
		FileInputStream ip;
			
			ip = new FileInputStream(System.getProperty("user.dir") + "/properties/config.properties");
			prop.load(ip);

	}

	
	public void FetchDataMap() {

		wrapperdriver.clickOnWebElement(Home);

		wrapperdriver.clearElement(FilterBar);

		wrapperdriver.sendKeysToWebelement(FilterBar, "data Mapping");

		wrapperdriver.clickOnWebElement(InactiveApps);
		wrapperdriver.clickOnWebElement(DataMapFilter);

		wrapperdriver.clickOnWebElement(APinbound);
		
		String Project_Type = wrapperdriver.getTextOfWebElement(ProjectType);

		String File_Type = wrapperdriver.getTextOfWebElement(FileType);

		String File_seperator = wrapperdriver.getTextOfWebElement(Fileseperator);

		String Filter_1 = wrapperdriver.getTextOfWebElement(Filter);

		String Date_Pattern = wrapperdriver.getTextOfWebElement(DatePattern);

		String Filter_App = wrapperdriver.getTextOfWebElement(FilterApp);

		String Tolerance_Value = wrapperdriver.getTextOfWebElement(ToleranceValue);
	

		
		String Delimiter = prop.getProperty("Delimter");
		
		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(File_seperator.equals(Delimiter));
		
		System.out.println(" Data mapping app data is validated");
		System.out.println(Project_Type + File_Type + Filter_1 + Date_Pattern + Filter_App + Tolerance_Value);

		


	}

}
