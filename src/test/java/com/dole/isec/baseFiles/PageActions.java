/**
 * 
 */
package com.dole.isec.baseFiles;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.dole.isec.utilities.Waits;
/**
 * @author alagappan.n
 *
 */
public class PageActions {
	
WebDriver driver;
WebElement element;
Waits wait = new Waits(driver) ;

private static final Logger log = LogManager.getLogger(PageActions.class);
public PageActions(WebDriver driver )
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
			log.error("Some exception is occurring while clicking on the element" + element);
		}
	}
	public  void enterText(WebElement element, String text)
	{ 
		try {
			wait.waitForElementVisible(element, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			element.clear();
			element.sendKeys(text);
			
		}
		catch(Exception e) {
			log.error("Some exception is occurring while sending text to the element" +element);
		}
		
	}
	public  String getText(WebElement element)
	{
		String text = null;
		try {
			wait.waitForElementVisible(element, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			text = element.getText();
			
		}
		catch(Exception e){
			log.error("Some exception is occurring while getting a text of the element" +element);
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
			log.error("Some exception is occuring while displaying of the element" +element);
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
			log.error("Some exception is occuring while displaying a text" +element);
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
			log.error("Some exception is occuring while checking a checkbox" +element);
			return false;
		}
	}
	
	public String getTextFromAttribute(WebElement element)
	{
		try
		{
			wait.waitForElementVisible(element,Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			return element.getAttribute("value").toString();
		}
		catch(Exception e)
		{
			log.error("Some exception is occuring while getting the value of given"+element+"attribute");
		}
		return null;
	}
	
	public void addAttributeText(WebElement element)
	{
		List<String> listOfValues = new LinkedList<String>();
		String attributeText= getTextFromAttribute(element);
		listOfValues.add(attributeText);
	}
	
	public List<WebElement> getAllElements(List<WebElement> elements)
	{
		try {
			
			wait.waitToVisibleAllElements(elements, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			return elements;
		}
		catch(Exception e)
		{
			log.error("No elemets are found while getting this list of"+elements);
		}
		return null;
	}
	public List<String> getTextOfAllElements(List<WebElement> elements,String value)
	{
		List<String> listOfValues = new ArrayList<String>();
		try {
			
			wait.waitToVisibleAllElements(elements, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			for(WebElement element:getAllElements(elements))
			{
				listOfValues.add(getText(element));
			}
			return listOfValues;
		}
		catch(Exception e)
		{
			log.error("No elemets are found while getting this list of"+elements);
		}
		return null;
	}
	
	public void clickAnElementFromAllElements(List<WebElement> elements,String value)
	{
		try {
			
			wait.waitToVisibleAllElements(elements, Long.parseLong((BaseTest.configData("timeOutSeconds"))));
			for(WebElement element:getAllElements(elements))
			{
				if(getText(element).equalsIgnoreCase(value))
				{
					doClick(element);
				}
			}
		}
		catch(Exception e)
		{
			log.error("No elemets are found while getting this list of"+elements);
		}
	}
	
	public void selectDate(String selectedDate,WebElement currentYearElement,WebElement nextArrow,WebElement prevArrow, List<WebElement> dayElements)
	{
		try
		{
			String[] date = selectedDate.split("/");
			String selectedMonth = Month.of(Integer.parseInt(date[0])).name();
			String selectedDay=date[1];
			String selectedYear=date[2];
			String currentYear= getText(currentYearElement);
			if(!currentYear.contains(selectedYear) && !currentYear.contains(selectedMonth)) {
				do {
					if(Integer.parseInt(currentYear)< Integer.parseInt(selectedYear))
					{
						doClick(prevArrow);
						
					}else if(Integer.parseInt(currentYear)> Integer.parseInt(selectedYear))
					{
						doClick(nextArrow);
					}
				}while(currentYear.contains(selectedYear) && currentYear.contains(selectedMonth));
				
				clickAnElementFromAllElements(dayElements,selectedDay);			
				}
			else {
				clickAnElementFromAllElements(dayElements,selectedDay);
			}
			
		}catch(Exception e) {
			
		}
	}
}
