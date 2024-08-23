package com.tutorialsNinja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.tutorialsNinja.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	
    public Properties prop;
    
    public Properties dataProp;
  	
	public Base() {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsNinja\\qa\\config\\config.properties");
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\cam\\tutorialsNinja\\qa\\testdata\\testdata.properties");
		
		try{
			FileInputStream fis2 = new FileInputStream(dataPropFile);
		dataProp.load(fis2);
		}catch(Throwable e){
			e.printStackTrace();
		}

		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();

		}
	}

	public WebDriver initializeBrowserAndOpenAppUrl(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\11.09.2023\\aceleration\\hybridTestNgFramework\\seleniumbasics\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (browserName.equals("firefox")) {

//			code for firefox driver
			System.setProperty("webdriver.gecko.driver", "C:\\11.09.2023\\aceleration\\hybridTestNgFramework\\seleniumbasics\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		if (browserName.equals("edge")) {

//			code for edge driver
			System.setProperty("webdriver.ie.driver", "C:\\aceleration\\hybridTestNgFramework\\seleniumbasics\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Utilities.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
	
	
}
