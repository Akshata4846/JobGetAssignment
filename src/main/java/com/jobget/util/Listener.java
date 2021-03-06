package com.jobget.util;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.jobget.base.Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Listener extends Base implements ITestListener {
	
	public Listener(String deviceName,String platformVersion, String UDID, String port) {
		super(null, deviceName, platformVersion, UDID, port);
	}

	public Listener(AppiumDriver<MobileElement> driver, String deviceName,String platformVersion, String UDID, String port) {
		super(driver, deviceName, platformVersion, UDID, port);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			Util.takeScreenshot(getDriver(),result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
