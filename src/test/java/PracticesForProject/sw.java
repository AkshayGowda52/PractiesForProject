package PracticesForProject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.ObjectRepository.SwiggyHomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class sw {

	public static void main(String[] args)
	{
		
		
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
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
		
//		 ArrayList<String> hotels = new ArrayList<String>();
//		boolean flag = true;
//		int i =1;
//		while(flag) {
//		try {
//			driver.findElement(By.xpath("//div/a[@aria-label='Go to page "+i+"']")).click();
//			List<WebElement> hotelnames = driver.findElements(By.xpath("//div[@class='_129-b']/div[@class='MZy1T']/div/a/div/div/div/div[@class='nA6kb']"));
//			for (WebElement webElement : hotelnames) {
//				hotels.add(webElement.getText());	
//			}
//		} catch (Exception e) {
//			break;
//
//		}
//		i++;
//		}
//		for (String string : hotels) {
//			System.out.println(string);
//		}
	    //List<WebElement> pages = driver.findElements(By.xpath("//div[@class='_2OmLw']/a"));
//	    int pageCount = swiggyHomePage.getPage().size();
//			for (int j = 1; j < swiggyHomePage.getPage().size() ; j++) 
//			{
//				for (int i = 0; i < swiggyHomePage.getHotels().size(); i++)
//		        {
//					 
//		        	String hotelname = swiggyHomePage.getHotels().get(i).getText();
//					System.out.println(hotelname);
//					
//				}
//				WebElement pa = swiggyHomePage.getPage().get(j);
//				Thread.sleep(2000);
//				js.executeScript("window.scrollBy(0,400)");
//				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//				wait.until(ExpectedConditions.elementToBeClickable(pa));
//				
//				pa.click();
//				pageCount = swiggyHomePage.getPage().size();
////				System.out.println(pageCount);
//				
//			}
			
        
		//driver.findElement(By.xpath("//div[@class='_2OmLw']/a[@aria-label='Go to page 3']")).click();
        
        

	}

}
