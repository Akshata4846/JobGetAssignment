package com.jobget.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.jobget.helper.CSVHelper;
import com.jobget.pages.LoginPage;

public class loginPageTest {
	LoginPage loginPage;
	
	
	@DataProvider
	public Iterator<String[]> getData() throws IOException {
		final String SHEETNAME = "EmployerDetails";
		ArrayList<String[]> bodyData = CSVHelper.getSheetData(SHEETNAME);
		return bodyData.iterator();
	}
	
	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage();
	}

}
