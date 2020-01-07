package com.dole.isec.pageObjects;

import java.time.Month;
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
	private WebElement EditHRDivisionDropdown;
	
	@FindBy(id="btnEmployeeStatusChangeSave")
	private WebElement EditFormSaveButton;
	
	@FindBy(xpath="//h3[text()='Edit Employee']/preceding-sibling::button")
	private WebElement EditFormCancelButton;
	
	@FindBy(xpath="(//div[@class='datepicker-months']//th[@class='next'])[1]")
	private WebElement hireDateNextArrow;
	
	@FindBy(xpath="(//div[@class='datepicker-months']//th[@class='prev'])[1]")
	private WebElement hireDatePrevArrow;
	
	@FindBy(xpath="(//div[@class='datepicker-months']//th[@class='prev']/following-sibling::th[1])[1]")
	private WebElement hireDateCurrentMonthYear;
	
	@FindBy(xpath="(//div[@class='datepicker-days']//tbody)[1]//td[not(self::td[@class='day old' or @class='day new'])]")
	private List<WebElement> hireDateDaysInMDatePicker;
	
	@FindBy(xpath="(//div[@class='datepicker-months']//tbody//td)[1]/span")
	private List<WebElement> hireDateMonthsInDatePicker;
	
	@FindBy(xpath="(//div[@class='datepicker-months']//th[@class='next'])[2]")
	private WebElement endDateNextArrow;
	
	@FindBy(xpath="(//div[@class='datepicker-months']//th[@class='prev'])[2]")
	private WebElement endDatePrevArrow;
	
	@FindBy(xpath="(//div[@class='datepicker-months']//th[@class='prev']/following-sibling::th[1])[2]")
	private WebElement endDateCurrentMonthYear;
	
	@FindBy(xpath="(//div[@class='datepicker-days']//tbody)[2]//td[not(self::td[@class='day old' or @class='day new'])]")
	private List<WebElement> endDateDaysInMDatePicker;
	
	@FindBy(xpath="(//div[@class='datepicker-months']//tbody//td)[2]/span")
	private List<WebElement> endDateMonthsInDatePicker;
	
	@FindBy(xpath="//table[@id='tblEmpStatusChangePV']/tbody/tr")
	private List<WebElement> SelectedStatusChangeEmp;
	
	@FindBy(xpath="//textarea[@id='txtMemo']")
	private WebElement memoTextbox;
	
	@FindBy(xpath = "//div[@id='divAssignedToWrap']//a")
	private WebElement AssignedToDropdown;
	
	
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
	
	public void selectHireDate(String selectedDate)
	{
		try
		{
			String[] date = selectedDate.split("/");
			String selectedMonth = Month.of(Integer.parseInt(date[0])).name();
			String selectedDay=date[1];
			String selectedYear=date[2];
			String currentYear= pageActions.getText(hireDateCurrentMonthYear);
			if(!currentYear.contains(selectedYear)) {
				do {
					pageActions.doClick(hireDateCurrentMonthYear);
					if(Integer.parseInt(currentYear)< Integer.parseInt(selectedYear))
					{
						pageActions.doClick(hireDatePrevArrow);
						
					}else if(Integer.parseInt(currentYear)> Integer.parseInt(selectedYear))
					{
						pageActions.doClick(hireDateNextArrow);
					}
				}while(currentYear.contains(selectedYear));
				pageActions.clickAnElementFromAllElements(hireDateMonthsInDatePicker,selectedMonth);;
				pageActions.clickAnElementFromAllElements(hireDateDaysInMDatePicker,selectedDay);			
				}
			else {
				pageActions.clickAnElementFromAllElements(hireDateDaysInMDatePicker,selectedDay);
			}
			
		}catch(Exception e) {
			
		}
	}
	
	public void selectEndDate(String selectedDate)
	{
		try
		{
			String[] date = selectedDate.split("/");
			String selectedMonth = Month.of(Integer.parseInt(date[0])).name();
			String selectedDay=date[1];
			String selectedYear=date[2];
			String currentYear= pageActions.getText(hireDateCurrentMonthYear);
			if(!currentYear.contains(selectedYear)) {
				do {
					pageActions.doClick(hireDateCurrentMonthYear);
					if(Integer.parseInt(currentYear)< Integer.parseInt(selectedYear))
					{
						pageActions.doClick(hireDatePrevArrow);
						
					}else if(Integer.parseInt(currentYear)> Integer.parseInt(selectedYear))
					{
						pageActions.doClick(hireDateNextArrow);
					}
				}while(currentYear.contains(selectedYear));
				pageActions.clickAnElementFromAllElements(hireDateMonthsInDatePicker,selectedMonth);;
				pageActions.clickAnElementFromAllElements(hireDateDaysInMDatePicker,selectedDay);			
				}
			else {
				pageActions.clickAnElementFromAllElements(hireDateDaysInMDatePicker,selectedDay);
			}
			
		}catch(Exception e) {
			
		}
	}
	
	void enterMemo(String memoText)
	{
		try
		{
		pageActions.enterText(memoTextbox, memoText);
	}catch(Exception e)
		{
		log.error("Some exception is occuring while trying to enter the memo text" +e);
		}
		
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

