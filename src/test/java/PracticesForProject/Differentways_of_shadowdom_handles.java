package PracticesForProject;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.w3c.dom.css.DocumentCSS;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Differentways_of_shadowdom_handles {
	
	
	WebDriver driver;
	@Test
	public void shadowdom1() throws InterruptedException
	{
		
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		driver.get("https://unsplash.com/s/photos/wild-animal");

		driver.findElement(By.xpath("//img[@alt='brown elephant on green grass field during daytime']")).click();
		driver.findElement(By.xpath("//span[text()='Download free']")).click();
		
		String window = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		
		driver.get("chrome://downloads/");
		
		WebElement root1 = driver.findElement(By.tagName("downloads-manager"));
		SearchContext root = (SearchContext) js.executeScript("return arguments[0].shadowRoot", root1);
		
		
		WebElement shadowroot4 = root.findElement(By.cssSelector("downloads-item"));
		SearchContext shadowroot5 = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowroot4);
		
		Thread.sleep(2000);
		
		WebElement ele = shadowroot5.findElement(By.cssSelector("div[id='details']>div"));
		ele.click();
	}
}
