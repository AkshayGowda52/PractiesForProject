package PracticesForProject;



import java.time.Duration;

import org.apache.commons.lang.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindHowMuchTimeTakenToVisibilityOfWebElementInWebPage {
	@Test
	public void visibilityOfElementTime()
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		long start = System.currentTimeMillis();
		
		driver.get("https://www.swiggy.com/");
	
		WebElement elelogin = driver.findElement(By.xpath("//a[text()='Login']"));
		long end = System.currentTimeMillis();
		long totalTime = end - start;
		System.out.println("time taken to load the login element in webpage"+" "+totalTime);
		
	}
	
	@Test
	public void loginButtonLoadingTiming() 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("https://www.swiggy.com/");
		driver.manage().window().maximize();
		
		StopWatch eleload = new StopWatch();
		eleload.start();
		
//		driver.get("https://www.swiggy.com/");
//		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Login']")));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Bangalore']")));
		
		eleload.stop();
		
		long loginbuttonTiming = eleload.getTime();
		long pageLoadTime_Seconds = loginbuttonTiming / 1000;
		System.out.println("time taken to load the login element in webpage1"+" "+loginbuttonTiming);
		System.out.println("time taken to load the login element in webpage1"+" "+pageLoadTime_Seconds);
		
	}
}
