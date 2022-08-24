package News_18Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class News18_ClickOnSearchIcon {

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://gujarati.news18.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("document.querySelector('[class=\"jsx-2746090766 Menu_top_search_icond__lwBVu\"]:nth-child(2)',':before').click();");
		driver.findElement(By.xpath("//input[@class='gsc-input']")).sendKeys("indian railway",Keys.ENTER);
		
	}

}
