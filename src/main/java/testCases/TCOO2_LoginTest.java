package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TCOO2_LoginTest extends BaseClass{
	@Test(groups={"Sanity","Master"})
	public void verifyLoginTest()
	{
		try
		{
		logger.info("*****starting TC002_login test*****");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickOnLoginBtn();
		
		MyAccountPage mp=new MyAccountPage(driver);
		boolean targerPage = mp.isMyAccountDisplay();
		//Assert.assertEquals(targerPage, true,"login failed");
		Assert.assertTrue(targerPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*****finished TC002_login test*****");
	}
	

}
