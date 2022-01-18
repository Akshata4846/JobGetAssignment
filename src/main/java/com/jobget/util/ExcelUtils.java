package com.jobget.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static String path;
	public FileInputStream input = null;
	public FileOutputStream out = null;
	private static XSSFWorkbook workBook = null;
	private static XSSFSheet sheet = null;
	private static XSSFRow row = null;
	private static XSSFCell cell = null;
	
	

	/** Open excel **/
	public ExcelUtils(String path) throws IOException {
		input = new FileInputStream(path);
		workBook = new XSSFWorkbook(input);
	}

	/** Get sheet **/
	private static XSSFSheet getSheet(String sheetName) throws IOException {
		XSSFSheet workBookSheet = workBook.getSheet(sheetName);
		return  workBookSheet;

	}

	/** Check if sheet exists 
	 * @throws IOException **/
	public static boolean isSheetExists(String sheetName) throws IOException {
		int index = workBook.getSheetIndex(sheetName);
		System.out.println("index" + index);
		if (index == -1)
			return false;
		else {
			System.out.println("Yaay");
			return true;
		}
	}

	/** Get row count **/
	public static int getRowCount(String sheetName) throws IOException {
		sheet = getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Number of rows" + rowCount);
		return rowCount;

	}

	/** Get column count 
	 * @throws IOException **/
	public static int getColumnCount(String sheetName) throws IOException {
		sheet = getSheet(sheetName);
		XSSFRow row = sheet.getRow(0);
		if (row==null) 
			return -1;
		int cellCount  = row.getLastCellNum();
		System.out.println("cellnumber" + cellCount);
		return cellCount;


	}

	/** Get cell data using formatter
	 * @throws IOException **/
	//	public static void getCellData(String sheetName,String path) throws IOException {
	//		XSSFSheet sheet = getSheet(sheetName, path);
	//		DataFormatter formatter = new DataFormatter();
	//		Object value  = formatter.formatCellValue(sheet.getRow(1).getCell(0));
	//		System.out.println("Value" + value);
	//		//sheet.getRow(0).getCell(0).getStringCellValue();
	//		
	//	}

	/** Get cell data 
	 * @throws IOException **/
	public String getCellData(String sheetName, int rowNum, int columnNum)  {
		try {
			sheet = getSheet(sheetName);
			if (rowNum <= 0) 
				return "";
			if (columnNum < 0)
				return "";

			row = sheet.getRow(rowNum);
			if (row==null)
				return "";

			cell = row.getCell(columnNum);
			if (cell == null)
				return "";

			if (cell.getCellType().name().equals("STRING"))
				return cell.getStringCellValue();

			else if (cell.getCellType().name().equalsIgnoreCase("Numeric")) 
				return String.valueOf(cell.getNumericCellValue());


			else if (cell.getCellType().BLANK != null)
				return "";

			else
				return String.valueOf(cell.getBooleanCellValue()); 
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return "row:" + rowNum+ "column:" +columnNum+ "does not exist" ;


		}
	}
	
	
		/**
		 * @param sheetName
		 * @param colName
		 * @param rowNum
		 * @param data
		 * @return
		 * Used for set cell data. Returns true if data is set successfully else false
		 */
		public boolean setCellData(String sheetName, int rowNum, int columnNum, String data, String path) {
			try {
				sheet = getSheet(sheetName);
//				input = new FileInputStream(path);
//				workBook = new XSSFWorkbook(input);
				
				if (rowNum <= 0) 
					return false;
				
				if (columnNum < 0)
					return false;

				//int colNum = -1;
	
				//row = sheet.getRow(0);
				
				row = sheet.
						getRow(rowNum);
				if (row==null)
					return false;

				cell = row.getCell(columnNum);
				if (cell == null)
					return false;

				cell.setCellValue(data);
				out = new FileOutputStream(path);
				workBook.write(out);
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	
}
