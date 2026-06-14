package karthikakkasaligar.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import karthikakkasaligar.PageObjectModel.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage login;
	
	public WebDriver initializedriver() throws IOException {
		
			Properties properties=new Properties();
			FileInputStream fileinputstream=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\GlobalProperties\\GlobalProperties.properties");
			properties.load(fileinputstream);
			String browsername= properties.getProperty("browser");
			
			if(browsername.equalsIgnoreCase("chrome")) {
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--incognito");
				driver=new ChromeDriver(options);
			}
			
			else if(browsername.equalsIgnoreCase("firefox")) {
				FirefoxOptions options=new FirefoxOptions();
				options.addArguments("--incognito");
				driver=new FirefoxDriver(options);
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return driver;
	}
	
	
	@BeforeMethod
	public void LaunchWebsite() throws IOException {
		driver= initializedriver();
		login = new LoginPage(driver);
		login.goTo();
		
	}
	
	@AfterMethod
	public void Teardown() {
		driver.quit();
	}
	
	
	
	
	
	
	
	

}
