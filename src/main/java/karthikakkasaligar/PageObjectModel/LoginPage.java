package karthikakkasaligar.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import karthikakkasaligar.ReuseableComponents.ReuseableComponentsPage;

public class LoginPage extends ReuseableComponentsPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='orangehrm-login-error']/div/p")
	WebElement username;

	@FindBy(xpath = "(//div[@class='orangehrm-login-error']/div/p)[2]")
	WebElement password;

	@FindBy(css = "[placeholder='Username']")
	WebElement usernameinputfeild;

	@FindBy(css = "[placeholder='Password']")
	WebElement passwordinputfeild;

	@FindBy(css = ".orangehrm-login-button")
	WebElement submitlogincta;

	@FindBy(css = "[role*='alert']")
	WebElement erroralert;

	@FindBy(css = ".oxd-input-group__message")
	WebElement FormValidationerror;

	@FindBy(css = ".oxd-input-group__message")
	List<WebElement> FormValidationerrorS;

	By errormessagealert = By.cssSelector("[role*='alert']");

	By FormValidation = By.cssSelector(".oxd-input-group__message");

	public String getusername() {
		return username.getText().split(":")[1].trim();
	}

	public String getpassword() {
		return password.getText().split(":")[1].trim();
	}

	public void goTo() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	public DashboardPage Validlogin(String Username, String Password) {
		usernameinputfeild.sendKeys(Username);
		passwordinputfeild.sendKeys(Password);
		submitlogincta.click();
		//Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login Failed!!");
		DashboardPage dashboard = new DashboardPage(driver);
		return dashboard;
	}

	public void InvalidUsernamelogin(String Username, String Password) {
		usernameinputfeild.sendKeys(Username);
		passwordinputfeild.sendKeys(Password);
		submitlogincta.click();
		waitForElementToBeVisible(errormessagealert);
		Assert.assertEquals("Invalid credentials", erroralert.getText().trim());
	}

	public void Invalidpasswordlogin(String Username, String Password) {
		usernameinputfeild.sendKeys(Username);
		passwordinputfeild.sendKeys(Password);
		submitlogincta.click();
		waitForElementToBeVisible(errormessagealert);
		Assert.assertEquals("Invalid credentials", erroralert.getText().trim());
	}

	public void Emptyusernamelogin(String Password) {
		passwordinputfeild.sendKeys(Password);
		submitlogincta.click();
		waitForElementToBeVisible(FormValidation);
		Assert.assertTrue(FormValidationerror.isDisplayed(), "FroentEnd Validation is not Visible");
		Assert.assertEquals(FormValidationerror.getText().trim(), "Required", "Incorrect validtion message");
	}

	public void Emptypasswordlogin(String Username) {
		usernameinputfeild.sendKeys(Username);
		submitlogincta.click();
		waitForElementToBeVisible(FormValidation);
		Assert.assertTrue(FormValidationerror.isDisplayed(), "FroentEnd Validation is not Visible");
		Assert.assertEquals(FormValidationerror.getText().trim(), "Required", "Incorrect validtion message");
	}

	public void Emptyusernameandpasswordlogin() {
		submitlogincta.click();
		Assert.assertEquals(FormValidationerrorS.size(), 2, "Expected 2 Validtion errors");
		for (WebElement error : FormValidationerrorS) {
			Assert.assertTrue(error.isDisplayed(), "FormError is not visible" + error);
			Assert.assertEquals(error.getText().trim(), "Required", "Incorrect validtion message");
		}
	}
	
	public void passwordmasking(String Password) {
		passwordinputfeild.sendKeys(Password);
		Assert.assertEquals( getpasswordfeildtype(),"password", "Password is not maked");
	}
	
	public  String getpasswordfeildtype() {
		return passwordinputfeild.getAttribute("type");
	}

}
