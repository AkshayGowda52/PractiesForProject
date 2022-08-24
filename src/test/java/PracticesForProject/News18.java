package PracticesForProject;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class News18 {

	public static void main(String[] args) throws Throwable 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.news18.com/topics/aiadmk/https://www.news18.com/topics/agnipath/");
		
		
		 WebElement dropdownElement = driver.findElement(By.xpath("//div[@class='jsx-3081444385 linner']"));
		 Actions act = new Actions(driver);
		 act.moveToElement(dropdownElement).perform();

		 List<WebElement> langList = driver.findElements(By.xpath("//div[@class='jsx-3081444385 lddnav']/a"));
		 for (int i = 0; i < langList.size(); i++) 
		 {
			 WebElement la = langList.get(i);
			 Thread.sleep(2000);
			 
//			 String windowId = driver.getWindowHandle();
//			 driver.switchTo().window(windowId);
//			 
//
//		
//			 WebElement error = driver.findElement(By.xpath("//div[@class='jsx-1294303084 pagenotFount']"));
//			
//			
//			 if(error.isDisplayed())
//			 {
//				 System.out.println("404 error is not pressent");
//			 }
//			 else
//			 {
//				 System.out.println("404 error is pressent");
//			 }
			
			 
			 Set<String> windows = driver.getWindowHandles();
			 for (String string : windows) 
			 {
				String title = driver.switchTo().window(string).getTitle();
				System.out.println(title);
				if (windows.contains(title)) 
				{
					 WebElement error = driver.findElement(By.xpath("//div[@class='jsx-1294303084 pagenotFount']"));
						
						
					 if(error.isDisplayed())
					 {
						 System.out.println("404 error is not pressent");
					 }
					 else
					 {
						 System.out.println("404 error is pressent");
					 }
				}
				la.click();
			}
			
			 
		 } 
		 

	}

}
