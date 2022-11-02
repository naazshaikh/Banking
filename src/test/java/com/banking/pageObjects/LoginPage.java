package com.banking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;

	// constructor
	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	// Locators
	@FindBy(name = "uid")
	@CacheLookup
	WebElement txtUserName;

	@FindBy(name = "password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(css = "input[value='LOGIN']")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(css = "a[href='Logout.php']")
	WebElement linkLogout;

	// @FindBy(css="span.mat-ripple mat-button-ripple")
	// WebElement btnaccept;

	// @FindBy(id="gdpr-consent-notice")
	// public WebElement ifr;

	// public void clickaccpt()
	// {
	// btnaccept.click();
	// }

	// setMethods Action
	public void setUserName(String uname)

	{
		txtUserName.sendKeys(uname);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}

	public void clickLogout() {
		linkLogout.click();
	}
}
