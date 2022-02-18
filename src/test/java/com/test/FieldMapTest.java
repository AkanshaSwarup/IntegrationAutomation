package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pages.FieldMapping;

import helper.Wrapperdriver;
import utility.BaseTest;

public class FieldMapTest extends BaseTest{
	Wrapperdriver wrapperdriver;
	FieldMapping field = null;



	public FieldMapTest() {
	}

	@Test
	public void FieldTest() throws FileNotFoundException, IOException, InterruptedException {		

		//call login page
		LoginTest login= new LoginTest();
		login.LoginPage();
				

		//calling FieldMapping class and method
		field= new FieldMapping();
		field.FetchFieldMapData();


		test = extent.createTest("Field Mapping Page Accessibility", "Validate Field Mapping Accessibility");
		test.assignCategory("AP Inbound");

		field.FetchFieldMapData();

		test.log(Status.PASS, "Field Mapping Page accessed");


	}
}
