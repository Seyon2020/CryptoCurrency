package com.sample.utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class WebTableHandling{
	/***
	 * This webDataToExcel() method copies the data from given List to the excel sheet specified
	 * */
		public static void webDataToExcel(String time,List<String> data, int rowsize,int colsize,String sheetname) throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\senth\\eclipse-workspace\\CryptocurrencyDemo\\testdata\\testdata.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet;
		if(workbook.getSheet(sheetname)!=null) {
			int sheetnum=workbook.getSheetIndex(sheetname);
			workbook.removeSheetAt(sheetnum);
		}
		sheet=workbook.createSheet(sheetname);
		Row row;
		int i=0;
		for(int j=0;j<rowsize+1;j++) {
		row=sheet.createRow(j);
		for(int k=0;k<colsize;k++) {
			Cell cell=row.createCell(k);
			cell.setCellValue(data.get(i++));
		}
		}
		FileOutputStream os=new FileOutputStream("C:\\Users\\senth\\eclipse-workspace\\CryptocurrencyDemo\\testdata\\testdata.xlsx");
		workbook.write(os);
		workbook.close();
		os.close();
		System.out.println("Successfully Writen");
	}
}
