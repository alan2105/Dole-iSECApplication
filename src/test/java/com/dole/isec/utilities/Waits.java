/**
 * 
 */
package com.dole.isec.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author alagappan.n
 *
 */
public class Waits {
	
	WebDriver driver;
	
	public Waits(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public  void setImplictWait(long timeout, TimeUnit unit)
	{
		System.out.println("Implict Wait has been set to "+timeout+" "+unit);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	
	public void waitForElementVisible(WebElement element,long timeOutInSeconds)
	{
		System.out.println("Waiting for"+element.toString()+"to visible for"+timeOutInSeconds+"seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeClickable(WebElement element,long timeOutInSeconds)
	{
		System.out.println("Waiting for"+element.toString()+" to become clickable for"+timeOutInSeconds+"seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void pageLoadTime(long timeout, TimeUnit unit)
	{
		System.out.println("Waiting for page to load for "+timeout+" "+unit);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
	}

}
