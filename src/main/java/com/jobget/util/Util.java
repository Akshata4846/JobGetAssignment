package com.jobget.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.jobget.base.TestBase;
import com.jobget.helper.CSVHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class Util {
	

	public static long IMPLICIT_WAIT_TIMEOUT = 30;
	
	//Handles startup pages in app like location access permission and providing location is deny
	public static void handleStartupPages(TestBase miscPage, String Country) {
		try {
			miscPage.locationPermissionAccess(Config.getProperty("LocationPermissionAccess"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		miscPage.selectCountry(Country);
	}
	
	//Checks if user is employer or job seeker on login page
	public static boolean isEmployerLogin(TestBase miscPage) {
		boolean isEmployer = miscPage.isEmployer();
		Assert.assertTrue(isEmployer, "Expected selected type was employer but it is not");
		String buttonText = miscPage.getEmployerBtnText();
		Assert.assertEquals(buttonText, "Continue as an Employer"
				, "Expected text on button was \"Continue as an Employer\", but it is not");
		return true;
	}
	
	//Checks if user is employer or job seeker on sign up page
	public static boolean isEmployerSignUp(TestBase miscPage) {
		boolean isEmployer = miscPage.isEmployer();
		Assert.assertTrue(isEmployer, "Expected selected type was employer but it is not");
		String buttonText = miscPage.getEmployerBtnText();
		Assert.assertEquals(buttonText, "Sign up as an Employer"
				, "Expected text on button was \"Sign up as an Employer\", but it is not");
		return true;
	}
	
	//Gets all data from csv sheet for Sign up testcases
	public static Iterator<String[]> getSignUpSheetData(String SheetName) throws IOException {
		ArrayList<String[]> bodyData = CSVHelper.getSignUpSheetData(SheetName);
		return bodyData.iterator();
	}
	
	//Gets all data from csv sheet for login testcases
	public static Iterator<String[]> getLoginSheetData(String SheetName) throws IOException {
		ArrayList<String[]> bodyData = CSVHelper.getLoginDetailsData(SheetName);
		return bodyData.iterator();
	}
	
	//Takes screenshot
	public static String takeScreenshot(AppiumDriver<MobileElement> driver, String methodName) throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ssaa");
	    String destFile = dateFormat.format(new Date()) + "_" + methodName + ".png";
		
		File destFilePath = new File(Config.getProperty("ScreenshotFilePath") + destFile);
		FileUtils.copyFile(scrFile, destFilePath);
		return destFile;

	}
	
	//Reads toastMessage
	public String readToastMessage(AppiumDriver<MobileElement> driver, String name) throws TesseractException,IOException {
		String imgName = takeScreenshot(driver,name);
		String output = null;
		File imageFile = new File(Config.getProperty("ScreenshotFilePath"), imgName);
		ITesseract instance = new Tesseract();
		File dataFolder = LoadLibs.extractTessResources("tessdata");
		instance.setDatapath(dataFolder.getAbsolutePath());
		
		output = instance.doOCR(imageFile);
		return output;	
		
	}
	
	//Gets the current method name
	public static String getCurrentMethodName() {
		String nameofCurrMethod = new Object() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
        return nameofCurrMethod;
	}
	
	
	//Generates random password 
	public static String generateRandomPassword() {
		Random random = new Random();
		int randomNumber = random.nextInt(1111111111);
		String password = "Auto!" + randomNumber;
		return password;
	}
	
	
}
