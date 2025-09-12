package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BankRegisterPage extends BasePage {

	BankRegisterPage(WebDriver driver) {
		super(driver);
	}
	

@FindBy(xpath="//a[normalize-space()='Register']")
WebElement registerLnk;
@FindBy(xpath="//input[@id='customer.firstName']")
WebElement firstName;
@FindBy(xpath="//input[@id='customer.lastName']")
WebElement lastName;
@FindBy(xpath="//input[@id='customer.address.street']")
WebElement street;
@FindBy(xpath="//input[@id='customer.address.city']")
WebElement city;
@FindBy(xpath="//input[@id='customer.address.state']")
WebElement state;
@FindBy(xpath="//input[@id='customer.address.zipCode']")
WebElement zipcode;
@FindBy(xpath="//input[@id='customer.phoneNumber']")
WebElement phoneNo;
@FindBy(xpath="//input[@id='customer.ssn']")
WebElement ssn;
@FindBy(xpath="//input[@id='customer.username']")
WebElement userName;
@FindBy(xpath="//input[@id='customer.password']")
WebElement pwd;
@FindBy(xpath="//input[@id='repeatedPassword']")
WebElement cnfmPwd;
@FindBy(xpath="//p[contains(text(),'Your account was created successfully. You are now')]")
WebElement msgConfirmation;

}
