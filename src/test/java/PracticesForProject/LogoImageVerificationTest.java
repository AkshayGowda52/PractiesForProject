package PracticesForProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogoImageVerificationTest 
{
	@Test
	public void logoVerification() throws Throwable
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.inviul.com/");
		
		Thread.sleep(2000);
		
		WebElement invilulogo = driver.findElement(By.xpath("//h1[@id='logo']//a //img"));
		if(invilulogo.isDisplayed())
		{
			System.out.println("logo is verified"+" "+invilulogo.getAttribute("alt"));
		}else
		{
			System.out.println("logo is not verified");
		}
		
	}
}
