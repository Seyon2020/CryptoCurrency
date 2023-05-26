package com.sample.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.sample.base.BaseUI;
import com.sample.base.BrowserSetup;

public class FileIO {
public static Properties prop;
public static int com_row_count;
public static int com_col_count;
/************Get properties File*************/
public static Properties initProperties() {
	if(prop==null) {
	prop=new Properties();
	try {
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/objectrepository/config.properties");
		prop.load(file);
	}
	catch(Exception e) {
		
		e.printStackTrace();
	}
	}
	return prop;
}
public static String[][] datahandling(String sheetname) throws IOException {
	
	FileInputStream fis=new FileInputStream("C:\\Users\\senth\\eclipse-workspace\\POMDemo\\testdata\\testdata.xlsx");
	XSSFWorkbook workbook =new XSSFWorkbook(fis);
	XSSFSheet sheet=workbook.getSheet(sheetname);
	int rowcount=sheet.getPhysicalNumberOfRows();
	Row row=sheet.getRow(0);
	int colcount=row.getPhysicalNumberOfCells();
	String testdata[][]=new String [rowcount][colcount];
	DataFormatter formatter=new DataFormatter();
	for (int i = 0; i < rowcount; i++) {
		for(int j=0;j<colcount;j++) {
			testdata[i][j]=formatter.formatCellValue(sheet.getRow(i).getCell(j));
		}
	}
	com_row_count=rowcount+rowcount;
	com_col_count=colcount;
	return testdata;
}
}