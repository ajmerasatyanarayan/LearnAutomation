package com.BullTokenCommunity.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
String excelPath= System.getProperty("user.dir")+"/TestData/BullTokenTestData.xlsx";
	

	public String getExcelData(String sheetName, int rowNum, int colNum)throws InvalidFormatException, IOException
	{
		//FileInputStream fis = new FileInputStream(excelPath);
		File file = new File(excelPath);
		FileInputStream fis = new FileInputStream(file);
//		Workbook wb = WorkbookFactory.create(fis);
//		Sheet sh = wb.getSheet(sheetName);
//		Row row = sh.getRow(rowNum);
//		Cell c = row.getCell(colNum);
//		c.setCellType(Cell.CELL_TYPE_STRING);
//		String data = c.getStringCellValue();
//		data = data.toString();
		XSSFWorkbook wb=new XSSFWorkbook(fis); 
		  // get the sheet which you want to modify or create
		XSSFSheet sh1= wb.getSheet(sheetName);
		String data = sh1.getRow(rowNum).getCell(colNum).toString();
		return data;
		// String data = row.getCell(colNum).getStringCellValue();
		//
		// return data;
	}

	public Row getRow(String sheetName, int rowNum)throws InvalidFormatException, IOException
	{
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		return row;
	}

	public int getRowCount(String sheetName) throws InvalidFormatException,IOException 
	{

		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		return rowCount;
	}

	public void setExcelData(String sheetName, int rowNum, int colNum,String data) throws InvalidFormatException, IOException 
	{
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.getCell(colNum);
		cel.setCellType(Cell.CELL_TYPE_STRING);
		cel.setCellValue(data);
		FileOutputStream fos = new FileOutputStream(excelPath);
		wb.write(fos);
	}

}
