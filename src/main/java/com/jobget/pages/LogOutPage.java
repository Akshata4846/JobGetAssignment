package com.jobget.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;

import com.jobget.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LogOutPage extends TestBase {
	
	@AndroidFindBy(id = "com.jobget:id/iv_profile_pic")
	MobileElement profile;
	
	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.ViewGroup/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView[1]")
	MobileElement settings;
	
	@FindBy(id = "com.jobget:id/tv_logout")
	MobileElement logout;
	
	@FindBy(id = "com.jobget:id/btn_yes")
	MobileElement logoutConfirmationYes;
	
	@FindBy(id = "com.jobget:id/btn_no")
	MobileElement logoutConfirmationNo;
	
	@FindBy(id = "com.jobget:id/tv_toolbar_title")
	MobileElement settingsPageTitle;
	
	
	public LogOutPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

	public void clickProfile() {
		profile.click();
	}
	
	public void clickSettings() {
		settings.click();
	}
	
	public void clickLogoutBtn() {
		logout.click();
	}
	
	public void logOutConfirmation(String Confirmation) {
		if (Confirmation.equalsIgnoreCase("Yes"))
		logoutConfirmationYes.click();
		else logoutConfirmationNo.click();
			
	}
	


}
