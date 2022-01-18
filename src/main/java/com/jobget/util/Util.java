package com.jobget.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
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
	
	public static Iterator<String[]> getSignUpSheetData(String SheetName) throws IOException {
		ArrayList<String[]> bodyData = CSVHelper.getSignUpSheetData(SheetName);
		return bodyData.iterator();
	}
	
	public static Iterator<String[]> getLoginSheetData(String SheetName) throws IOException {
		ArrayList<String[]> bodyData = CSVHelper.getLoginDetailsData(SheetName);
		return bodyData.iterator();
	}
	
	public static String takeScreenshot(AppiumDriver<MobileElement> driver, String methodName) throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ssaa");
	    String destFile = dateFormat.format(new Date()) + "_" + methodName + ".png";
		
		File destFilePath = new File(Config.getProperty("ScreenshotFilePath") + destFile);
		FileUtils.copyFile(scrFile, destFilePath);
		return destFile;

	}
	
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
	
	public static String getCurrentMethodName() {
		String nameofCurrMethod = new Object() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
        return nameofCurrMethod;
	}
	
	
}
