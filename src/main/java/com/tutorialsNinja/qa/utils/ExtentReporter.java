package com.tutorialsNinja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentReports;



public class ExtentReporter {
	

	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
//		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
//		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
//		
//		sparkReporter.config().setTheme(Theme.DARK);
//		sparkReporter.config().setReportName("tutorialsNinjaTestResult");
//		sparkReporter.config().setDocumentTitle("TN automation report");
//		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
//		extentReport.attachReporter(sparkReporter);
		
//		Properties configProp = new Properties();
//		try {
//			FileInputStream fisConfigProp = new FileInputStream(System.getProperty("user,dir")
//					+ "\\src\\main\\java\\com.tutorialsNinja.qa.config\\config.properties");
//			configProp.load(fisConfigProp);
//		} catch (Throwable e) {
//			e.printStackTrace();
//
//		}
		
		extentReport.addSystemInfo("Application URL", "ttninja");
		extentReport.addSystemInfo("BrowserName", "chrome");
		extentReport.addSystemInfo("Email", "something@gmail");
		extentReport.addSystemInfo("Password", "ssss");
		extentReport.addSystemInfo("operating system", System.getProperty("os.name"));
		extentReport.addSystemInfo("System Name", System.getProperty("user.name"));
		extentReport.addSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}

}
