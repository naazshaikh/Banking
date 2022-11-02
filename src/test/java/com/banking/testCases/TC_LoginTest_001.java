package com.banking.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.banking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	@Test
	public void loginTest()throws IOException {
		logger.info("url is opened");
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("username added");
		lp.setPassword(password);
		logger.warn("check");
	    lp.clickLogin();
	    
	   
	    }

}
