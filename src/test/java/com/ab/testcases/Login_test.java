package com.ab.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.ab.basepage.Page;
import com.ab.pages.HomePage;
import com.ab.pages.LoginPage;
import com.ab.pages.ZohoAppPage;
import com.ab.pages.crm.accounts.AccountHomepage;
import com.ab.pages.crm.accounts.CreateAccountPage;
import com.ab.utility.DataProviders;

import com.ab.utility.Utility;

public class Login_test extends Basetest {
	@Test(dataProviderClass=DataProviders.class,dataProvider="LoginTestDP")
	public void login_Test(Hashtable<String,String> data)
	{
		
		HomePage hp=new HomePage();
		LoginPage lp= hp.gotoSignin();
		
		  lp.dologin(data.get("Username"),data.get("Password"));
		  
		 
	}

}
