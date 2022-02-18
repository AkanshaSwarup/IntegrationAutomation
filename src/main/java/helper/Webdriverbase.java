package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class Webdriverbase {
	protected static WebDriver Driver;
	public static NgWebDriver ngWebDriver;
	public FirefoxProfile Ffp;
	private static DesiredCapabilities _desiredCapabilities;
	static Properties prop = new Properties();
//	public static String downloadFilepath = "/Users/tejaswini.patne/eclipse-workspace/Automation5/screenshots";

	@SuppressWarnings("deprecation")
	public static WebDriver StartWebDriver() throws FileNotFoundException {
			try {
				
				prop.load(new FileInputStream(System.getProperty("user.dir") + "/properties/config.properties"));
				
			//	prop.load(new FileInputStream("/Users/tejaswini.patne/eclipse-workspace/Automation/src/main/java/com/config/config.properties"));
				String browserName = prop.getProperty("browser");
				if (browserName == null || browserName.isEmpty()) {
					try {
						throw new Exception("browser property cannot be empty String or null");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				switch (browserName.toLowerCase()) {

				case "firefox":
					/*
					 * FirefoxProfile profile = new FirefoxProfile();
					 * profile.setPreference("browser.download.folderList", 2);
					 * profile.setPreference("browser.download.manager.showWhenStarting", false);
					 * profile.setPreference("browser.download.dir", downloadFilepath);
					 * profile.setPreference("browser.helperApps.neverAsk.openFile",
					 * "application/zip");
					 * profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					 * "application/zip");
					 * profile.setPreference("browser.helperApps.alwaysAsk.force", false);
					 * profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
					 * profile.setPreference("browser.download.manager.focusWhenStarting", false);
					 * profile.setPreference("browser.download.manager.useWindow", false);
					 * profile.setPreference("browser.download.manager.showAlertOnComplete", false);
					 * profile.setPreference("browser.download.manager.closeWhenDone", false);
					 * 
					 * profile.setPreference("intl.accept_languages", "sl"); _desiredCapabilities =
					 * DesiredCapabilities.firefox(); profile.setPreference("intl.accept_languages",
					 * "sl"); // Ffp = new FirefoxProfile {AcceptUntrustedCertificates = true };
					 * _desiredCapabilities.setJavascriptEnabled(true); Driver = new
					 * FirefoxDriver(profile);
					 */
					break;

				case "chrome":
					_desiredCapabilities = DesiredCapabilities.chrome();
					
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
				//	File file = new File("/Users/tejaswini.patne/eclipse-workspace/Automation/driver/chromedriver");
				//	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
			//		chromePrefs.put("download.default_directory", downloadFilepath);
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", chromePrefs);
					options.addArguments("enable-automation");
					options.addArguments("--window-size=1920,1080");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-extensions");
					options.addArguments("--dns-prefetch-disable");
					options.addArguments("--disable-gpu");
					options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					options.addArguments("enable-features=NetworkServiceInProcess");
					options.addArguments("disable-features=NetworkService");
					
					options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);

					
					_desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
					Driver = new ChromeDriver(_desiredCapabilities);
					ngWebDriver = new NgWebDriver((JavascriptExecutor) Driver);
					
					// To handle Private Connection
				//	options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				//	options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);

				//	WebDriver Driver = new ChromeDriver(options);
					
					break;

				case "ie":
					File ieFile = new File("./src/main/resources/Drivers/IEDriverServer.exe");
					System.setProperty("webdriver.ie.driver", ieFile.getAbsolutePath());
					_desiredCapabilities = DesiredCapabilities.internetExplorer();
					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
					caps.setCapability("ignoreZoomSetting", true);
					caps.setJavascriptEnabled(true);
					Driver = new InternetExplorerDriver(_desiredCapabilities);
					break;

				default:
					try {
						throw new Exception("please provide valid browser name");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Driver.manage().deleteAllCookies();
				Driver.manage().window().maximize();
				Driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} catch (IOException e) {
				System.out.println(e.fillInStackTrace());
			}
			return Driver;
		}

		public void WebDriverBaseTest() throws FileNotFoundException, IOException {
			Driver = StartWebDriver();


		}

	}
