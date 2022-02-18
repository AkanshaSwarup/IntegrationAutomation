package utility;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import helper.Wrapperdriver;

public class BaseTest{
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static WebDriver driver;
    public static TakesScreenshot ts;
    Wrapperdriver wrapperdriver;

    @BeforeSuite
    public void setUp() {
    	
    
    	htmlReporter = new ExtentHtmlReporter(
    			System.getProperty("user.dir") + "/test-output/IntegrationReport/FieldMapReport.html");
    		extent = new ExtentReports();
    		extent.attachReporter(htmlReporter);
    		extent.setSystemInfo("OS", "Mac");
    		extent.setSystemInfo("Environment", "QA");
    		extent.setSystemInfo("User Name", "Integrationqa");
    		extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    		htmlReporter.config().setDocumentTitle("Integration Regression Suite");
    		htmlReporter.config().setReportName("Integration UI Automation Report");
    		htmlReporter.config().setTheme(Theme.DARK);
    
    }
    
    @AfterMethod
    public void getResult(ITestResult result) throws Exception {

	if (result.getStatus() == ITestResult.FAILURE) {
	    test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case is FAILED due to below issues:",
		    ExtentColor.RED));
	    test.fail(result.getThrowable());
	    test.info("Click Here for Screenshot:",
		    MediaEntityBuilder.createScreenCaptureFromBase64String(Wrapperdriver.getScreenshot()).build());

	} else if (result.getStatus() == ITestResult.SUCCESS) {
	    test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));

	} else {
	    test.log(Status.SKIP,
		    MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
	    test.skip(result.getThrowable());
	}
    }

    @AfterSuite
    public void tearDown() {
	extent.flush();
    }
}
