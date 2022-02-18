package com.pages;


import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import helper.Webdriverbase;
import helper.Wrapperdriver;

public class LoginPage extends Webdriverbase{


	Wrapperdriver wrapperdriver;


	public By UserId= By.xpath("//*[@value=\"Use Onit ID\"]");

	public By emailid= By.xpath("//*[@id=\"user_email\"]");
	
	public By userpass = By.xpath("//input[@id=\"user_password\"]");
	
	public By Login = By.xpath("//input[@value=\"Login\"]");
	
	public By PageName= By.xpath("//span[@title=\"Matter Management Home\"]//child::span");
	
	
	
	
	public LoginPage(WebDriver driver) {

		wrapperdriver = new Wrapperdriver(driver);
	}
	public void getEmailidPassword(String email1, String pass) throws FileNotFoundException {

		wrapperdriver.clickOnWebElement(UserId);
		wrapperdriver.sendKeysToWebelement(emailid, email1);
		wrapperdriver.sendKeysToWebelement(userpass, pass);
		wrapperdriver.clickOnWebElement(Login);
	


		
		
	
		}

	}

