package com.QA.Utilities;

import org.testng.annotations.DataProvider;

public interface DataProviders {

	@DataProvider(name="AllData")
	public String [][] AllDataProvider();
	
}
