package Rough;

import org.openqa.selenium.WebDriver;

import com.ab.basepage.Page;
import com.ab.pages.HomePage;
import com.ab.pages.LoginPage;
import com.ab.pages.ZohoAppPage;
import com.ab.pages.crm.accounts.AccountHomepage;
import com.ab.pages.crm.accounts.CreateAccountPage;



public class Rough {
	public static void main(String[] args) {
		HomePage hp=new HomePage();
		LoginPage lp= hp.gotoSignin();
		ZohoAppPage zp=lp.dologin("priyank25sharma@yahoo.co.in","Test#123");
		zp.gotoCRM();
		AccountHomepage ahp=	 Page.menu.gotoAccounts();
		CreateAccountPage cap=ahp.gotoCreateAccount();
		cap.createAccount("Test1");
		 
		
		
		
		
		
	}

	

}
