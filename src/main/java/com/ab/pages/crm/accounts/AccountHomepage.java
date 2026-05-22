package com.ab.pages.crm.accounts;

import org.openqa.selenium.By;

import com.ab.basepage.Page;

public class AccountHomepage extends Page{
	public CreateAccountPage gotoCreateAccount()
	{
		click("CreateAcount_xpath");
		
		return new CreateAccountPage();
	}
	
	public void gotoImportAccount()
	{
		
	}

}
