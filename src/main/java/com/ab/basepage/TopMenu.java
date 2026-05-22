package com.ab.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ab.pages.crm.accounts.AccountHomepage;

public class TopMenu {
	
	 WebDriver driver;
	
	public TopMenu(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	public void gotoHome() {

	}

	public void gotoFeeds() {

	}

	public void gotoLeadsPage() {

	}

	public  AccountHomepage gotoAccounts() {
		
		driver.findElement(By.xpath("//*[@id=\"moduleId_Accounts\"]/a/lyte-text")).click();
		return new AccountHomepage();
		
		
	}

	public void gotoContacts() {

	}
	
	
	public void signOut(){
		
		
	}

}