/**
 * 
 */
package com.dole.isec.baseFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.dole.isec.pageObjects.StatusChangeMultipleCreatePage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author alagappan.n
 *
 */
public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop;
	private static final Logger log = LogManager.getLogger(StatusChangeMultipleCreatePage.class);
	
	public static void loadData()
	{
		try {
		prop = new Properties();
	
			FileInputStream ip = new FileInputStream(getFilePath("\\src\\test\\java\\com\\dole\\isec\\config\\config.properties"));
			try {
				prop.load(ip);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
	}
	
	public static String configData(String dataName) {
		loadData();
		return prop.getProperty(dataName);
	}
	
	public void openBrowser()
	{
		
		String browserName = configData("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	}
	@Parameters({"browser"})
	public void openBrowsersInParallel()
	{
		String browser = "";
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
	}
	
	public  void getURL()
	{
		
		driver.get(configData("url"));
		
		//System.out.println(configData("url"));
	}
	@BeforeClass
	public void lanuchApplication()
	{
		String runMode = configData("runMode");
		if(runMode.equalsIgnoreCase("Non-parallel"))
		{
			openBrowser();
		}
		else if (runMode.equalsIgnoreCase("Parallel"))
		{
			openBrowsersInParallel();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		getURL();
	}
	
	public static void captureScreenshot(String testCaseName) {
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("dd-MM-YYYY_hh:mm").format(new Date());
		try {
			FileUtils.copyFile(srcFile, new File (getFilePath("\\resource\\Screenshots\\")+testCaseName+timeStamp+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFilePath(String path)
	{
		String basepath = System.getProperty("user.dir");
		return basepath+"\\"+path;
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		driver = null;
	}
	
	public static void main(String[] args) {
	//System.out.println(getFilePath(configData("testDataFilePath")));
		
	}
	


}
