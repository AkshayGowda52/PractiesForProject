package PracticesForProject;

import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.crm.ObjectRepository.News18HomePage;
import com.mysql.cj.x.protobuf.MysqlxResultset.FetchSuspendedOrBuilder;

import News18_ObjectRepository.News18_HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class News18_Ad_VerificationTest 
{

	WebDriver driver; 
	News18_HomePage news18homepage;
	@BeforeSuite
	public void LaunchBrowser()
	{
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		news18homepage = new News18_HomePage(driver);
		driver.get("https://www.news18.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("--disable-notifications");
	}
	
	@Test
	public void Fiest_Ad() throws Throwable
	{  
		Thread.sleep(1000);	
//		news18homepage.adsblocks(driver);
		news18homepage.ClickOnadsblocks(driver);
		news18homepage.databases(driver);
		news18homepage.comperingDBvalue_ads(driver);
		news18homepage.databasesclose(driver);
	}

}
