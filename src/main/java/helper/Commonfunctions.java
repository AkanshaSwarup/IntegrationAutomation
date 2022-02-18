package helper;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Commonfunctions

{
    public static WebDriver driver = null;
    static Wrapperdriver wrapperdriver;

    public Commonfunctions(WebDriver driver) {
	Commonfunctions.driver = driver;
	wrapperdriver = new Wrapperdriver(driver);

    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
	boolean flag = false;
	File dir = new File(downloadPath);
	File[] dir_contents = dir.listFiles();

	for (int i = 0; i < dir_contents.length; i++) {
	    if (dir_contents[i].getName().contains(fileName))
		return flag = true;
	}

	return flag;
    }

    public String[] splitstring(String splittext, String splitchar) {
	splitchar = "\\(";
	String[] output = splittext.split(splitchar);

	return output;

    }

    public boolean comparelist(List<String> List1, List<String> List2) {
	boolean match = false;

	if (List1.equals(List2)) {
	    System.out.println("Same");
	    match = true;
	} else {
	    System.out.println("Not Same");

	}
	return match;

    }
    
    public boolean comparestring  (String String1, String String2) {
    	boolean match = false;

    	if (String1.equals(String2)) {
    	    System.out.println("Same");
    	    match = true;
    	} else {
    	    System.out.println("Not Same");

    	}
    	return match;

        }

    // To generate a random String value
    public String getSaltString() {
	String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	StringBuilder salt = new StringBuilder();
	Random rnd = new Random();
	while (salt.length() < 4) { // length of the random string.
	    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	    salt.append(SALTCHARS.charAt(index));
	}
	String saltStr = salt.toString();
	return saltStr;

    }

    public String getSaltStringBlock() {
	String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	StringBuilder salt = new StringBuilder();
	Random rnd = new Random();
	while (salt.length() < 4) { // length of the random string.
	    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	    salt.append(SALTCHARS.charAt(index));
	}
	String saltStr = salt.toString();
	return saltStr;

    }

    public String getSaltStringSmall() {
	String SALTCHARS = "qwertyuiopasdfghjklzxcvbnm";
	StringBuilder salt = new StringBuilder();
	Random rnd = new Random();
	while (salt.length() < 4) { // length of the random string.
	    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	    salt.append(SALTCHARS.charAt(index));
	}
	String saltStr = salt.toString();
	return saltStr;

    }

    public String getSaltStringNumber() {
	String SALTCHARS = "0123456789";
	StringBuilder salt = new StringBuilder();
	Random rnd = new Random();
	while (salt.length() < 4) { // length of the random string.
	    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	    salt.append(SALTCHARS.charAt(index));
	}
	String saltStr = salt.toString();
	return saltStr;

    }

    public String getSaltStringSpecial() {
	String SALTCHARS = "@*()_-\\/.";
	StringBuilder salt = new StringBuilder();
	Random rnd = new Random();
	while (salt.length() < 4) { // length of the random string.
	    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	    salt.append(SALTCHARS.charAt(index));
	}
	String saltStr = salt.toString();
	return saltStr;

    }

    public void waitForPageLoaded() {
	ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	 //   @Override
	    public Boolean apply(WebDriver driver) {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
			.equals("complete");
	    }
	};
	try {
	    Thread.sleep(1000);
	    WebDriverWait wait = new WebDriverWait(driver, 100);
	    wait.until(expectation);
	} catch (Throwable error) {
	    Assert.fail("Timeout waiting for Page Load Request to complete.");
	}
    }

    public void waitForDropDownPopulate(WebDriver driver, final Select dropDown) {
	WebDriverWait wait = new WebDriverWait(driver, 30);
	try {
	    wait.until(new ExpectedCondition<Boolean>() {
	//	@Override
		public Boolean apply(WebDriver driver) {
		    List<WebElement> list = dropDown.getOptions();
		    if (list.size() < 4) {
			return false;
		    } else {
			return true;
		    }
		}
	    });
	} catch (Exception e) {
	    System.out.println(e);
	}
    }

    public void Sort2Strings(String string1, String string2) {
	String[] array1 = { string1.toLowerCase(), string2.toLowerCase() };
	String[] array2 = { string1.toLowerCase(), string2.toLowerCase() };
	Arrays.sort(array2);
	Assert.assertTrue(Arrays.equals(array1, array2));
    }

    public void Sort2Dates(String Date1, String Date2) throws ParseException {
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date DateRes1 = df.parse(Date1);
	Date DateRes2 = df.parse(Date2);

	if (DateRes1.compareTo(DateRes2) < 0) {
	    System.out.println("Sort By Date" + "Pass");
	}

	else {
	    System.out.println("Sort By Date" + "Fail");
	}

    }

//.To Print Future Dates
    public static String addDaysToDate(String date, String days) {
	Calendar c = Calendar.getInstance();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	try {
	    Date myDate = df.parse(date.trim());
	    c.setTime(myDate);
	    c.add(Calendar.DATE, Integer.parseInt(days));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	String toDate = df.format(c.getTime());
	return toDate;
    }

//.To Print Past Dates
    public static String subtractDaysFromDate(String date, String days) {
	Calendar c = Calendar.getInstance();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	try {
	    Date myDate = df.parse(date.trim());
	    c.setTime(myDate);
	    c.add(Calendar.DATE, (Integer.parseInt(days) * -1));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	String toDate = df.format(c.getTime());
	return toDate;
    }

}
