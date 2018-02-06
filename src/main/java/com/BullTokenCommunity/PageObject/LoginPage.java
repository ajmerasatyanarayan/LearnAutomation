package com.BullTokenCommunity.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.BullTokenCommunity.Model.LoginModel;
import com.BullTokenCommunity.TestBase.TestBase;

public class LoginPage extends TestBase {
	public static final Logger log = Logger.getLogger(LoginPage.class.getName());
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="username")
	WebElement UserNameTextBox;
	
	@FindBy(id="password")
	WebElement PasswordTextBox;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement LoginButton;
	
	@FindBy(xpath="//span[contains(text(),'Invalid credentials')]")
	WebElement AuthenticationFailureMessage;
	
	@FindBy(xpath="//span[contains(text(),'User doesn't exist')]")
	WebElement UserNotExistMessage;

	public void LoginWithUserDoesNotExist(LoginModel data)
	{
		if((UserNameTextBox.isDisplayed()|| UserNameTextBox.isEnabled()) && (PasswordTextBox.isEnabled()|| PasswordTextBox.isDisplayed()))
		{
			UserNameTextBox.clear();
			UserNameTextBox.sendKeys(data.UsernameNotexist);
			log("entered user name:-"+data.UsernameNotexist+" and object is "+UserNameTextBox.toString());
			PasswordTextBox.clear();
			PasswordTextBox.sendKeys(data.passwordNotexist);
			log("entered Password:-"+data.passwordNotexist+" and object is "+PasswordTextBox.toString());
			LoginButton.click();
			log("Clicked on Login Button and object is " +LoginButton.toString());
			waitForElement(driver,30,UserNotExistMessage);
			if(UserNotExistMessage.isDisplayed()==true)
			{
				UserDoesNotExistMessage();
				
			}
			
		}
		else
		{
			log("Website is not loaded and username & Password text box is not enabled");
		}	
	}
	public void LoginWithInvalidCredentials(LoginModel data)
	{
		if((UserNameTextBox.isDisplayed()|| UserNameTextBox.isEnabled()) && (PasswordTextBox.isEnabled()|| PasswordTextBox.isDisplayed()))
		{
			UserNameTextBox.clear();
			
			UserNameTextBox.sendKeys(data.Invalidusername);
			PasswordTextBox.clear();
			PasswordTextBox.sendKeys(data.invalidpassword);
			LoginButton.click();
			waitForElement(driver,30,AuthenticationFailureMessage);
			if(AuthenticationFailureMessage.isDisplayed()==true)
			{
				AuthenticationFailureMessage();
			}
			
		}
		else
		{
			log("Website is not loaded and username & Password text box is not enabled");
		}	
	}
	public void LoginWithvalidCredentials(LoginModel data)
	{
		if((UserNameTextBox.isDisplayed()|| UserNameTextBox.isEnabled()) && (PasswordTextBox.isEnabled()|| PasswordTextBox.isDisplayed()))
		{
			UserNameTextBox.clear();
			UserNameTextBox.sendKeys(data.UserName);
			PasswordTextBox.clear();
			PasswordTextBox.sendKeys(data.Password);
			LoginButton.click();
		}
	}
	public String AuthenticationFailureMessage()
	{
		log.error("Auhorization Failure message is"+ AuthenticationFailureMessage.getText());
		return AuthenticationFailureMessage.getText();
		 
	}
	public String UserDoesNotExistMessage()
	{
		log.error("Error message is "+ UserNotExistMessage.getText());
		return UserNotExistMessage.getText();
		 
	}
}

