package News_18Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import News18_ObjectRepository.News18_Gujarati_HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class News18_Gujarati_HomePageTest {
	
	News18_Gujarati_HomePage new18gujarthhomepage;
	WebDriver driver;
	
	@BeforeSuite
	public void LaunchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
	    new18gujarthhomepage = new News18_Gujarati_HomePage(driver);
		
		driver.get("https://gujarati.news18.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		
	}
	
	@Test
	public void clickonsearchicon()
	{
		new18gujarthhomepage.clickOnSearchicon(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("document.querySelector('[class=\"jsx-2746090766 Menu_top_search_icond__lwBVu\"]:nth-child(2)',':before').click();");
		
	}

}
