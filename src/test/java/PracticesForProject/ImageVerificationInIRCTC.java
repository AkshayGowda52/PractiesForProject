package PracticesForProject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ImageVerificationInIRCTC 
{
	//Capturing a buddha image
	@Test
	public void imageVerification() throws Throwable 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.irctc.co.in/");
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		
		WebElement readmore = driver.findElement(By.xpath("//strong[text()='Read More']"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView();", readmore);
		
		
		Thread.sleep(2000);
		WebElement buddhaimg = driver.findElement(By.xpath("//img[@alt='Tourist Trains']"));
		File src = buddhaimg.getScreenshotAs(OutputType.FILE);
		File hrd = new File("./ScreenShots/"+"buddhish img"+".png");
		Files.copy(src, hrd);
		
	}
	
	//Capturing a mountain image
	@Test
	public void imagescreenShot() throws Throwable
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.irctc.co.in/");
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		
        WebElement readmore = driver.findElement(By.xpath("//strong[text()='Read More']"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView();", readmore);
		
		Thread.sleep(2000);
		WebElement Mountainsimg = driver.findElement(By.xpath("//img[@alt='Rail/ Land Tour Packages']"));
		File src = Mountainsimg.getScreenshotAs(OutputType.FILE);
		File hrd = new File("./ScreenShots/"+"Mountainsimg img"+".png");
		Files.copy(src, hrd);
		
	}
	
	@Test
	public void imageComparing() throws Throwable
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.irctc.co.in/");
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		
		WebElement readmore = driver.findElement(By.xpath("//strong[text()='Read More']"));
		
		//positive scenario
		BufferedImage Expectedbuddaimage = ImageIO.read(new File("./ScreenShots/"+"buddhish img"+".png"));
		
		//Negative scenario
//		BufferedImage Expectedbuddaimage = ImageIO.read(new File("./ScreenShots/"+"Mountainsimg img"+".png"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView();", readmore);
		
	
		Thread.sleep(2000);
		WebElement buddhaimg = driver.findElement(By.xpath("//img[@alt='Tourist Trains']"));
		File actualbuddaimg = buddhaimg.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(actualbuddaimg, new File("./ScreenShots/"+"buddhishActual img"+".png"));
	
		BufferedImage buffImg = new BufferedImage(buddhaimg.getSize().getWidth(), buddhaimg.getSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
		BufferedImage buffimg = ImageIO.read(actualbuddaimg);
		
		ImageDiffer imgcomparing = new ImageDiffer();
		ImageDiff comparing = imgcomparing.makeDiff(Expectedbuddaimage, buffimg);
		System.out.println(comparing.hasDiff());
		
		Assert.assertTrue(!comparing.hasDiff(), "differnt img");
		System.out.println("image is verifide");
		
//		if (compering.equals(Expectedbuddaimage)) {
//			System.out.println("image is verifed");
//		}
//		else {
//			System.out.println("image is not verified");
//		}
	}
	
	@Test
	public void imageVerifing() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.online-convert.com/");
		WebElement onlineConvertorimg = driver.findElement(By.xpath("//img[@class='header-logo-responsive d-none d-sm-block']"));
		
		Screenshot screenShot = new AShot().takeScreenshot(driver, onlineConvertorimg);
		File scr = new File("./ScreenShots/"+"OnlineScreenShot"+".png");
		
		ImageIO.write(screenShot.getImage(), "png", scr);
		
		BufferedImage expectedimg = ImageIO.read(scr);
		
		BufferedImage actualimg = screenShot.getImage();
		
		ImageDiffer diff = new ImageDiffer();
		ImageDiff comparingImg = diff.makeDiff(expectedimg, actualimg);
		if (comparingImg.hasDiff()) {
			System.out.println("both image are different");
		}
		else
		{
			System.out.println("both image are same");
		}		
	}
}
