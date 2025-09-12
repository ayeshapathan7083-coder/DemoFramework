package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	

@FindBy(xpath="//input[@id='input-email']")
WebElement emailTxtField;
@FindBy(xpath="//input[@id='input-password']")
WebElement pwdTxtField;
@FindBy(xpath="//input[@value='Login']")
WebElement loginbtn;

public void setEmail(String email)
{
	emailTxtField.sendKeys(email);
}
public void setPassword(String pwd)
{
	pwdTxtField.sendKeys(pwd);
}
public void clickOnLoginBtn()
{
	loginbtn.click();
}

}
