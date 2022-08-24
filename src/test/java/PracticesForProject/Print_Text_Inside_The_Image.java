package PracticesForProject;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.asprise.ocr.Ocr;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Print_Text_Inside_The_Image {

	@Test
	public void printtextinsidetheimage()
	{
		Ocr ocr = new Ocr(); 
		ocr.startEngine("eng", Ocr.SPEED_FASTEST);
															
		String imgtext = ocr.recognize(new File[] {new File("C:\\Users\\Akshay\\OneDrive\\Desktop\\Photo\\l002-social.jpg")}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
		System.out.println(imgtext);
		ocr.stopEngine();
	}
	
	@Test
	public void javaproghram()
	{
		String str = "apple.ball.cat.dog";
		String[] str1 = str.replace(".", " ").split(" ");
		System.out.println(str1);
		System.out.println(str1[3]);
	}
	
	@Test
	public void sendkey() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://www.cricbuzz.com/");
		driver.findElement(By.xpath("//span[@class='cb-ico cb-search-input-icon cb-search-input-icon-nav']")).click();
//		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("india",Keys.CONTROL +"a",Keys.CONTROL+"c");
//		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("england",Keys.ENTER);
//		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(Keys.TAB.ENTER);
//		WebElement element = driver.findElement(By.xpath("//input[@name='search']"));
//		element.sendKeys(Keys.TAB.ENTER);
//		element.sendKeys(Keys.ENTER);
		
		WebElement element = driver.findElement(By.xpath("//input[@name='search']"));
		Actions act = new Actions(driver);
		act.sendKeys(element, "abc").perform();
		act.sendKeys(Keys.ARROW_UP).perform();
		
	
	}
}
