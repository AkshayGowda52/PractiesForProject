package PracticesForProject;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Swiggy {

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		//Mobile view testing for iphoneXR
		Map<String, Object> device = new HashMap<String, Object>();
		device.put("width", 414);
		device.put("height", 896);
		device.put("deviceScaleFactor", 50);
		device.put("mobile", true);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", device);
		
		
		driver.get("https://www.swiggy.com/");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
		
		//driver.findElement(By.xpath("//a[text()='bangalore']")).click();
		
		List<WebElement> citynames = driver.findElements(By.xpath("//div[@class='_1_sSy']//ul//li"));
		for (WebElement webElement : citynames) 
		{
			WebElement city = driver.findElement(By.xpath("//div[@class='_1_sSy']//ul//li/a[text()='bangalore']"));
			city.click();
			break;
		}
		
		js.executeScript("window.scrollBy(0,800)");
		
		driver.findElement(By.xpath("//img[@alt='South Indian']")).click();
		
		
		List<WebElement> hotelnames = driver.findElements(By.xpath("//div[@class='_129-b']/div[@class='MZy1T']/div/a/div/div/div/div[@class='nA6kb']"));
//		for (WebElement webElement1 : hotelnames) 
//		{
//			String hotlenametext = webElement1.getText();
//			System.out.println(hotlenametext);
//		}
		
		List<WebElement> price = driver.findElements(By.xpath("//div[@class='_129-b']/div[@class='MZy1T']/div/a/div/div/div/following::div[@class='_3Mn31']/div[@class='nVWSi']"));
		List<WebElement> ratings = driver.findElements(By.xpath("//div[@class='_129-b']/div[@class='MZy1T']/div/a/div/div/div/following::div[@class='_3Mn31']/div[@class='_9uwBC wY0my']"));
		for (int i = 0; i < hotelnames.size(); i++) 
		{
			for (int j = 0; j < price.size(); j++) 
			{
				if(i==j)
				{
					String hotelnametext = hotelnames.get(i).getText();
					String pricevalue = price.get(i).getText();
					System.out.println(hotelnametext+" ===> "+pricevalue);
				}
			}
			for (int k = 0; k < ratings.size(); k++) 
			{
				if(i==k)
				{
					String rating = ratings.get(i).getText();
					System.out.println(" ===> "+rating);
				}
			}
		}
		
		driver.findElement(By.xpath("//div[@class='_129-b']/div[@class='MZy1T']/div/a/div/div/div/div[text()='Meghana Foods']")).click();
		
		
		List<WebElement> dishnames = driver.findElements(By.xpath("//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemName__hLfgz']"));
		//List<WebElement> dishprices = driver.findElements(By.xpath("//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemPortionContainer__1u_tj']"));
		List<WebElement> dishprices = driver.findElements(By.xpath("//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemPortionContainer__1u_tj']/span/span[@class='rupee']"));
		for (int i = 0; i < dishnames.size(); i++) 
		{
			for (int j = 0; j < dishprices.size(); j++) 
			{
				if(i==j)
				{
					String dishname = dishnames.get(i).getText();
					String dishprice = dishprices.get(j).getText();
					System.out.println(dishname+" ====> "+dishprice);
				}
			}
		}
		driver.close();
	}

}
