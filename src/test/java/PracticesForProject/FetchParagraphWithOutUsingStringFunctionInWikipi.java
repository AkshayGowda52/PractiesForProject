package PracticesForProject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchParagraphWithOutUsingStringFunctionInWikipi {

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://en.wikipedia.org/wiki/IPhone_12");
		String paragraph = driver.findElement(By.xpath("//div[@id='mw-content-text']//div/p[3]")).getText();
	
		
		String[] pgh = paragraph.split(" ");
		int pghcount = pgh.length;
		
		int count = 0;
	
		
//		ArrayList<String> arr = new ArrayList<String>();
//		for (int i = 0; i <paragraph.length(); i++) {
//			arr.add(paragraph);
//			break;
//		}
//		System.out.println(arr);
        
		String first = "iPhone";
		String lsat = "Mac";
		for (int i = 0; i < pgh.length; i++) {
			if(pgh[i].contains(first))
			{
				count = i;
				break;
			}
		}
		System.out.println(pghcount);
		System.out.println(count);
		
		for (int i = count; i < pghcount; i++) {
			if (pgh[i].contains(lsat)) {
				break;
			}
			else
			{
				System.out.print(pgh[i]+" ");
			}
		}
		
//		for (String string : arr) {
//			System.out.println(string.substring(23, 419));
//			
//		}
		
	}

}
