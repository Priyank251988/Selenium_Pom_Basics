package com.ab.pages;

import org.openqa.selenium.By;

import com.ab.basepage.Page;

public class HomePage extends Page{
	
	
	public void goToSignup()
	{
		getDriver().findElement(By.xpath("//*[@id=\"header\"]/div[3]/div[3]/a[2]")).click();
	}
	public LoginPage gotoSignin()
	{
		click("signin_xpath");
		
		return new LoginPage();
		
	}
	
	public void goToEnterprise()
	{
		
	}

}
