package com.ab.pages.crm.accounts;

import org.openqa.selenium.By;

import com.ab.basepage.Page;

public class CreateAccountPage extends Page{
	
	public void createAccount(String accountName)
	{
		
		type("AccountName_xpath", accountName);
		click("Savebtn_xpath");
		
	}	
	

}
