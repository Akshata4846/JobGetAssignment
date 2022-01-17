package com.jobget.base;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.jobget.util.Util;
import com.jobget.util.Config;
import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBase {
	public static DesiredCapabilities dc;
	static URL url;
	public AppiumDriver<MobileElement> driver;
	Config config = new Config();
	
	public TestBase() {
		initialization();
	}


	public AppiumDriver<MobileElement> initialization()  {
		dc = new DesiredCapabilities();
		try {
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getProperty("deviceName"));
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, Config.getProperty("platformName"));
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, Config.getProperty("platformVersion"));
			dc.setCapability(MobileCapabilityType.UDID, Config.getProperty("UDID"));
			dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			dc.setCapability(MobileCapabilityType.APP, "C:\\Users\\sanat\\Downloads\\app-preproduction-jobget-14-jan.apk");
			//dc.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\src\\test\\resources\\apps" + Config.getProperty("app"));
			//System.out.println("apk path" + System.getProperty("user.dir") + "\\src\\test\\resources\\apps" + Config.getProperty("app"));
			url =  new URL("http://0.0.0.0:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url,dc);
			driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
			return driver;
		} 
		catch (Exception e) {
			System.out.println("ExceptionCause is " + e.getCause());
			System.out.println("Message is " +e.getMessage());
			e.printStackTrace();
		}
		return null;

	}
	
	public void locationPermissionAccess(String action) {
		
	}
	
	public void selectContacts(String contact) {
		
	}
	
	public boolean isEmployer() {
		return false;
	}
	
	public String getEmployerBtnText() {
		return null;
	}
}
