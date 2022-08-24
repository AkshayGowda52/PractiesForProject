package PracticesForProject;

import java.sql.DriverManager;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowDom {

	public static void main(String[] args) throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://unsplash.com/");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		driver.findElement(By.xpath("//img[@class='YVj9w' and@data-perf='lazy-loaded-img']")).click();
		driver.findElement(By.xpath("//span[text()='Download free']")).click();
		Thread.sleep(3000);
		
		String window = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		
		driver.get("chrome://downloads/");
		
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
	    WebElement	web=(WebElement)js1.executeScript("return document.querySelector(\"body > downloads-manager\").shadowRoot.querySelector(\"#frb0\").shadowRoot.querySelector(\"#show\")");
		web.click();
//	    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", web);
		
	}

}
