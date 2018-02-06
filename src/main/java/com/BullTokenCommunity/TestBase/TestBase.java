package com.BullTokenCommunity.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public static WebDriver driver;
	// public EventFiringWebDriver driver;
	//public WebEventListener eventListener;
	public Properties OR = new Properties();
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;

	public WebDriver getDriver()
	{
		return driver;
	}
	static 
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/TestResult/TestAutomationResult.html", true);
	}

	public void init() throws IOException {
		//loadData();
		String log4jConfPath = "log4J.properties";
		PropertyConfigurator.configure(log4jConfPath);
		//System.out.println(OR.getProperty("browser"));
		//selectBrowser(OR.getProperty("browser"));
		//getUrl(OR.getProperty("url"));
		selectBrowser("FireFox");
		getUrl("http://devcommunity.bulltoken.tech/#/login");
		PageLoadTimeout();
		
	}
	public void selectBrowser(String browser) {
		System.out.println(System.getProperty("os.name"));
		if (System.getProperty("os.name").contains("Window"))
		{
			if (browser.equalsIgnoreCase("chrome"))
			{
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
				driver = new ChromeDriver();
				// driver = new EventFiringWebDriver(dr);
				// eventListener = new WebEventListener();
				// driver.register(eventListener);
			} 
			else if (browser.equalsIgnoreCase("firefox"))
			{
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				// driver = new EventFiringWebDriver(dr);
				//eventListener = new WebEventListener();
				// driver.register(eventListener);
				// setDriver(driver);
			}
		}
		else if (System.getProperty("os.name").contains("Mac"))
		{
			if (browser.equalsIgnoreCase("chrome")) 
			{
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
				driver = new ChromeDriver();
				// driver = new EventFiringWebDriver(dr);
				// eventListener = new WebEventListener();
				// driver.register(eventListener);
			} 
			else if (browser.equalsIgnoreCase("firefox"))
			{
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geckodriver");
				driver = new FirefoxDriver();
				// driver = new EventFiringWebDriver(dr);
				//eventListener = new WebEventListener();
				// driver.register(eventListener);
				// setDriver(driver);
			}
		}
	}
	
	public void getUrl(String url) 
	{
		log.info("navigating to :-" + url);
		driver.get(url);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void log(String data)
	{
		log.info(data);
		//Reporter.log(data);
		test.log(LogStatus.INFO, data);
	}
	
	public void loadData() throws IOException 
	{
		File file = new File(System.getProperty("user.dir") + "/src/main/java/com/test/automation/uiAutomation/config/config.properties");
		FileInputStream f = new FileInputStream(file);
		OR.load(f);
	}
	
	public void PageLoadTimeout()
	{
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
	}
	public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public void getresult(ITestResult result)
	{
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			//String screen = captureScreen("");
			//test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}
	
	@AfterMethod()
	public void afterMethod(ITestResult result)
	{
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result)
	{
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterSuite(alwaysRun = false)
	public void endTest() 
	{
		tearDown();
	}
	
	public void tearDown()
	{
		log.info("browser closed");
		extent.endTest(test);
		extent.flush();
        sendPDFReportByGMail("ajmera.satyanarayan@gmail.com", "sumitajmera", "imprateeksinghal@gmail.com", "Automation TestReport", "");

    }

    private static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body)
    {

    	Properties props = System.getProperties();

    	String host = "smtp.gmail.com";

    	props.put("mail.smtp.starttls.enable", "true");

    	props.put("mail.smtp.host", host);

    	props.put("mail.smtp.user", from);

    	props.put("mail.smtp.password", pass);
	
    	props.put("mail.smtp.port", "587");
    	
    	props.put("mail.smtp.auth", "true");
	
    	Session session = Session.getDefaultInstance(props);
	
    	MimeMessage message = new MimeMessage(session);
	
	try {
	
	    //Set from address
	
		message.setFrom(new InternetAddress(from));
	
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	
	//Set subject
	
		message.setSubject(subject);
	
		message.setText(body);
	
		BodyPart objMessageBodyPart = new MimeBodyPart();
	
		objMessageBodyPart.setText("Please Find The Attached Report File!");
	
		Multipart multipart = new MimeMultipart();
	
		multipart.addBodyPart(objMessageBodyPart);
	
		objMessageBodyPart = new MimeBodyPart();
	
	//Set path to the pdf report file
	
		String filename = System.getProperty("user.dir") + "/TestResult/TestAutomationResult.html";
	
	//Create data source to attach the file in mail
	
		DataSource source = new FileDataSource(filename);
	
		objMessageBodyPart.setDataHandler(new DataHandler(source));
	
		objMessageBodyPart.setFileName(filename);
	
		multipart.addBodyPart(objMessageBodyPart);
	
		message.setContent(multipart);
	
		Transport transport = session.getTransport("smtp");
		
		transport.connect(host, from, pass);
		
		transport.sendMessage(message, message.getAllRecipients());
		
		transport.close();
	
	}
	
	catch (AddressException ae) {
	
		ae.printStackTrace();
	
	}
	
	catch (MessagingException me) {
	
		me.printStackTrace();
	
	}
	
 }

}
