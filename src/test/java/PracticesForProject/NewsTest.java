package PracticesForProject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewsTest {

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
			 
			 String windowId = driver.getWindowHandle();
			 driver.switchTo().window(windowId);
			 
			 
			 Thread.sleep(3000);
			 WebElement error = driver.findElement(By.xpath("//div[@class='jsx-1294303084 pagenotFount']"));
			
			
			 if(error.isDisplayed())
			 {
				 System.out.println("404 error is not pressent");
			 }
			 else
			 {
				 System.out.println("404 error is pressent");
			 }
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			 wait.until(ExpectedConditions.elementToBeClickable(la));
			 la.click();
			 
		 } 
		 driver.quit();
	}

}
