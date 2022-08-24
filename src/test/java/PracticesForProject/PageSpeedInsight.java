package PracticesForProject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PageSpeedInsight {

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("pagespeed insite",Keys.ENTER);
		driver.findElement(By.xpath("//h3[text()='PageSpeed Insights']")).click();
		driver.findElement(By.xpath("//input[@class='VfPpkd-fmcmS-wGMbrd ']")).sendKeys("https://www.irctc.co.in/");
		driver.findElement(By.xpath("//span[text()='Analyze']")).click();
		
	
		
		List<WebElement> elements = driver.findElements(By.xpath("//label[@class='ucyZQe moNF0b' and@for='c9']/following-sibling::div[@class='OyzgL']"));
		
	   for (WebElement webElement : elements) 
	   {
		   
		   String el = webElement.getText();
		   System.out.println(el);
	   }
	}
	
	@DataProvider
	public  Object[] dataproviderTest()
	{
		Object[] a = new Object[3];
		a[0] ="http://google.com/";
		a[1] ="http://irctc.com/";
		a[2] ="http://testyantra.com/";
		return a;
	}

}
	
