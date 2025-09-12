package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//dataprovider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".//test-output//opencart_loginData.xlsx";//taking xl file from testdata
		
		ExcelUtilities xlutil=new ExcelUtilities(path);//creating object for xlutility
		
		int totalrow=xlutil.getRowCount("sheet1");
		int totalcells=xlutil.getCellCount("sheet1", 1);
		String logindata[][]=new String[totalrow][totalcells];
		
		for(int i=1;i<totalrow;i++)//index is one bez of header
		{
			for(int j=0;j<totalcells;j++)//i is row n j is coloum
			{
				logindata[i-1][j]=xlutil.getCellData("sheet1", i, j);//1,0
			}
		}
		
		return logindata;//returning 2 dimensional array
		
		
	}
	

}
