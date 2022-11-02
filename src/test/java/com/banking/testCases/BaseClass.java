package com.banking.testCases;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.banking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	ReadConfig rc = new ReadConfig();

	public String baseUrl = rc.getApplicationUrl();
	public String username = rc.getUserName();
	public String password = rc.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	public static String screenshotSubFolderName;

	//Setting Browser
	@Parameters("browser")
	@BeforeClass
	public void setup(@Optional("chrome") String br) {
		logger = Logger.getLogger("BankingProject");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.toLowerCase().equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", rc.getchromepath());
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver", rc.getfirefoxpath());
			driver = new FirefoxDriver();
		}
		
		//getting base url
		driver.get(baseUrl);
		driver.manage().window().maximize();

		driver.switchTo().frame("gdpr-consent-notice");

		driver.findElement(By.cssSelector("button[id = 'save']")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// driver.switchTo().defaultContent();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	// screenshots taken using dependency injection
	@AfterMethod
	public void ScreenshotCapture(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreen(result.getTestContext().getName() + "_" + result.getMethod().getMethodName() + ".png");
		}
	}

	public void captureScreen(String fileName) {
		if (screenshotSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			System.out.println("Before Formatting :" + myDateObj);
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			screenshotSubFolderName = myDateObj.format(myFormatObj);
			System.out.println("After formatting:" + screenshotSubFolderName);
		}
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("./Screenshots/" + screenshotSubFolderName + "/" + fileName);
		try {
			FileUtils.copyFile(src, target);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot Saved Successfully");

	}
	
	public String RandomString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	public String RandomNum()
	{
		String generatedstring = RandomStringUtils.randomNumeric(8);
		return(generatedstring);
	}

}
