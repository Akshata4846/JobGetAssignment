package com.jobget.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.Assert;

import com.jobget.base.TestBase;
import com.jobget.helper.CSVHelper;

public class Util {
	public static long IMPLICIT_WAIT_TIMEOUT = 30;
	
	public static void handleStartupPages(TestBase miscPage, String Country) {
		try {
			miscPage.locationPermissionAccess(Config.getProperty("LocationPermissionAccess"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		miscPage.selectContacts(Country);
	}
	
	public static boolean isEmployerLogin(TestBase miscPage) {
		boolean isEmployer = miscPage.isEmployer();
		Assert.assertTrue(isEmployer, "Expected selected type was employer but it is not");
		String buttonText = miscPage.getEmployerBtnText();
		Assert.assertEquals(buttonText, "Continue as an Employer"
				, "Expected text on button was \"Continue as an Employer\", but it is not");
		return true;
	}
	
	public static boolean isEmployerSignUp(TestBase miscPage) {
		boolean isEmployer = miscPage.isEmployer();
		Assert.assertTrue(isEmployer, "Expected selected type was employer but it is not");
		String buttonText = miscPage.getEmployerBtnText();
		Assert.assertEquals(buttonText, "Sign up as an Employer"
				, "Expected text on button was \"Sign up as an Employer\", but it is not");
		return true;
	}
	
	public static Iterator<String[]> getData(String SheetName) throws IOException {
		ArrayList<String[]> bodyData = CSVHelper.getSheetData(SheetName);
		return bodyData.iterator();
	}
	
	public static Iterator<String[]> getLoginSheetData(String SheetName) throws IOException {
		ArrayList<String[]> bodyData = CSVHelper.getLoginDetailsData(SheetName);
		return bodyData.iterator();
	}


}
