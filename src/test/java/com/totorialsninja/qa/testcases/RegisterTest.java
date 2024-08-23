package com.totorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pages.AccountSuccessPage;
import com.tutorialsNinja.qa.pages.HomePage;
import com.tutorialsNinja.qa.pages.RegisterPage;
import com.tutorialsNinja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenAppUrl(prop.getProperty("browserName"));
		HomePage homePage= new HomePage(driver);
		homePage.clickOnMyAccount();
//		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		registerPage= homePage.selectRegisterOption();
//		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandetoryField() {
		
		registerPage.fillAllMandetoryField(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), "harsha" + Utilities.generateTimeStamp() + "@gmail.com", dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));

//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys("harsha" + Utilities.generateTimeStamp() + "@gmail.com");
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerPage.clickOnPrivacyPolicy();
//		driver.findElement(By.name("agree")).click();
		accountSuccessPage = registerPage.clickOnContinue();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
//		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accountSuccessPage.getAccountSuccessHeading();
//		String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessHeading"), "Account is not created");

	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountByProvidingAllField() {
		
		registerPage.fillAllMandetoryField(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), "harsha" + Utilities.generateTimeStamp() + "@gmail.com", dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));

//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys("harsha" + Utilities.generateTimeStamp() + "@gmail.com");
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		
		registerPage.clickOnYesOption();
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		registerPage.clickOnPrivacyPolicy();
//		driver.findElement(By.name("agree")).click();
		accountSuccessPage = registerPage.clickOnContinue();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
		String actualSuccessHeading = accountSuccessPage.getAccountSuccessHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessHeading"), "Account is not created");

	}

	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExixstingEmailAddress() {
	
		registerPage.fillAllMandetoryField(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));

//    	driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerPage.clickOnYesOption();
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		registerPage.clickOnPrivacyPolicy();
//		driver.findElement(By.name("agree")).click();
		registerPage.clickOnContinue();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualWarning = registerPage.duplicateEmailWarningMsg();
//		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),
				"Warning msg regarding duplicate email addressis not displyaed");

	}

	@Test(priority = 4)
	public void registeringAccountWithoutFillingAnyDetails() {
		
		registerPage.clickOnContinue();

//		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		
//		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String actualPrivacyPolicyWarning =registerPage.getPrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),
				"actual Privacy Policy not displyed");

		String actualFirstnameWarning =registerPage.getFirstNameWarning();
//		String actualFirstnameWarning = driver
//				.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstnameWarning, dataProp.getProperty("firstNameWarning"),
				"Actual firstname Warning msg not displayed");

		String actualLastnameWarning = registerPage.getLasttNameWarning();
//		String actualLastnameWarning = driver
//				.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastnameWarning, dataProp.getProperty("lastNameWarning"),
				"Actual lastname Warning msg not displayed");

		String actualEmailWarning =registerPage.getEmailWarning();
//		String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div"))
//				.getText();
		Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailWarning"),
				"Actual email Warning msg not displayed");

		String actualTelephoneWarning =registerPage.gettelephoneWarning();
//		String actualTelephoneWarning = driver
//				.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"),
				"Actual Telephone Warning msg not displayed");

		String actualPasswordWarning = registerPage.getPasswordWarning();
//		String actualPasswordWarning = driver
//				.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwrodWarning"),
				"Actual password Warning msg not displayed");

	}

}
