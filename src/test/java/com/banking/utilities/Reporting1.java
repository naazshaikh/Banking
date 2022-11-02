package com.banking.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting1 extends TestListenerAdapter{
		
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
		
		
		public void onStart(ITestContext testContext) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			String repName ="Test-Report-"+timeStamp+".html";
			File file = new File(".//Reports//" +repName);
			
			sparkReporter = new ExtentSparkReporter(file);
			try {
				sparkReporter.loadXMLConfig(System.getProperty("user.dir")+ "//extent-config.xml");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		    
		    extent = new ExtentReports();
		    extent.attachReporter(sparkReporter);
		    
		    extent.setSystemInfo("Host Name", "localhost");
		    extent.setSystemInfo("Environment", "QA");
		    extent.setSystemInfo("user","naaz");
		    
		    sparkReporter.config().setDocumentTitle("Banking Project");
		    sparkReporter.config().setReportName("Functional Test Reports");
		  // sparkReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		    sparkReporter.config().setTheme(Theme.DARK);
		
		}
		
		public void onTestSuccess(ITestResult tr)
		{
			System.out.println(tr.getTestContext().getName());
		System.out.println(tr.getName());
		
			test = extent.createTest(tr.getName()); //create new entry in the report
			test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed information
		}

		public void onTestFailure(ITestResult tr)
		{
			
			test = extent.createTest(tr.getName());//create new entry in the report
			test.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
			
			String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
			File f = new File(screenshotPath);
			
			if(f.exists())
			{
				test.fail("Screenshot is below :" + test.addScreenCaptureFromPath(screenshotPath));
			}
		}

		
		public void onTestSkipped(ITestResult tr) {
			test = extent.createTest(tr.getName());//create new entry in the report
			test.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		}
		
		
		public void onFinish(ITestContext testContext) 
		{
			extent.flush();
		//	Desktop.getDesktop().browse(new File(repName).toURI());
		}


		
		
}
	


