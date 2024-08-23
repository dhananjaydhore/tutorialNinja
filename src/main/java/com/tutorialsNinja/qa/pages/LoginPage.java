package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage (WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchWarnngMsg;

	
//	actions
	
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPasswordNotMatchingWarningMsgText() {
		String warningText = emailPasswordNotMatchWarnngMsg.getText();
		return warningText;
	}
}
