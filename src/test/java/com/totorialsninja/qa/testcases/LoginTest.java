package com.totorialsninja.qa.testcases;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pages.AccountPage;
import com.tutorialsNinja.qa.pages.HomePage;
import com.tutorialsNinja.qa.pages.LoginPage;
import com.tutorialsNinja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	LoginPage loginPage;

	public LoginTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenAppUrl(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
//        driver.findElement(By.xpath("//span[text()='My Account']")).click();
//        driver.findElement(By.linkText("Login")).click();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1, dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredentials(String email, String password) {
//        driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage=loginPage.clickOnLoginButton();
//        driver.findElement(By.id("input-email")).sendKeys(email);
//        driver.findElement(By.id("input-password")).sendKeys(password);
//        driver.findElement(By.xpath("//input[@value='Login']")).click();

//        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
//		  AccountPage accountPage = new AccountPage(driver);
		
		  Assert.assertTrue(accountPage.getDisplayStatusOfAccInfOption());
		
		

//        driver.quit();

	}

	@DataProvider // can give name also here
	public Object[][] supplyTestData() {
		Object[][] data = { { "dhoredhananjay5@gmail.com", "DD12345" }, { "ddhandbd@gmail.com", "12352" },
				{ "gdhsdhdj@gail.com", "324142" } };
//		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		
		loginPage.enterEmailAddress("dhoredha" + Utilities.generateTimeStamp() + "anjay5@gmail.com");
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
//        driver.findElement(By.id("input-email")).sendKeys("dhoredha"+Utilities.generateTimeStamp()+"anjay5@gmail.com");
//        driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
//        driver.findElement(By.xpath("//input[@value='Login']")).click();

//        String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotMatchingWarningMsgText();
		String expectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMsg), "expected warning msg not displayed");

//        driver.quit();
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.enterEmailAddress("dhoredha" + Utilities.generateTimeStamp() + "anjay5@gmail.com");
		loginPage.enterPassword(dataProp.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
//        driver.findElement(By.id("input-email")).sendKeys("dhoredha"+Utilities.generateTimeStamp()+"anjay5@gmail.com");
//        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//        driver.findElement(By.xpath("//input[@value='Login']")).click();

//        String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotMatchingWarningMsgText();
		String expectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMsg), "expected warning msg not displayed");

//        driver.quit();
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInValidPassword() {
		
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("DD12" + Utilities.generateTimeStamp() + "345"));
		loginPage.clickOnLoginButton();
//        driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//        driver.findElement(By.id("input-password")).sendKeys("DD12"+Utilities.generateTimeStamp()+"345");
//        driver.findElement(By.xpath("//input[@value='Login']")).click();

//      String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotMatchingWarningMsgText();
		String expectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMsg), "expected warning msg not displayed");

//        driver.quit();
	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {
		
		loginPage.clickOnLoginButton();
//        driver.findElement(By.xpath("//input[@value='Login']")).click();

//      String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotMatchingWarningMsgText();
		String expectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMsg), "expected warning msg not displayed");

//        driver.quit();
	}

}