package com.ab.testcases;

import org.testng.annotations.AfterSuite;

import com.ab.basepage.Page;

public class Basetest {
	@AfterSuite
	public void tearDown()
	{
		Page.quit();
	}

}
