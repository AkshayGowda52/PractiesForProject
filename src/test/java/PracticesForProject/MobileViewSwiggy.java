package PracticesForProject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.crm.ObjectRepository.SwiggyHomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileViewSwiggy {
	
	public static void main(String[] args) throws Throwable 
	{

		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		//Mobile view testing for pixel 3
		Map<String, Object> device = new HashMap<String, Object>();
		device.put("width", 393);
		device.put("height", 786);
		device.put("deviceScaleFactor", 50);
		device.put("mobile", true);
		
		
		//driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", device);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", device);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.swiggy.com/");
		
		SwiggyHomePage swiggyHomePage = new SwiggyHomePage(driver);
		
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
		
		js.executeScript("window.scrollBy(0,600)");
		
		driver.findElement(By.xpath("//img[@alt='South Indian']")).click();
		
		js.executeScript("window.scrollBy(0,1000)");
		
		 ArrayList<String> hotels = new ArrayList<String>();
		boolean flag = true;
		int i =1;
		while(flag) {
		try {
			driver.findElement(By.xpath("//div/a[@aria-label='Go to page "+i+"']")).click();
			List<WebElement> hotelnames = driver.findElements(By.xpath("//div[@class='_129-b']/div[@class='MZy1T']/div/a/div/div/div/div[@class='nA6kb']"));
			for (WebElement webElement : hotelnames) {
				hotels.add(webElement.getText());

			}
		} catch (Exception e) {
			break;

		}
		i++;
		}
		for (String string : hotels) {
			System.out.println(string);
		}
	}

}
