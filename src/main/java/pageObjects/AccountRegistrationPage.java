package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}

@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtfname;
@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtlname;
@FindBy(xpath="//input[@id='input-email']")
WebElement txtemail;
@FindBy(xpath="//input[@id='input-telephone']")
WebElement txttelephone;
@FindBy(xpath="//input[@id='input-password']")
WebElement txtpwd;
@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtcpwd;
@FindBy(xpath="//label[normalize-space()='Yes']")
WebElement yesRadioBtn;
@FindBy(xpath="//label[normalize-space()='No']")
WebElement noRadioBtn;
@FindBy(xpath="//input[@name='agree']")
WebElement chkbox;
@FindBy(xpath="//input[@value='Continue']")
WebElement ctnuBtn;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

public void setFirstName(String fname)
{
	txtfname.sendKeys(fname);
}
public void setLastName(String lname)
{
	txtlname.sendKeys(lname);
}
public void setEmail(String email)
{
	txtemail.sendKeys(email);
}
public void setTelephone(String telephone)
{
	txttelephone.sendKeys(telephone);
}
public void setPwd(String pwd)
{
	txtpwd.sendKeys(pwd);
}
public void setCpwd(String cpwd)
{
	txtcpwd.sendKeys(cpwd);
}
public void clickyesRadioBtn(String ybtn)
{
	yesRadioBtn.click();
}
public void clicknoRadioBtn()
{
	noRadioBtn.click();
}
public void clickAgreechkbox()
{
	chkbox.click();
}
public void clickcontinueBtn()
{
	ctnuBtn.click();
}
public String getConfirmationMsg()
{ 
	try {
		return(msgConfirmation.getText());
		
	}
	catch(Exception e)
	{
		return(e.getMessage());
	}
	
	
}





}
