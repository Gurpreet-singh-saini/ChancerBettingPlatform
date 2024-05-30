package com.QA.BaseClass;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.apache.commons.mail.*;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.QA.Utilities.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import java.io.File;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass implements DataProviders {

	public static WebDriver driver;
	ReadConfig ReadConfig_obj = new ReadConfig();
	public String baseURL = ReadConfig_obj.getApplicationURL();
	String browser = ReadConfig_obj.getBrowser();


	public static Logger logger;

	@BeforeSuite
	public void setup() {
		// System.setProperty("webdriver.chrome.driver",ReadConfig_obj.ChromeDriverPath());

		// launch browser
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver",ReadConfig_obj.ChromeDriverPath());
	        DesiredCapabilities dcap = new DesiredCapabilities();
	        dcap.setCapability("pageLoadStrategy", "normal");
	        ChromeOptions opt = new ChromeOptions();
	        opt.merge(dcap);
			driver = new ChromeDriver(opt);
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}

		// implicit wait of 10 secs
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// for logging
		logger = LogManager.getLogger("BaseClass");
		driver.manage().window().maximize();
		logger.info("Window maximized");

	}

	@AfterClass
	public void tearDown() {
		// driver.close();
		// driver.quit();
	}
	
	
	@AfterSuite
	public static void SendEmail()  {
		

		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication("gurpreet.singh01@antiersolutions.com", "Password");

					}

				});


		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress("gurpreet.singh01@antiersolutions.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("gurpreet.singh01@antiersolutions.com, yesican8059@gmail.com"));
            
			String SubjectName = "Chancer(Regression Testing Status)";
                        // Add the subject link
			message.setSubject(SubjectName);

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
	
			messageBodyPart1.setText("Hello, Good day! \n"
					+ "\n"
					+ "All scenarios have been executed. Please find the attached report of the execution.  \n"
					+ "For better results please download the file first. Then see the progress of the Regression Testing.  \n"
					+ "Thanks,\n"
					+ "\n"
					+ "Regards\n"
					+ "Gurpreet Singh\n"
					+ "QA Analyst." );

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = System.getProperty("user.dir") + "\\Reports\\" + "Chancer(RegressionTesting).html";
			//DDX(Regression Testing).html

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(filename);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}
	}
	
	
	// user method to capture screen shot
	public void captureScreenShot(WebDriver driver, String testName) throws IOException {
		// step1: convert webdriver object to TakesScreenshot interface
		TakesScreenshot screenshot = ((TakesScreenshot) driver);

		// step2: call getScreenshotAs method to create image file

		File src = screenshot.getScreenshotAs(OutputType.FILE);

		File dest = new File(System.getProperty("user.dir") + "//Screenshots//" + testName + ".png");

		// step3: copy image file to destination
		FileUtils.copyFile(src, dest);
	}

	@DataProvider(name = "AllData")
	public String[][] AllDataProvider() {
		// TODO Auto-generated method stub
		String fName = System.getProperty("user.dir")+"//TestData//TestSheetData.xlsx";
		
		int ttlRowCnt = ReadExcelFile.getRowCount(fName, "SignUpTestSheetData");
		int ttlColCnt= ReadExcelFile.getColCount(fName, "SignUpTestSheetData");
		
		String userData[][] = new String[ttlRowCnt-1][ttlColCnt];
		
		for(int rowNo = 1; rowNo<ttlRowCnt; rowNo++)
		{
			for(int colNo=0; colNo<ttlColCnt; colNo++)
			{
				userData[rowNo-1][colNo] = ReadExcelFile.getCellValue(fName, "SignUpTestSheetData", rowNo, colNo);
			}
			
		}
		return userData;
	}

}
