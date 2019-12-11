package com.dole.isec.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.dole.isec.baseFiles.BaseTest;
import com.dole.isec.baseFiles.PageActions;


public class StatusChangeMultipleCreatePage extends BaseTest {
	
	WebDriver driver;
	PageActions pageActions = new PageActions(driver);
	private static final Logger log = LogManager.getLogger(StatusChangeMultipleCreatePage.class);
	
	@FindBy(xpath="//a[@id='Request']")
	private WebElement RequestMenuDropdown;
	
	@FindBy(xpath="///a[contains(@href,'StatusChangeMultiple')]")
	private WebElement StatusCahngeMultipleSubMenu;
	
	@FindBy(xpath="//ul/li/a[text()='Request']/parent::li/following-sibling::li")
	private WebElement RequestName;
	
	@FindBy(xpath="//div[@id='txtRequestRequestor']")
	private WebElement RequesterName;
	
	@FindBy(xpath="//div[@id='txtRequestReqType']")
	private WebElement RequestType;
	
	@FindBy(xpath="//div[@id='txtRequestReferenceCode']")
	private WebElement RequestorReferenceCode;
	
	@FindBy(xpath="//i[@id='username']/parent::a")
	private WebElement Username;
	
	@FindBy(xpath="//a[@id='loggedInUserName']")
	private WebElement LoggedUserDropdown;
	
	@FindBy(xpath="//div[@id='divStatusChange']/div/a")
	private WebElement AddEmployeesLink;
	
	@FindBy(xpath="//div[@id='s2id_autogen1']")
	private WebElement AddEmployeeDropdown;
	
	@FindBy(xpath="//label[text()[normalize-space()='User to model']]/following::input[3]")
	private WebElement DropdownSearchBox;
	
	@FindBy(xpath="//label[text()[normalize-space()='User to model']]/following::ul[2]//div")
	private List<WebElement> DropdownSearchResult;
	
	@FindBy(xpath="//div[@id='divStatusChange']//button[@type='submit']")
	private WebElement TickButton;
	
	@FindBy(xpath="//div[@id='divStatusChange']//button[@type='button']")
	private WebElement CancleButton;
	
	@FindBy(xpath="//td//span[text()='Edit']")
	private WebElement EmployeeEditLink;
	
	@FindBy(xpath="//div[@id='s2id_cboEmployeeStatusChange']")
	private WebElement EditEmployeeDropdown;
	
	@FindBy(id="txtStatusChangeFirstName")
	private WebElement EditEmployeeFirstname;
	
	@FindBy(id="txtStatusChangeMiddleName")
	private WebElement EditEmployeeMiddlename;
	
	@FindBy(id="txtStatusChangeLastName")
	private WebElement EditEmployeeLastname;
	
	@FindBy(id="txtStatusChangeHireDate")
	private WebElement EditEmployeeHireDate;
	
	@FindBy(id="txtStatusChangeEndDate")
	private WebElement EditEmployeeEndDate;
	
	@FindBy(id="txtStatusChangeEmpShName")
	private WebElement EditHeatID;
	
	@FindBy(id="txtStatusChangeEmpShName")
	private WebElement EditeHRDivisionDropdown;
	
	@FindBy(id="btnEmployeeStatusChangeSave")
	private WebElement EditSaveButton;
	
	@FindBy(xpath="//h3[text()='Edit Employee']/preceding-sibling::button")
	private WebElement EditFormCancelButton;
	
	
	
	
	void clickOnRequestMenu()
	{
		log.info("Cliked on Request Menu");
		pageActions.doClick(RequestMenuDropdown);
	}
	
	void ClickonStatusChangeMultiple()
	{
		clickOnRequestMenu();
		log.info("Clicked on Status Change Multiple");
		pageActions.doClick(StatusCahngeMultipleSubMenu);
	}
	void clickOnAddEmployeeLink()
	{
		
		if(AddEmployeesLink.isDisplayed()==true) {
			pageActions.doClick(AddEmployeesLink);
		}
		else
		{
			log.error("The Add Employee Link is not visible and Element status is"+AddEmployeesLink.isDisplayed());
		}
	}
	
	void typeEmployeeName(String searchText)
	{
		pageActions.doClick(AddEmployeeDropdown);
		pageActions.enterText(DropdownSearchBox, searchText);
	}

	void selectEmployeeName(String employeeName)
	{
		
		typeEmployeeName(employeeName);
		for(WebElement element:pageActions.getAllElements(DropdownSearchResult))
		{
			if(element.getText().equalsIgnoreCase(employeeName)){
			pageActions.doClick(element);
					}
		}
	}
	
	List<String> getEmployeeNameList(String searchText)
	{
		List<String> EmployeeList= new ArrayList<String>();
	
		for(WebElement element:pageActions.getAllElements(DropdownSearchResult))
		{
			if(element.getText().contains(searchText)) {
		 EmployeeList.add(pageActions.getText(element));
			}
			else
			{
				log.error("No Employee found for searching name:"+searchText);
			}
		}
		return	EmployeeList;
	}
	String getRequesterName()
	{
		try {
		return pageActions.getText(RequesterName);
		}
		catch(Exception e)
		{
			log.error("Some exception is occuring while geting the Requestor Name");
			return null;
		}
	}
	String getRequestType()
	{
		try {
			return pageActions.getText(RequestType);
		}catch(Exception e){
			log.error("Some exception is occuring while geting the Requestor Name");
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
