package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-firstname")
	private WebElement firstNameInputField;
	
	@FindBy(id="input-lastname")
	private WebElement lasttNameInputField;
	
	@FindBy(id="input-email")
	private WebElement emailInputField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneInputField;
	
	@FindBy(id="input-password")
	private WebElement passwordInputField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordInputField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
    private WebElement yesOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstnameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastnameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	

	
//	Actions
	
	public void fillAllMandetoryField(String firstname, String lastName, String email, String telephone, String password, String confirmPassword) {
		firstNameInputField.sendKeys(firstname);
		lasttNameInputField.sendKeys(lastName);
		emailInputField.sendKeys(email);
		telephoneInputField.sendKeys(telephone);
		passwordInputField.sendKeys(password);
		confirmPasswordInputField.sendKeys(confirmPassword);
		
	}
	
	public void clickOnYesOption() {
		yesOption.click();
	}
	
	public void clickOnPrivacyPolicy() {
		privacyPolicyField.click();
		
	}
	
	public AccountSuccessPage clickOnContinue() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String duplicateEmailWarningMsg() {
		String duplicateWarningMsg = duplicateEmailWarning.getText();
		return duplicateWarningMsg;
	}
	
	public String getPrivacyPolicyWarning() {
		String privacyPolicyWarningmsg = privacyPolicyWarning.getText();
		return privacyPolicyWarningmsg;
	}
	
	public String getFirstNameWarning() {
		String firstNameWarningmsg=firstnameWarning.getText();
		return firstNameWarningmsg;
	}
	
	public String getLasttNameWarning() {
		String lastNameWarningmsg=lastnameWarning.getText();
		return lastNameWarningmsg;
	}
	public String getEmailWarning() {
		String emailWarningmsg=emailWarning.getText();
		return emailWarningmsg;
	}
	public String gettelephoneWarning() {
		String telephoneWarningmsg=telephoneWarning.getText();
		return telephoneWarningmsg;
	}
	public String getPasswordWarning() {
		String passwordWarningmsg=passwordWarning.getText();
		return passwordWarningmsg;
	}
}
