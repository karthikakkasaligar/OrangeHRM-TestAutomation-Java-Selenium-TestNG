package karthikakkasaligar.TestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ValaidLogin {

	public static void main(String[] args) {
		
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--incognito");
		WebDriver driver=new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	    String usernametext=driver.findElement(By.xpath("//div[@class='orangehrm-login-error']/div/p")).getText().trim();
	    String Username=usernametext.split(":")[1].trim();
	    String PasswordText=driver.findElement(By.xpath("(//div[@class='orangehrm-login-error']/div/p)[2]")).getText().trim();
	    String Password=PasswordText.split(":")[1].trim();
	    driver.findElement(By.cssSelector("[placeholder='Username']")).sendKeys(Username);
	    driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys(Password);
	    driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
	    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard/index"), "Login Failed!!");
	    driver.quit();
		
		
	}

}
