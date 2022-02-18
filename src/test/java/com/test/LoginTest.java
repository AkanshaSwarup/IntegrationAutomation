package com.test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pages.LoginPage;

import helper.Wrapperdriver;
import utility.BaseTest;

public class LoginTest extends BaseTest{

	WebDriver driver = null;
	LoginPage login = null;
	Wrapperdriver wrapperdriver;

	public static Properties prop;

	public LoginTest() throws IOException {
		prop = new Properties();
		FileInputStream ip;


		ip = new FileInputStream(System.getProperty("user.dir") + "/properties/config.properties");
		prop.load(ip);



	}

	@Test
	public void LoginPage() throws IOException, InterruptedException {



		driver = helper.Webdriverbase.StartWebDriver();
		login = new LoginPage(driver);
		wrapperdriver = new Wrapperdriver(driver);
		driver.get(prop.getProperty("URL"));

		test = extent.createTest("Login Page Validation", "Validate Login Page Accessibility");
		test.assignCategory("AP Inbound");

		login.getEmailidPassword(prop.getProperty("email"),prop.getProperty("password"));

		test.log(Status.PASS, "Login Page accessed");



	}


}
