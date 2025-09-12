package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{ 
		try
		{
		logger.info("*****starting TC001_account registration*****");
		HomePage hm=new HomePage(driver);
		Thread.sleep(3000);
		hm.clickMyAccount();
		logger.info("clicked on myAccount link..");
		hm.clickRegister();
		logger.info("clicked on register link..");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("providing customer details..");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
	    regpage.setEmail(randomString()+"@gmail.com");	//randomly generated the email
	    regpage.setTelephone(randomNumber());
	    
	    String password=randomAlphanumeric();
	    regpage.setPwd(password);
	    regpage.setCpwd(password);
	    regpage.clickAgreechkbox();
	    regpage.clickcontinueBtn();
	    logger.info("Validating expected msg..");
	   String confirmationMsg= regpage.getConfirmationMsg();
	   if(confirmationMsg.equals("Your Account Has Been Created!"))
	   {
		   Assert.assertTrue(true);
	   }
	   else
	   {
		   logger.error("test failed");
		   Assert.assertTrue(false);
	   }
	
	}
	catch(Exception e)
		{
		Assert.fail();
		}
		logger.info("*****finished TC001_account registration*****");
	}
	
}
