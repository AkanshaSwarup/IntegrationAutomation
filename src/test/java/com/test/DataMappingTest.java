package com.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.pages.DataMapping;

import helper.Wrapperdriver;
import utility.BaseTest;

public class DataMappingTest extends BaseTest {
	WebDriver driver = null;
	Wrapperdriver wrapperdriver;
	DataMapping data = null;

	public DataMappingTest() {

	}

	@Test 
	public void dataMappingTest() throws Exception {

		// call login page
		LoginTest Login = new LoginTest();
		Login.LoginPage();

		data = new DataMapping(driver);

		data.FetchDataMap();


		test = extent.createTest("Data Mapping page Validation", "Validate Data Mapping page ");
		test.assignCategory("AP Inbound");

		data.FetchDataMap();

		test.log(Status.PASS, "Data Mapping Page is accessed");


	}
}
