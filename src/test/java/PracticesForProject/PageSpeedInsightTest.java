package PracticesForProject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PageSpeedInsightTest 
{
	@Test(dataProvider="dataproviderTest")
	public void pagespeedinsightTest(String url)
	{
		{
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			
			driver.get("https://www.google.com");
			driver.findElement(By.xpath("//input[@name='q']")).sendKeys("pagespeed insite",Keys.ENTER);
			driver.findElement(By.xpath("//h3[text()='PageSpeed Insights']")).click();
			driver.findElement(By.xpath("//input[@class='VfPpkd-fmcmS-wGMbrd ']")).sendKeys(url);
			driver.findElement(By.xpath("//span[text()='Analyze']")).click();
			
		
			WebElement id = driver.findElement(By.xpath("//input[@class='GuMAkc'and@aria-label='Toggle the display of histogram breakdowns']"));
			String id1 = id.getAttribute("id");
			List<WebElement> elements1 = driver.findElements(By.xpath("//input[@id='"+id1+"']/following-sibling::div/div/span/a"));
			List<WebElement> elements2 = driver.findElements(By.xpath("//input[@id='"+id1+"']/following-sibling::div/div/div/div/span/span[@class='Ykn2A LR2yK']"));


			for (int i = 0; i<= elements1.size()-1; i++) 
			{
				for (int j = 0; j <= elements2.size()-1; j++) 
				{
					if(i==j)
					{
						System.out.println(elements1.get(i).getText()+" "+elements2.get(j).getText());
					}
				}
			}
		}
	}
	@DataProvider
	public  Object[] dataproviderTest()
	{
		Object[] a = new Object[3];
		a[0] ="http://google.com/";
		a[1] ="http://irctc.com/";
		a[2] ="http://Flipkart.com/";
		return a;
	}
}

