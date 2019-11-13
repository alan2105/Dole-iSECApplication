/**
 * 
 */
package com.dole.isec.baseFiles;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dole.isec.utilities.Waits;

/**
 * @author alagappan.n
 *
 */
public class BasePage {
	
WebDriver driver;
WebElement element;
Waits wait = new Waits(driver) ;


public BasePage(WebDriver driver )
{
	this.driver = driver;
}


	public void doClick(WebElement element)
	{
		try
		{
			wait.waitForElementToBeClickable(element, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			element.click();
		}
		catch(Exception e)
		{
			System.out.println("Some exception is occurring while clicking on the element" + element);
		}
	}
	
//	public void onSelct()
//	{
//		
//	}
	
	public void enterText(WebElement element, String text)
	{ 
		try {
			wait.waitForElementVisible(element, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			element.clear();
			element.sendKeys(text);
			
		}
		catch(Exception e) {
			System.out.println("Some exception is occurring while sending text to the element" +element);
		}
		
	}
	public String getText(WebElement element)
	{
		String text = null;
		try {
			wait.waitForElementVisible(element, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			text = element.getText();
			
		}
		catch(Exception e){
			System.out.println("Some exception is occurring while getting a text of the element" +element);
		}
		return text;
	}
	public boolean isElementdisplayed(WebElement element)
	{
		try {	
			wait.waitForElementVisible(element, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			return element.isDisplayed();	
		}
		catch(Exception e) {
			System.out.println("Some exception is occuring while displaying of the element" +element);
			return false;
		}
	}
	public boolean isTextDisplayed(WebElement element)
	{
		try {
			wait.waitForElementVisible(element, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			return getText(element).isEmpty();
		}
		catch(Exception e)
		{
			System.out.println("Some exception is occuring while displaying a text" +element);
			return false;
		}
	}
	
	public boolean isCheckboxSelected(WebElement element) {
		
		try {
			wait.waitForElementVisible(element, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			return element.isSelected();	
		}
		catch(Exception e)
		{
			System.out.println("Some exception is occuring while checking a checkbox" +element);
			return false;
		}
	}
}
