package karthikakkasaligar.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import karthikakkasaligar.ReuseableComponents.ReuseableComponentsPage;

public class DashboardPage extends ReuseableComponentsPage {

	WebDriver driver;
	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".oxd-userdropdown-tab")
	WebElement usermenu;
	
	@FindBy(xpath="(//a[@class='oxd-userdropdown-link'])[4]")
	WebElement LogoutCta;
	
	By logoutbutton=By.xpath("(//a[@class='oxd-userdropdown-link'])[4]");
	
	public void Logout() {
		usermenu.click();
		waitForElementToBeVisible(logoutbutton);
		LogoutCta.click();
		Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Logout is Not Sucessful");
	}

}
