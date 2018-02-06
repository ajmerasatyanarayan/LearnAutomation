package com.BullTokenCommunity.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.BullTokenCommunity.Model.ICOBasicDetailModel;
import com.BullTokenCommunity.TestBase.TestBase;

public class SubmitICOStep1 extends TestBase{
	public static final Logger log = Logger.getLogger(LoginPage.class.getName());
	WebDriver driver;
	
	public SubmitICOStep1(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[text()='ICO Submission']")
	WebElement ICOSubmissionMenu;
	
	@FindBy(id="name")
	WebElement ICONameTextBox;
	
	@FindBy(id="url")
	WebElement ICOURLTextBox;
	
	@FindBy(id="category")
	WebElement IcoCategoryDropdown;
	
	@FindBy(id="country_of_issue")
	WebElement CountryDropdown;
	
	@FindBy(id="description")
	WebElement DescriptionTextArea;
	
	@FindBy(id="company_name")
	WebElement CompanyNameTextBox;
	
	@FindBy(id="whitepaper_link")
	WebElement WhitepaperTextBox;
	
	@FindBy(id="lightpaper_link")
	WebElement LightPaperTextBox;
	
	@FindBy(id="github_link")
	WebElement GithubLink;
	
	@FindBy(id="facebook_link")
	WebElement FacebookTextbox;
	
	@FindBy(id="linkedin_link")
	WebElement LinkdinTextBox;
	
	@FindBy(xpath="//small[@class='text-danger' and contains(text(),'Please enter ICO Name')]")
	WebElement ICONameValidationMessage;
	
	@FindBy(xpath="//small[@class='text-danger' and contains(text(),'Please enter ICO URL')]")
	WebElement ICOURLValidationMessage;
	
	@FindBy(xpath="//small[@class='text-danger' and contains(text(),'Please select category')]")
	WebElement CategoryDropdownValidationMessage; 
	
	@FindBy(xpath="//small[@class='text-danger' and contains(text(),'Please select country')]")
	WebElement CountryDropdownValidationMessage;
	
	@FindBy(xpath="//small[@class='text-danger' and contains(text(),'Please enter detail of ICO')]")
	WebElement TextAreaValidationMessage;
	
	@FindBy(xpath="//small[@class='text-danger' and contains(text(),'Please enter company name')]")
	WebElement CompanyNameValidationMessage;
	
	@FindBy(xpath="//small[@class='text-danger' and contains(text(),'Please enter whitepaper link')]")
	WebElement WhitepaperValidationMessage;
	
	@FindBy(xpath="//small[@class='text-danger' and contains(text(),'Please enter facebook link')]")
	WebElement FacebookValidationMessage;
	
	@FindBy(xpath="//small[@class='text-danger' and contains(text(),'Please enter Linkedin link')]")
	WebElement LinkdinValidationMessage;
	
	public void ClickOnICOSubmissionMenu()
	{
		waitForElement(driver, 30, ICOSubmissionMenu);
		ICOSubmissionMenu.click();
	}
	public void TestEmptyIcoName()
	{
		ICONameTextBox.clear();
		ICONameTextBox.sendKeys(Keys.TAB);
		waitForElement(driver, 10, ICONameValidationMessage);
		if(ICONameValidationMessage.isDisplayed()==true)
		{
			GetICONameValidationMessage();
		}
		
	}
	public void TestEmptyIcoURL()
	{
		ICOURLTextBox.clear();
		ICOURLTextBox.sendKeys(Keys.TAB);
		waitForElement(driver, 30, ICOURLValidationMessage);
		if(ICOURLValidationMessage.isDisplayed()==true)
		{
			GetICOURLValidationMessage();
		}
	}
	public void TestEmptyIcoCategory()
	{
		
		IcoCategoryDropdown.sendKeys(Keys.TAB);
		waitForElement(driver, 30, CategoryDropdownValidationMessage);
		if(CategoryDropdownValidationMessage.isDisplayed()==true)
		{
			GetICOCategoryValidationMessage();
		}
	}
	public void TestEmptyIcoCountry()
	{
		
		CountryDropdown.sendKeys(Keys.TAB);
		waitForElement(driver, 30, CountryDropdownValidationMessage);
		if(CountryDropdownValidationMessage.isDisplayed()==true)
		{
			GetCountryValidationMessage();
		}
	}
	public void TestEmptyIcoDescrition()
	{
		
		DescriptionTextArea.sendKeys(Keys.TAB);
		waitForElement(driver, 30, TextAreaValidationMessage);
		if(TextAreaValidationMessage.isDisplayed()==true)
		{
			GetDescriptionErrorMessage();
		}
	}
	public void TestEmptyCompanyName()
	{
		
		CompanyNameTextBox.sendKeys(Keys.TAB);
		waitForElement(driver, 30, CompanyNameValidationMessage);
		if(CompanyNameValidationMessage.isDisplayed()==true)
		{
			GetCompanyNameErrorMessage();
		}
	}
	public void TestEmptyWhitepaper()
	{
		
		WhitepaperTextBox.sendKeys(Keys.TAB);
		waitForElement(driver, 30, WhitepaperValidationMessage);
		if(WhitepaperValidationMessage.isDisplayed()==true)
		{
			GetWhitepaperErrorMessage();
		}
	}
	public void TestEmptyFacebook()
	{
		
		FacebookTextbox.sendKeys(Keys.TAB);
		waitForElement(driver, 30, FacebookValidationMessage);
		if(FacebookValidationMessage.isDisplayed()==true)
		{
			GetFacebookErrorMessage();
		}
	}
	public void TestEmptyLinkdin()
	{
		
		LinkdinTextBox.sendKeys(Keys.TAB);
		waitForElement(driver, 30, LinkdinValidationMessage);
		if(LinkdinValidationMessage.isDisplayed()==true)
		{
			GetLinkdinErrorMessage();
		}
	}
	
	public void ClearAllFields()
	{
		ICONameTextBox.clear();
		ICOURLTextBox.clear();
		DescriptionTextArea.clear();
		CompanyNameTextBox.clear();
		WhitepaperTextBox.clear();
		FacebookTextbox.clear();
		LinkdinTextBox.clear();
		
	}
	public void SubmitICOBasicDetails(ICOBasicDetailModel data)
	{
		ClearAllFields();
		ICONameTextBox.sendKeys(data.ICOName);
		ICOURLTextBox.sendKeys(data.ICOURL);
		waitForElement(driver, 10, IcoCategoryDropdown);
		Select ICOCateg=new Select(IcoCategoryDropdown);
		waitForElement(driver, 10, IcoCategoryDropdown);
		ICOCateg.selectByVisibleText(data.ICOcategory);
		waitForElement(driver, 10, IcoCategoryDropdown);
		Select Country=new Select(CountryDropdown);
		waitForElement(driver, 10, IcoCategoryDropdown);
		Country.selectByVisibleText(data.ICOCountry);
		DescriptionTextArea.sendKeys(data.IcoDeescription);
		CompanyNameTextBox.sendKeys(data.CompanyName);
		LightPaperTextBox.sendKeys(data.LightpaperLink);
		WhitepaperTextBox.sendKeys(data.WhitepaperLink);
		FacebookTextbox.sendKeys(data.FacebookUrl);
		LinkdinTextBox.sendKeys(data.LinkdinUrl);
		GithubLink.sendKeys(data.GithubUrl);
		
	}
	public String GetICONameValidationMessage()
	{
		return ICONameValidationMessage.getText();	
	}
	
	public String GetICOURLValidationMessage()
	{
		return ICOURLValidationMessage.getText();
	}
	public String GetICOCategoryValidationMessage()
	{
		return CategoryDropdownValidationMessage.getText();
	}
	public String GetCountryValidationMessage()
	{
		return CountryDropdownValidationMessage.getText();
	}
	public String GetDescriptionErrorMessage()
	{
		return TextAreaValidationMessage.getText();
	}
	public String GetCompanyNameErrorMessage()
	{
		return CompanyNameValidationMessage.getText();
	}
	public String GetWhitepaperErrorMessage()
	{
		return WhitepaperValidationMessage.getText();
	}
	public String GetFacebookErrorMessage()
	{
		return FacebookValidationMessage.getText();
	}
	public String GetLinkdinErrorMessage()
	{
		return LinkdinValidationMessage.getText();
	}
}


