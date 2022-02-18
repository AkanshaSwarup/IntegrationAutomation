package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pages.InvoiceAPP;
import utility.BaseTest;

public class InvoiceAppTest extends BaseTest{


	public static Properties prop;
	InvoiceAPP invoice = null;

	public InvoiceAppTest() throws IOException {
		prop = new Properties();
		FileInputStream ip;
		ip = new FileInputStream(System.getProperty("user.dir") + "/properties/config.properties");
		prop.load(ip);

	}




	@Test
	public void InvoiceTest() throws FileNotFoundException, IOException, InterruptedException {

		//call login page
		LoginTest login= new LoginTest();
		login.LoginPage();

		invoice = new InvoiceAPP();

		invoice.FetchInvoiceData();

		test = extent.createTest("Invoice page Validation", "Validate Invoice page ");
		test.assignCategory("AP Inbound");

		invoice.FetchInvoiceData();

		test.log(Status.PASS, "Invoices Page is accessed");



	}
}
