package helper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrapperdriver {

    public static WebDriver Wrapperdriver;
    static Properties prop = new Properties();
    public static int DefaultTimeOut = 25;

    public Wrapperdriver(WebDriver driver) {
	this.Wrapperdriver = driver;
    }

    public static String downloadFilepath = "/Users/tejaswini.patne/eclipse-workspace/Automation6/screenshots";


    JavascriptExecutor js = (JavascriptExecutor) Wrapperdriver;

    
    public String getTimeStamp() {
	String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	return timestamp;
    }
/*
    public static String getScreenshot() throws IOException {
	TakesScreenshot newScreen = (TakesScreenshot) Wrapperdriver;
	File SrcFile  = newScreen.getScreenshotAs(OutputType.FILE);
	File DestFile=new File(downloadFilepath);
    FileUtils.copyFile(SrcFile, DestFile);
	return downloadFilepath;
    }
    */
    public static String getScreenshot() {
	TakesScreenshot newScreen = (TakesScreenshot) Wrapperdriver;
	String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
	return "data:image/jpg;base64, " + scnShot;
    }

    public WebElement waitForElementToBeVisible(By byElement, long waitTime) {
	WebDriverWait wait = new WebDriverWait(Wrapperdriver, waitTime);
	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byElement));
	return element;
    }

    public void waitForElementToBeClickable(By byElement, long waitTime) {
	WebDriverWait wait = new WebDriverWait(Wrapperdriver, waitTime);
	wait.until(ExpectedConditions.elementToBeClickable(byElement));
    }

    public void clickOnWebElement(By by) {
	WebDriverWait wait = new WebDriverWait(Wrapperdriver, 400);
	wait.until(ExpectedConditions.elementToBeClickable(by));
	Wrapperdriver.findElement(by).click();
    }

    public void InterceptclickOnWebElement(By by) {
	Actions actions = new Actions(Wrapperdriver);
	actions.moveToElement(Wrapperdriver.findElement(by)).click().perform();
    }

    public void HardclickOnWebElement(By by) {
	Wrapperdriver.findElement(by).sendKeys(Keys.ENTER);
    }

    public int getrowcount(By by) {
	@SuppressWarnings("unchecked")
	List<WebElement> row = (List<WebElement>) Wrapperdriver.findElement(by);
	int rowcount = row.size();
	return rowcount;
    }

    public List<String> getAlldropdownOptions(By by) {
	List<String> options = new ArrayList<String>();
	for (WebElement option : new Select(Wrapperdriver.findElement(by)).getOptions()) {
	    if (option.getAttribute("value") != "")
		options.add(option.getText());
	}
	return options;
    }

    public void sendKeysToWebelement(By by, String string) {
	Wrapperdriver.findElement(by).clear();
	Wrapperdriver.findElement(by).sendKeys(string);
    }

    public void sendKeysToWebelement(By by, Keys keys) {
	Wrapperdriver.findElement(by).clear();
	Wrapperdriver.findElement(by).sendKeys(keys);
    }

    public void clearElement(By by) {
	Wrapperdriver.findElement(by).clear();
    }

    public void sendHardKeysToWebelement(By by, String string) {
	Wrapperdriver.findElement(by).sendKeys(string);
    }

    public String alltablerowcount(By by, By by1) {
	String tablecount = null;
	try {
	    int rowCount = Wrapperdriver.findElements(by).size();

	    while (webElementIsEnabled(by1)) {
		Wrapperdriver.findElement(by1).click();
		Thread.sleep(300);
		rowCount += Wrapperdriver.findElements(by).size();
	    }
	    tablecount = String.valueOf(rowCount);

	} catch (Exception ex) {
	    // System.out.println("All Notes tab grid not visible");
	}
	return tablecount;
    }

    public boolean assertLinkNotPresent(By by) {
	List<WebElement> bob = Wrapperdriver.findElements(by);
	if (bob.isEmpty() == true) {
	    return true;
	} else
	    return false;
    }

    public boolean webElementIsDisplayed(By by) {
	// Wrapperdriver.waitForAjaxRefresh();

	try {
	    if (Wrapperdriver.findElement(by).isDisplayed())
		return true;
	    else
		return false;
	} catch (Exception e) {
	    return false;
	}
    }

    public boolean webElementIsEnabled(By by) {
	// Wrapperdriver.waitForElementToBeVisible(by, 30, true);

	if (Wrapperdriver.findElement(by).isEnabled())
	    return true;
	else
	    return false;
    }

    public String getAttributeOfWebElement(By by, String attribute) {
	return Wrapperdriver.findElement(by).getAttribute(attribute);
    }

    public boolean verifyDisabledAttributeOfWebElement(By by) {
	String disabled = getAttributeOfWebElement(by, "disabled");
	System.out.println("Element is disabled : " + disabled);

	if (disabled.equals("true"))
	    return true;
	else
	    return false;

    }

    public String getTextOfWebElement(By by) {
	return Wrapperdriver.findElement(by).getText();
    }

    public boolean isCheckBoxChecked(By by) {
	return Wrapperdriver.findElement(by).isSelected();
    }

    public void selectDropDownValueByText(By selectBy, String visibleText) throws InterruptedException {
	Select dropDown = new Select(Wrapperdriver.findElement(selectBy));
	dropDown.selectByVisibleText(visibleText);
    }

    public void selectDropDownValueByValue(By selectBy, String value) {
	Select dropDown = new Select(Wrapperdriver.findElement(selectBy));
	dropDown.selectByValue(value);
    }

    public String selectDropDownValueById(By selectBy, int id) {
	Select dropDown = new Select(Wrapperdriver.findElement(selectBy));
	dropDown.selectByIndex(id);
	return null;
    }

    public int getElementCount(By by) {
	List<WebElement> ele = Wrapperdriver.findElements(by);
	return ele.size();
    }

    public void hoverOnWebElement(By toElement) {
	Actions act = new Actions(Wrapperdriver);
	org.openqa.selenium.interactions.Action hover;
	hover = act.moveToElement(Wrapperdriver.findElement(toElement)).build();
	hover.perform();
    }

    public void switchToFrame(By by) {
	Wrapperdriver.switchTo().frame(Wrapperdriver.findElement(by));
    }

    public void switchToDefaultContent() {
	Wrapperdriver.switchTo().defaultContent();
    }

    public void closeDriver() {
	Wrapperdriver.quit();
    }

    public WebElement returnwebelement(By by) throws InterruptedException {
	System.out.println("Inside find webelement function with " + by);

	Thread.sleep(10000);
	WebElement e = null;

	try {

	    WebDriverWait wait = new WebDriverWait(Wrapperdriver, 100);
	    e = wait.until(ExpectedConditions.elementToBeClickable(by));
	    return e;
	} catch (Exception e1) {
	    System.out.println("Error is " + e1.fillInStackTrace());
	}
	return null;
    }

    public void loadurl(String url) {
	System.out.println("Loading url " + url);
	Wrapperdriver.get(url);

    }

    public void scrolltoelement(By by) {
	WebElement e = Wrapperdriver.findElement(by);
	JavascriptExecutor js = ((JavascriptExecutor) Wrapperdriver);
	js.executeScript("arguments[0].scrollIntoView(true);", e);
    }

    public void settemplatefile() throws AWTException {
	Robot robot = new Robot();
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	System.out.println("Inside set template file function ");
    }

    public boolean iselementexist(String by) {
	int size = Wrapperdriver.findElements(By.xpath(by)).size();
	if (size == 0)
	    return false;
	else
	    return true;
    }

    public String getname(String xpathkey) {
	String name = Wrapperdriver.findElement(By.xpath(xpathkey)).getText();
	System.out.println("Printing name " + name);
	return name;
    }

    public boolean iselementdisplayed(String by) {

	WebElement e = Wrapperdriver.findElement(By.xpath(by));

	if (e.isDisplayed()) {
	    return true;
	} else
	    return false;

    }

    public boolean elementdisplayed(By by) {

	WebElement e = Wrapperdriver.findElement(by);

	if (e.isDisplayed()) {
	    return true;
	} else
	    return false;

    }

    public void clickOnWebElement(String xpathkey) {

	try {
	    WebElement e = null;
	    System.out.println("Inside click on webelement function with " + xpathkey);
	    WebDriverWait wait = new WebDriverWait(Wrapperdriver, 59);
	    e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathkey)));
	    e.click();
	} catch (Exception e) {
	    System.out.println("Failed to click on element " + e.fillInStackTrace());
	}
    }

    public boolean iselementclickable(By by) {

	try {
	    WebElement e = null;
	    System.out.println("Inside is element clickable function ");
	    e = Wrapperdriver.findElement(by);
	    // e.click();
	    System.out.println("Function returning true ********************* ");
	    return true;
	} catch (Exception e1) {
	    System.out.println("Element not clickable " + e1.fillInStackTrace());
	}
	System.out.println("Function returning false    ********************  ");
	return false;
    }

    public void scrolldown() throws InterruptedException {
	System.out.println("Inside scroll down function ");
	JavascriptExecutor jse = (JavascriptExecutor) Wrapperdriver;
	jse.executeScript("window.scrollBy(0,200)", "");
    }

    public void scrollup() {
	System.out.println("Inside scroll up function ");
	JavascriptExecutor jse = (JavascriptExecutor) Wrapperdriver;
	// jse.executeScript("window.scrollBy(0,-200)", "");
	jse.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public void scrollleft() throws InterruptedException {
	System.out.println("Inside scroll left function ");
	JavascriptExecutor jse = (JavascriptExecutor) Wrapperdriver;
	jse.executeScript("window.scrollBy(-2000,0)");
    }

    public void scrollright() throws InterruptedException {
	System.out.println("Inside scroll tight function ");
	JavascriptExecutor jse = (JavascriptExecutor) Wrapperdriver;
	jse.executeScript("window.scrollBy(2000,0)");
    }

    public void makingelementclickable(By by) throws InterruptedException {

	System.out.println("Inside makingelementclickable function ");

	while (!iselementclickable(by)) {
	    System.out.println("Inside while loop *************************** ");
	    JavascriptExecutor jse = (JavascriptExecutor) Wrapperdriver;
	    System.out.println("Scrolling by Y co-ordinate [150] ");
	    jse.executeScript("window.scrollBy(0,150)", "");

	}
	System.out.println("Exiting while loop ");
    }

    public void makingelementclickable(String xpathkey) throws InterruptedException {

	System.out.println("Inside makingelementclickable function ");
	int i = 0;
	while (!iselementclickable(xpathkey)) {
	    System.out.println("Inside while loop *************************** ");
	    JavascriptExecutor jse = (JavascriptExecutor) Wrapperdriver;
	    System.out.println("Scrolling down by Y co-ordinate [150] ");
	    jse.executeScript("window.scrollBy(0,150)", "");
	    i++;
	    System.out.println("In click loop " + i);
	    if (i > 5) {
		System.out.println("Breaking loop ,element not found to click ");
		break;
	    }

	}
	System.out.println("Exiting while loop ");
    }

    public boolean iselementclickable(String xpathkey) {

	try {
	    WebElement e = null;
	    System.out.println("Inside is element clickable function ");

	    // e= Wrapperdriver.findElement(By.xpath(xpathkey));
	    e = returnwebelement(xpathkey);
	    e.click();
	    System.out.println("Function returning true ********************* ");
	    return true;
	} catch (Exception e1) {
	    System.out.println("Element not clickable " + e1.fillInStackTrace());
	}
	System.out.println("Function returning false    *********************  ");
	return false;
    }

    public WebElement returnwebelement(String xpathkey) {
	System.out.println("Inside find webelement function with ***********" + xpathkey);

	WebElement e = null;

	try {

	    WebDriverWait wait = new WebDriverWait(Wrapperdriver, 59);
	    e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathkey)));

	    return e;
	} catch (Exception e1) {
	    System.out.println("Error is " + e1.fillInStackTrace());
	}
	return null;
    }

    public WebElement returnwebelementusingtext(String text) {
	System.out.println("Inside find webelement function with ***********" + text);

	WebElement e = null;

	try {

	    WebDriverWait wait = new WebDriverWait(Wrapperdriver, 59);
	    e = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(text)));
	    return e;
	} catch (Exception e1) {
	    System.out.println("Error is " + e1.fillInStackTrace());
	}
	return null;
    }

    public void OpenLinkinNewTab(By by) {
	Actions actions = new Actions(Wrapperdriver);
	WebElement e = null;
	actions.contextClick(e).sendKeys(Keys.DOWN).sendKeys(Keys.CONTROL).build().perform();

    }

    public String getHref(By by) {
	WebElement e = null;
	String s = e.getAttribute("href");
	System.out.println(s);
	return s;

    }

 /*   public void waitForLoad(WebDriver driver) {
	new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
		.executeScript("return document.readyState").equals("complete"));
    }
*/
    public void waitForElementToBePresent(By byElement, long waitTime) {
	WebDriverWait wait = new WebDriverWait(Wrapperdriver, waitTime);
	wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
    }

    public void waitForElementToBeActive(By byElement, long waitTime) {
	WebDriverWait wait = new WebDriverWait(Wrapperdriver, waitTime);
	wait.until(ExpectedConditions.elementSelectionStateToBe(byElement, true));
    }

    public List<WebElement> getAlldropdownOption(By by) {
	Select select = new Select(Wrapperdriver.findElement(by));
	return select.getOptions();
    }
    
  

    public boolean webElementIsChecked(By by) {
	try {
	    if (Wrapperdriver.findElement(by).getAttribute("checked").equals("true"))
		return true;
	    else
		return false;
	} catch (Exception e) {
	    return false;
	}
    }

}
