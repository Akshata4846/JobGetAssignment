package com.jobget.helper;

import java.io.IOException;
import java.util.ArrayList;

import com.jobget.util.Config;
import com.jobget.util.ExcelUtils;

public class CSVHelper {

	public static String getCSV() {
		try {
			String csvPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\jobget\\testdata\\" + Config.getProperty("CSVName");
			return csvPath;
		} catch (Exception e) {
			e.printStackTrace();
			return "csv path is empty";
		}
	}

	public static ArrayList<String[]> getSignUpSheetData(String sheetName) throws IOException {

		ArrayList<String[]> data = new ArrayList<String[]>();

		String csvPath = CSVHelper.getCSV();
		ExcelUtils utils = new ExcelUtils(csvPath);
		String firstName = utils.getCellData(sheetName, 1, 0);
		String lastName = utils.getCellData(sheetName, 1, 1);
		String email = utils.getCellData(sheetName, 1, 2);
		String password = utils.getCellData(sheetName, 1, 3);
		String country = utils.getCellData(sheetName, 1, 4);
		String companyName = utils.getCellData(sheetName, 1, 5);
		String companyWebsite = utils.getCellData(sheetName, 1, 6);
		String countryCode = utils.getCellData(sheetName, 1, 7);
		String mobileNumber = utils.getCellData(sheetName, 1, 4);

		String[] allData = {firstName,lastName,email,password,country,companyName,companyWebsite,countryCode,mobileNumber};
		data.add(allData);

		return data;
	}

	public static ArrayList<String[]> getLoginDetailsData(String sheetName) throws IOException {

		ArrayList<String[]> data = new ArrayList<String[]>();

		String csvPath = CSVHelper.getCSV();
		ExcelUtils utils = new ExcelUtils(csvPath);
		String emailAddress = utils.getCellData(sheetName, 1, 0);
		String password = utils.getCellData(sheetName, 1, 1);
		String country = utils.getCellData(sheetName, 1, 2);

		String[] allData = {emailAddress,password,country};
		data.add(allData);

		return data;
	}
	
	
	public static void setExcelCellData(String sheetName, String newData) throws IOException {
		
		String csvPath = CSVHelper.getCSV();
		ExcelUtils utils = new ExcelUtils(csvPath);
		boolean result = utils.setCellData(sheetName, 1, 1, newData, csvPath);
		if (result == true)
			System.out.println("Provided data was updated in" + csvPath);
		else
		   System.out.println("did not set");
		
	}

}
