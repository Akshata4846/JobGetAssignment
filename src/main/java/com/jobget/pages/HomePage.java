package com.jobget.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jobget.base.Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends Base {
	
	public HomePage(AppiumDriver<MobileElement> driver, String deviceName,String platformVersion, String UDID) {
		super(driver, deviceName, platformVersion, UDID);
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}


	@FindBy(id = "com.jobget:id/tv_title")
	public static MobileElement jobPostingsPageTitle;
	
	
	public String getJobPostingsPageTitle() {
		String title = jobPostingsPageTitle.getText();
		return title;
	}

}
