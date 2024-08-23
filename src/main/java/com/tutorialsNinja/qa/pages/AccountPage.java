package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsNinja.qa.utils.Utilities;

public class AccountPage {
	WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	

	@FindBy(linkText="Edit your account information")
	private WebElement editYourAccountInformationOption;
	
	
//	actions
	
	public boolean getDisplayStatusOfAccInfOption() {
		boolean displayStatus = editYourAccountInformationOption.isDisplayed();
		return displayStatus;
	}
}
