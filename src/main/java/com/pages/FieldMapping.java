package com.pages;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import helper.Webdriverbase;
import helper.Wrapperdriver;

public class FieldMapping extends Webdriverbase{

	Wrapperdriver wrapperdriver;
	public static Properties prop;



	public By Home= By.xpath("//*[@class=\"drawerContent__SideBarDark__2k2LWKZ_ThQkeAdH7ClCaY\"]//*[@class='material-icons qa-home-icon']");

	public By FilterBar= By.xpath("//span[text()='Filter']//parent::div//child::div//input[@role=\"input\"]");

	public By InactiveApps= By.xpath("//span[text()=\"Inactive Apps\"]");

	public By FieldMapFilter= By.xpath("//div[@title='Field Mappings']");

	public By MapNameFiler= By.xpath("//div[@data-column-key='name']//child::div[@class=\"grid-dropdown__indexDark__R20SrfExkdRZl9LtrOSWQ\"]");

	public By FilerMap= By.xpath("//input[@id=\"name.$eq\"]");

	public By ApplyNameFilter= By.xpath("//button[text()='Apply']");

	public By Filters = By.xpath("//button[@title=\"Filters\"]");

	public By ClearFilers = By.xpath("//span[text()='Clear all filters']");

	public By FiltersApplied = By.xpath("//span[text()='FILTERS APPLIED']//parent::div//child::div");

	public By Refresh = By.xpath("//button[@title=\"Refresh\"]");


	public By coll= By.xpath("//div[@data-key=\"Column Positioning\"]//child::span");

	public By Fieldss = By.xpath("//div[@data-key=\"Source Field Name\"]//child::span");


	public String  F1Feilds;
	public String  F1Columns;



	public WebElement fields;

	public FieldMapping()
	{

		PageFactory.initElements(Driver, this);
		wrapperdriver = new Wrapperdriver(Driver);

	}

	public void FetchFieldMapData() throws IOException, ElementClickInterceptedException {


		wrapperdriver.clickOnWebElement(Home);

		wrapperdriver.clearElement(FilterBar);

		wrapperdriver.sendKeysToWebelement(FilterBar, "Field Mapping");

		wrapperdriver.clickOnWebElement(InactiveApps);
		wrapperdriver.clickOnWebElement(FieldMapFilter);

		if(wrapperdriver.elementdisplayed(FiltersApplied)) {
			wrapperdriver.clickOnWebElement(FiltersApplied);
		}
		wrapperdriver.clickOnWebElement(Refresh);

		wrapperdriver.clickOnWebElement(MapNameFiler);

		wrapperdriver.clearElement(FilerMap);

		wrapperdriver.sendKeysToWebelement(FilerMap, "ap_inbound_template");
		wrapperdriver.clickOnWebElement(ApplyNameFilter);

		// To Print total columns

		//	List<WebElement>  Columns = Driver.findElements(coll);
		//	WebElement col;


		//To Print Source Field names
		List<WebElement> filedname= Driver.findElements(Fieldss);
		WebElement fields;


		int NumberOfFields = filedname.size()-1;


		String FieldsOnApp = null;

		for(int i=0;i<filedname.size();i++) {

			fields=filedname.get(i);

			FieldsOnApp = fields.getText();

			int HeaderCountFromFile;
			String FieldNamesFromFile = null;

			//Take Job logs file from config
			prop = new Properties();
			prop.load(new FileInputStream(System.getProperty("user.dir") + "/properties/config.properties"));
						
			String csvFile = prop.getProperty("JobLogsFile");

			String line = "";
			String cvsSplitBy = ",";
			try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {


				int count = 0;
				while ((line = br.readLine()) != null) {

					String[] values = line.split(cvsSplitBy);
					count = values.length;

					HeaderCountFromFile= count;
					for (int j = 0; j < values.length; j++) {
						FieldNamesFromFile = values[j];

						//To Compare File and App date	
						while(i==j) {

							if(HeaderCountFromFile!=NumberOfFields) {

								System.out.println(HeaderCountFromFile + " Header Count From File");
								if(FieldNamesFromFile!=FieldsOnApp) {

									List <String> AppFields =Arrays.asList(FieldsOnApp);
									List <String> FileFields =Arrays.asList(FieldNamesFromFile);

									Set<String> FieldApp= new HashSet<String>(AppFields);

									FieldApp.removeAll(FileFields);
									System.out.println("after removing");
									System.out.println(FieldApp);
									
									//Delete file after processing
									File f = new File(prop.getProperty("JobLogsFile"));
									f.delete();
									System.out.println("File is deleted");
									
									Assert.assertEquals(HeaderCountFromFile, NumberOfFields);
									break;

								}

							}
							else {
								System.out.println(HeaderCountFromFile + " and " + FieldNamesFromFile);
								System.out.println("Inbound File Data is correct");
								File f = new File(prop.getProperty("JobLogsFile"));
								f.delete();
								System.out.println("File is deleted");

								break;
							}
							
							


							//	FieldNames+ " = "+ FieldsOnApp);
							//		1.Total Count- print - done
							//		2.Field mismatch print missing field
							//		3.CSV path - make it dynamic- hide the filename and flush it after completing script- done by config file
							//		4. If count mismatch stop script their itself- hard assert- done

							break;
						}

					}	

					break;
				}

			}

		}

	}
	
	
	 

}


//	driver.close();





