package com.tutorialsNinja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class Utilities {
	
	public WebDriver driver;

	public static final int IMPLICIT_WAIT_TIME = 10;

	public static String generateTimeStamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

//	below method to understand the use of excelsheet data use
	public static Object[][] getTestDataFromExcel(String sheetName) {
		
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\cam\\tutorialsNinja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook=null;
		
		try{
			FileInputStream fisExcel=new FileInputStream(excelFile);
		    workbook = new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
//				have dirffernt code below
				
				data[i][j] = cell.getStringCellValue();
//			   if(cell.getCellType()==cell.CELL_TYPE_STRING) {
//				   
//				   data[i][j] = cell.getStringCellValue();
//				   
//			   }if(cell.getCellType()==cell.CELL_TYPE_NUMERIC) {
//				   
//				   data[i][j] = Integer.toString((int)cell.getNumericCellValue());
//			   }else {
//				   cell.getBooleanCellValue();
//			   }
//			CellType cellType = cell.getCellType();
//			   switch(cellType){
			   
//			   case STRING:
//			   data [i][j] = cell.getStringCellValue();
//			   break;
			   
//			   case NUMERIC:
//			   data [i][j] = Integer.toString((int)cell.getNumericCellValue());
//			   break;
			   
//			   case BOOLEAN:
//			    data [i][j] = cell.getBooleanCellValue();
//                break;
			   
//			   }
			}
		}
		
		return data;
	}
	
public static String screenshotTaker(String screenShotDestinationPath, WebDriver driver) {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File screenshot = ts.getScreenshotAs(OutputType.FILE);
		File screenShotDestinationPath1 = new File(screenShotDestinationPath);
		try {
			FileUtils.copyFile(screenshot, screenShotDestinationPath1);
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		return screenShotDestinationPath;
	}

}
