package com.tutorialsNinja.qa.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tutorialsNinja.qa.utils.ExtentReporter;
import com.tutorialsNinja.qa.utils.*;


import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler;

public class MyListners implements ITestListener  {
	
	ExtentReports extentReport;
	ExtentTest extentTest;
//	WebDriver driver;
	
	@Override
	public void onStart(ITestContext context) {
		
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.startTest(testName);
		extentTest.log(LogStatus.INFO, testName+"started executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(LogStatus.PASS, testName+"got success");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+System.currentTimeMillis()+".png";
		try {
			FileUtils.copyFile(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
//		extentTest.addScreenCapture(destinationScreenshotPath);
//		extentTest.addScreenCapture(Utilities.screenshotTaker(System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png" , driver));
		
		extentTest.log(LogStatus.INFO, result.getThrowable());
		extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(destinationScreenshotPath), testName+" got failed");
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extentTest.log(LogStatus.INFO, result.getThrowable());
		extentTest.log(LogStatus.SKIP, testName+"got skipped");
		
		
	}	

	@Override
	public void onFinish(ITestContext context) {
		
        extentReport.endTest(extentTest);
		
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
//		extentReport.close();
		
	}
	

}
