package com.ab.utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import com.ab.basepage.Page;

public class Utility extends Page{
	public static String scrrenshotname;

	public static void captureScreenshot()
	{
	
		File srcfile=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		Date d=new Date();
		
		try {
			scrrenshotname="Error_Screenshot"+d.toString().replace(":", "_").replace(" ", "_")+".png";
			
			System.out.println(System.getProperty("user.dir")+"\\target\\surefire-reports\\html");
			FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+scrrenshotname));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean is_TestRunnable(String testname,ExcelReader excel)
	{
		String sheetname=Constants.suite_sheet;
		int rows=excel.getRowCount(sheetname);
		for(int i=2;i<=rows;i++) {
			String test_case=excel.getCellData(sheetname,"Testcase",i);
			System.out.println(test_case+"  "+testname);
			if(test_case.equalsIgnoreCase(testname))
			{
				String runmode=excel.getCellData(sheetname,"Runmode",i);
				if(runmode.equalsIgnoreCase("Y"))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		return false;
	}
}
