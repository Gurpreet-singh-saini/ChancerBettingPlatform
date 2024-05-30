package com.QA.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig()
	{
		File scr = new File("./configuration/config.properties");
		try
		{
			FileInputStream fis = new FileInputStream(scr);
			pro = new Properties();
			pro.load(fis);
		}
		catch(Exception e)
		{
			System.out.println("Exception is "+e.getMessage());
		}

	}

	public String getApplicationURL()
	{
		String AdminBaseURL = pro.getProperty("baseURL");
		if(AdminBaseURL!=null) {
			return AdminBaseURL;
		} else {
			throw new RuntimeException("URl not specified in config file.");
		}

	}

	public String getBrowser()
	{
			String  browser = pro.getProperty("Browser");
			return browser;
	}
		

	public String ChromeDriverPath() {
		// TODO Auto-generated method stub
		String  ChromeDriverPath = pro.getProperty("ChromePath");
		return ChromeDriverPath;
	}

}
