package PracticesForProject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import News18_ObjectRepository_for_Ads_Dimensions.News18_Ads_Dimensions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class News18_Ads_Dimension {


	WebDriver driver; 
	News18_Ads_Dimensions news18_Ads_Dimension;
	@BeforeSuite
	public void LaunchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		news18_Ads_Dimension = new News18_Ads_Dimensions(driver);
		driver.get("https://www.news18.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("--disable-notifications");
	}

	@Test
	public void First_Ad() throws Throwable
	{  
		Thread.sleep(1000);	
		news18_Ads_Dimension.ClickOnadsblocks(driver);
//		news18_Ads_Dimension.databases(driver);
//		news18_Ads_Dimension.comperingDb_values(driver);
		
	}

}
