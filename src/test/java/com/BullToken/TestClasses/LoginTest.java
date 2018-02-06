package com.BullToken.TestClasses;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.BullTokenCommunity.Model.LoginModel;
import com.BullTokenCommunity.PageObject.LoginPage;
import com.BullTokenCommunity.Repositories.LoginRepository;
import com.BullTokenCommunity.TestBase.TestBase;

public class LoginTest extends TestBase {
	public static final Logger log = Logger.getLogger(LoginTest.class.getName());	

	LoginPage Login;
	//String sheetName = "Login";
    LoginRepository repo= new LoginRepository();
	
	@BeforeClass
	public void setUp() throws IOException{
     init();

	}
	
	@Test(priority=2,enabled=false)
	public void verifyLoginWithUserDoesNotExist() throws InvalidFormatException, IOException
	{
		LoginModel data=repo.Get();
		log.info("=========== Starting verifyLoginWithNoSuchUserExist Test=============");
		Login = new LoginPage(driver);
		Login.LoginWithUserDoesNotExist(data);
		Assert.assertEquals("User doesn't exist",Login.UserDoesNotExistMessage());
		log.info("=========== Finished verifyLoginWithNosuchUserExist Test=============");
	}
	@Test(priority=1)
	public void verifyLoginWithInvalidUserCredentials() throws InvalidFormatException, IOException
	{
		LoginModel data=repo.Get();
		log.info("=========== Starting verifyLoginWithInvalidUserCredentials Test=============");
		Login = new LoginPage(driver);
		Login.LoginWithInvalidCredentials(data);
		Assert.assertEquals("Invalid credentials",Login.AuthenticationFailureMessage());
		log.info("=========== Finished verifyLoginWithInvalidUserCredentials Test=============");
	}
	@Test(priority=3)
	public void verifyLoginWithvalidCredentials() throws InvalidFormatException, IOException
	{
		LoginModel data=repo.Get();
		log.info("=========== Starting verifyLoginWithvalidCredentials Test=============");
		Login = new LoginPage(driver);
		Login.LoginWithvalidCredentials(data);
		//Assert.assertEquals("Please enter valid credentials",Login.AuthenticationFailureMessage());
		log.info("=========== Finished verifyLoginWithvalidCredentials Test=============");
	}

}
