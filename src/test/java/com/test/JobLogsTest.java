package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pages.JobLogsPage;

import helper.Wrapperdriver;
import utility.BaseTest;

public class JobLogsTest extends BaseTest {
	
	

	LoginTest lgntst = null;
	WebDriver driver = null;
	Wrapperdriver wrapperdriver;
	public static Properties prop;
	
	@Test
	public void downloadJoblogs() throws FileNotFoundException, IOException, InterruptedException
	{
		LoginTest login= new LoginTest();
		login.LoginPage();
		
		
		JobLogsPage job= new JobLogsPage();
		job.downloadDataFile();
	
		test = extent.createTest("Job Logs App Accessibility", "Job Logs App Accessibility");
		test.assignCategory("AP Inbound");

		job.downloadDataFile();

		test.log(Status.PASS, "Job Log is accessed");
		
	}

}
