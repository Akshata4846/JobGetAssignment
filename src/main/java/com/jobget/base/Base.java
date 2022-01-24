package com.jobget.base;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.jobget.util.Util;
import com.jobget.util.Config;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {

	private DesiredCapabilities dc;
	private URL url;
	private AppiumDriver<MobileElement> driver;
	

	public Base (AppiumDriver<MobileElement> driver, String deviceName,String platformVersion, String UDID ) {
		if (driver != null) {
			this.driver = driver;
		} else {
			initializeApp(deviceName,platformVersion, UDID);
		}
	}

	public void initializeApp(String deviceName,String platformVersion, String UDID)  {
		dc = new DesiredCapabilities();
		try {
			//dc.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getProperty("deviceName"));
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, Config.getProperty("platformName"));
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			dc.setCapability(MobileCapabilityType.UDID, UDID);
			dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			dc.setCapability(MobileCapabilityType.APP, Config.getProperty("appPath") + "app-preproduction-jobget-14-jan.apk");
			//dc.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\src\\test\\resources\\apps" + Config.getProperty("app"));
			//System.out.println("apk path" + System.getProperty("user.dir") + "\\src\\test\\resources\\apps" + Config.getProperty("app"));
			url =  new URL("http://" + Config.getProperty("hostname") + ":" + Config.getProperty("port") + "/wd/hub");
			driver = new AppiumDriver<MobileElement>(url,dc);
			driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		
		} 
		catch (Exception e) {
			System.out.println("ExceptionCause is " + e.getCause());
			System.out.println("Message is " +e.getMessage());
			e.printStackTrace();
		}
	}
	
	public AppiumDriver<MobileElement> getDriver() {
		return driver;
	}
	
	public void locationPermissionAccess(String action) {
		
	}
	
	public void selectCountry(String contact) {
		
	}
	
	public boolean isEmployer() {
		return false;
	}
	
	public String getEmployerBtnText() {
		return null;
	}

}
