package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name="search")
	private WebElement searchInputField;
	
	@FindBy(xpath="//div[@id='search']/span[@class='input-group-btn']")
	private WebElement searchButton;
	
	@FindBy(linkText="HP LP3065")
	private WebElement hpLP3065Product;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductSearchMsg;

	
//	Actions
	
	public void searchProduct(String productName) {
		searchInputField.sendKeys(productName);
	}
	
	public void clickOnSearchButton() {
		searchButton.click();
	}
	
	public WebElement getProductElemet() {
		WebElement hpLP3065ProductElement=hpLP3065Product;
		return hpLP3065ProductElement;
	}
	
	public String getNoProductSearchMsg() {
		String noProductSearchMessege=noProductSearchMsg.getText();
		return noProductSearchMessege;
	}
}
