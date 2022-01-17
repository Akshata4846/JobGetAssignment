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
	
    public static ArrayList<String[]> getSheetData(String sheetName) throws IOException {
		
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		String csvPath = CSVHelper.getCSV();
		ExcelUtils utils = new ExcelUtils(csvPath);
		String firstName = utils.getCellData(sheetName, 1, 0);
		String lastName = utils.getCellData(sheetName, 1, 1);
		String email = utils.getCellData(sheetName, 1, 2);
		String password = utils.getCellData(sheetName, 1, 3);
		String country = utils.getCellData(sheetName, 1, 4);
		
		String[] allData = {firstName,lastName,email,password,country};
		data.add(allData);
		
		return data;
	}

}