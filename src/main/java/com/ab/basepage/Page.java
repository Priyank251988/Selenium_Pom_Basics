package com.ab.basepage;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import com.ab.listener.Custom_listener;
import com.ab.utility.ExcelReader;
import com.ab.utility.Utility;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Page {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static WebDriverWait wait;
	public static TopMenu menu;
	public static Properties  or;
	public static Properties  config;
	public static FileInputStream fis;
	public Logger log=Logger.getLogger(this.getClass().getName());
	public  ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\ab\\excel\\testdata.xlsx");
	public static String browser;
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	
	
	
	public  Page()
	{
		
		System.out.println("1111");
		try {
			// Load properties once
			if (config == null) {
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\ab\\properties\\or.properties");
				or=new Properties();
				or.load(fis);
                
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\ab\\properties\\config.properties");
				config=new Properties();
				config.load(fis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Jenkins Browser configuration
				if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
				{
					browser=System.getenv("browser");
				}
				else
				{
					browser=config.getProperty("browser");
				}
				config.setProperty("browser", browser);
				
				
				if (driver.get() == null) {
					 browser = config.getProperty("browser");
					if (browser == null) {
						throw new RuntimeException("Browser not specified in config.properties");
					}
					WebDriver wd;
					if (browser.equalsIgnoreCase("chrome")) {
						
						Map<String, Object> prefs = new HashMap<String, Object>();
						prefs.put("profile.default_content_setting_values.notifications", 2);
						prefs.put("credentials_enable_service", false);
						prefs.put("profile.password_manager_enabled", false);
						ChromeOptions options = new ChromeOptions();
						options.setExperimentalOption("prefs", prefs);
						options.addArguments("--disable-extensions");
						options.addArguments("--disable-infobars");
						wd = new ChromeDriver(options);
					} else if (browser.equalsIgnoreCase("edge")) {
						wd = new EdgeDriver();
					} else if (browser.equalsIgnoreCase("firefox")) {
						wd = new FirefoxDriver();
					} else {
						throw new RuntimeException("Unsupported browser: " + browser);
					}
					
					driver.set(wd);
					getDriver().get("https://www.zoho.com/");
					getDriver().manage().window().maximize();
					getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					wait=new WebDriverWait(getDriver(),Duration.ofSeconds(20));
					menu = new TopMenu(getDriver());
				}
				
				
		
		
				
		
		}
	
	public void waitForLoaderToDisappear(String locator) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
		} catch (Exception e) {
			// ignore - loader might not be present
		}
	}
		
		
	
	
	public void switchtoChildWindow(String title)
	{
		 Set<String> hndl= getDriver().getWindowHandles();
			Iterator<String>itr=hndl.iterator();
			while(itr.hasNext())
			{
				String windhndl=itr.next();
				getDriver().switchTo().window(windhndl);
				if(getDriver().getTitle().equals(title))
				{
					System.out.println("Switched to child window");
					break;
				}
		}
	}
	
	public boolean isElementPresent(String locator)
	{
		try {
			getDriver().findElement(org.openqa.selenium.By.xpath(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public void click(String locator)
	{
		try {
			if(locator.endsWith("_xpath"))
			{
				getDriver().findElement(By.xpath(or.getProperty(locator))).click();
				log.info("Clicked on element with locator: " + locator);
				Custom_listener.getTest().info("Clicked on element with locator: " + locator);
			}
			else if(locator.endsWith("_id"))
			{
				getDriver().findElement(By.id(or.getProperty(locator))).click();
				log.info("Clicked on element with locator: " + locator);
				Custom_listener.getTest().info("Clicked on element with locator: " + locator);
			}
			else if(locator.endsWith("_css"))
			{
				getDriver().findElement(By.cssSelector(or.getProperty(locator))).click();
				log.info("Clicked on element with locator: " + locator);
				Custom_listener.getTest().info("Clicked on element with locator: " + locator);
			}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Error clicking on element with locator: " + locator, e);
				throw new RuntimeException("Failed to click on element: " + locator, e);
		}
	}
		
		public void type(String locator, String value)
		{
			try {
				if(locator.endsWith("_xpath"))
				{
					getDriver().findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
					log.info("Typed into element with locator: " + locator + " value: " + value);
					Custom_listener.getTest().info("Typed into element with locator: " + locator + " value: " + value);
				}
				else if(locator.endsWith("_id"))
				{
					getDriver().findElement(By.id(or.getProperty(locator))).sendKeys(value);
					log.info("Typed into element with locator: " + locator + " value: " + value);
					Custom_listener.getTest().info("Typed into element with locator: " + locator + " value: " + value);
				}
				else if(locator.endsWith("_css"))
				{
					getDriver().findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
					log.info("Typed into element with locator: " + locator + " value: " + value);
					Custom_listener.getTest().info("Typed into element with locator: " + locator + " value: " + value);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Error typing into element with locator: " + locator, e);
				throw new RuntimeException("Failed to type into element: " + locator, e);
			}
		}
		
		
		public void select(String locator,String value)
		{
			WebElement dropdownElement=null;
			if(locator.endsWith("_xpath"))
			{
				dropdownElement=getDriver().findElement(By.xpath(or.getProperty(locator)));
			
			}
			else if(locator.endsWith("_id"))
			{
				dropdownElement=getDriver().findElement(By.id(or.getProperty(locator)));
			}
			else if(locator.endsWith("_css"))
			{
				dropdownElement=getDriver().findElement(By.cssSelector(or.getProperty(locator)));
			}
			Select sel=new Select(dropdownElement);
			sel.selectByVisibleText(value);
			Custom_listener.getTest().info("Selected from dropdown with locator: " + locator + " value: " + value);
				log.info("Selected from dropdown with locator: " + locator + " value: " + value);
		}
		public static void VerifyEquals(String expected, String actual)
		{
			try {
				org.testng.Assert.assertEquals(actual, expected);
			} catch (Throwable t) {
				t.printStackTrace();
				Utility.captureScreenshot();
				Custom_listener.getTest().fail("Assertion failed. Expected: " + expected + ", Actual: " + actual, MediaEntityBuilder.createScreenCaptureFromPath(Utility.scrrenshotname).build());
				
			}
		}
		
		public static void quit()
		{	
			//if(getDriver()!=null)
			//{
				getDriver().quit();
			//}
			
		}

}
