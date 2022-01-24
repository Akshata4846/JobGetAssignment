package com.jobget.testcases;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.jobget.util.Util;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class TestBase {
	public ExtentTest test;
    public ExtentReports extent;
    
	
	@BeforeTest()
	public void generateReport() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ssaa");
	    String FileName = dateFormat.format(new Date());
	    extent = new ExtentReports(System.getProperty("user.dir")+ "\\Reports\\" + FileName + "report.html", false);
		extent.addSystemInfo("Hostname", "Localhost");
		extent.addSystemInfo("OS", "Win10");
	}
	
	@AfterTest()
	public void writeReport() {
		extent.flush();
	}

}
