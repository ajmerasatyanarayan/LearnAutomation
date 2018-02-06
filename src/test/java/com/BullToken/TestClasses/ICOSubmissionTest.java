package com.BullToken.TestClasses;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.BullTokenCommunity.Model.ICOBasicDetailModel;
import com.BullTokenCommunity.PageObject.SubmitICOStep1;
import com.BullTokenCommunity.Repositories.SubmitICORepositiory;
import com.BullTokenCommunity.TestBase.TestBase;

public class ICOSubmissionTest extends TestBase 
{
	public static final Logger log = Logger.getLogger(LoginTest.class.getName());
	SubmitICOStep1 icosubmit;
	SubmitICORepositiory sumbitICORepo=new SubmitICORepositiory();
	
	@Test(priority=1)
	public void TestEmptyFieldValidation()
	{
		icosubmit=new SubmitICOStep1(driver);
		icosubmit.ClickOnICOSubmissionMenu();
		icosubmit.TestEmptyIcoName();
		icosubmit.TestEmptyIcoURL();
		icosubmit.TestEmptyIcoCategory();
		icosubmit.TestEmptyIcoCountry();
		icosubmit.TestEmptyIcoDescrition();
		icosubmit.TestEmptyCompanyName();
		icosubmit.TestEmptyWhitepaper();
		icosubmit.TestEmptyFacebook();
		icosubmit.TestEmptyLinkdin();
	}
	@Test(priority=2)
	public void SumbitBasicICODetailTest() throws InvalidFormatException, IOException
	{
		ICOBasicDetailModel data=sumbitICORepo.Get();
		icosubmit=new SubmitICOStep1(driver);
		icosubmit.SubmitICOBasicDetails(data);
	}
}
