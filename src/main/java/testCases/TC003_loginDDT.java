package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*data is valid -->login success-->passed-->logout
data =is valid--->login unsuccessful-->failed

data is invalid -->login success-->failed-->logout
data is invalid-->login unscuccessful-->passed*/
public class TC003_loginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="DataDriven")
	public void verify_loginDDT(String email, String pwd, String expedResult) {

		logger.info("*****starting TC003_loginDDT test*****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickOnLoginBtn();

			MyAccountPage mp = new MyAccountPage(driver);
			boolean targerPage = mp.isMyAccountDisplay();
			if (expedResult.equalsIgnoreCase("valid")) {
				if (targerPage == true) {
					mp.clickLogout();
					Assert.assertTrue(true);

				} else {
					Assert.assertTrue(false);
				}

			}
			if (expedResult.equalsIgnoreCase("invalid")) {
				if (targerPage == true) {
					mp.clickLogout();
					Assert.assertTrue(false);

				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("*****finished TC003_loginDDT test*****");

	}

}
