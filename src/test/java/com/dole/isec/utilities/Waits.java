/**
 * 
 */
package com.dole.isec.utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
	public void waitForElementToBeLocated(By locator,long timeOutInSeconds)
	{
		System.out.println("Waiting for"+locator.toString()+" to be located for"+timeOutInSeconds+"seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public void pageLoadTime(long timeout, TimeUnit unit)
	{
		System.out.println("Waiting for page to load for "+timeout+" "+unit);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
	}
	public void waitForTextToBePresentInElement(WebElement element,long timeOutInSeconds,String elementText)
	{
		System.out.println("Waiting for"+elementText.toString()+" to be present in the"+element+"for"+timeOutInSeconds+"seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, elementText));
	}
	public void waitToVisibleAllElements(List<WebElement> elements,long timeOutInSeconds)
	{
		System.out.println("Waiting for list"+elements.toString()+"to be present in ");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

}
