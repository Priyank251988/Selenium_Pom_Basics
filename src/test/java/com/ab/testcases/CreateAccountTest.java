package com.ab.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ab.basepage.Page;
import com.ab.pages.ZohoAppPage;
import com.ab.pages.crm.accounts.AccountHomepage;
import com.ab.pages.crm.accounts.CreateAccountPage;
@Test
public class CreateAccountTest extends Basetest {
	
	public void createAccountTest()
	{
		ZohoAppPage zp =new ZohoAppPage();
		zp.gotoCRM();
	  AccountHomepage ahp= Page.menu.gotoAccounts();
	  CreateAccountPage cap=ahp.gotoCreateAccount();
	  cap.createAccount("Test1");
	  Assert.fail("create account failed");
	}

}
