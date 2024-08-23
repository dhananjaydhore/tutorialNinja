package com.totorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchPage;
	
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenAppUrl(prop.getProperty("browserName"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct(){
		searchPage=new SearchPage(driver);
		searchPage.searchProduct(dataProp.getProperty("validProduct"));
//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		searchPage.clickOnSearchButton();
//		driver.findElement(By.xpath("//div[@id='search']/span[@class='input-group-btn']")).click();
		
		Assert.assertTrue(searchPage.getProductElemet().isDisplayed(),"valid product not displayed");
//		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"valid product not displayed");
		
		
	}
	
	@Test(priority=2)
    public void verifySearchWithInValidProduct(){
		searchPage=new SearchPage(driver);
		searchPage.searchProduct(dataProp.getProperty("InValidProduct"));
//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("InValidProduct"));
		searchPage.clickOnSearchButton();
//		driver.findElement(By.xpath("//div[@id='search']/span[@class='input-group-btn']")).click();
		
		String actaulSearchMessage = searchPage.getNoProductSearchMsg();
//		String actaulSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actaulSearchMessage, dataProp.getProperty("noProductTextSearchMsg"), "actual Sarch Message not displayed");
		
	}
	
	@Test(priority=3)
	public void verifySearchWithourAnyProduct() {
		searchPage=new SearchPage(driver);
		
		searchPage.clickOnSearchButton();
//		driver.findElement(By.xpath("//div[@id='search']/span[@class='input-group-btn']")).click();
		
		String actaulSearchMessage = searchPage.getNoProductSearchMsg();
//		String actaulSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actaulSearchMessage, dataProp.getProperty("noProductTextSearchMsg"), "actual Sarch Message not displayed");
	}
}
