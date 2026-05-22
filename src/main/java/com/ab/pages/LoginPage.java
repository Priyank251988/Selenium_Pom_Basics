package com.ab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ab.basepage.Page;

public class LoginPage extends Page {
	
	public ZohoAppPage dologin(String username,String password)
	{
		type("Email_xpath", username);
		click("NextBtn_xpath");
		type("Password_xpath", password);
		click("SignINBtn_xpath");
		
		
		
		return new ZohoAppPage();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='top_New']")));
	}
	
	

}
