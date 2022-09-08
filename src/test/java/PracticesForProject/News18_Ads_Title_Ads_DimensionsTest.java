package PracticesForProject;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import News18_ObjectRepository_for_Ads_Dimensions.News18_Ads_Dimensions;
import News18_ObjectRepository_for_Ads_Dimensions.News18_Ads_Title_Ads_Dimensions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class News18_Ads_Title_Ads_DimensionsTest {


	WebDriver driver; 
	News18_Ads_Title_Ads_Dimensions News18_Ads_Title_Ads_Dimensions;
	
	@BeforeSuite
	public void LaunchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	    News18_Ads_Title_Ads_Dimensions = new News18_Ads_Title_Ads_Dimensions(driver);
		driver.get("https://www.news18.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("--disable-notifications");
	}

	@Test
	public void First_Ad() throws Throwable
	{  
		Thread.sleep(1000);	
		News18_Ads_Title_Ads_Dimensions.ClickOnadsblocks(driver);
		News18_Ads_Title_Ads_Dimensions.databases(driver);
		News18_Ads_Title_Ads_Dimensions.comperingDb_values(driver);
	}
	@AfterSuite
	private void QuiT() {
//	this method is used quit the connection from the server:
		driver.quit();

	}

}

