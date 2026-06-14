package karthikakkasaligar.PageObjectModel;

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

	By errormessagealert = By.cssSelector("[role*='alert']");

	public String getusername() {
		return username.getText().split(":")[1].trim();
	}

	public String getpassword() {
		return password.getText().split(":")[1].trim();
	}

	public void goTo() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	public void Validlogin(String Username, String Password) {
		usernameinputfeild.sendKeys(Username);
		passwordinputfeild.sendKeys(Password);
		submitlogincta.click();
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard/index"), "Login Failed!!");
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

}
