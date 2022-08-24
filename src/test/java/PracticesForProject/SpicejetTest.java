package PracticesForProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpicejetTest {

	public static void main(String[] args) throws Throwable 
	{
		String Date = "26";
		String Month = "June";
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(opt);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		FileInputStream file = new FileInputStream("./src/test/resources/UserNames.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet("Sheet1");
		DataFormatter formatter = new DataFormatter();
		String value1 = formatter.formatCellValue(sh.getRow(0).getCell(1));
		String value2 = formatter.formatCellValue(sh.getRow(0).getCell(2));
		String value3 = formatter.formatCellValue(sh.getRow(0).getCell(3));
		String value4 = formatter.formatCellValue(sh.getRow(0).getCell(4));
		String value5 = formatter.formatCellValue(sh.getRow(0).getCell(5));
		
		String value6 = formatter.formatCellValue(sh.getRow(1).getCell(1));
		String value7 = formatter.formatCellValue(sh.getRow(1).getCell(2));
		String value8 = formatter.formatCellValue(sh.getRow(1).getCell(3));

		
		 
		driver.get("https://www.spicejet.com/");
	    driver.findElement(By.xpath("//input[@class='css-1cwyjr8 r-homxoj r-ubezar r-10paoce r-13qz1uu' and@type='text']")).sendKeys("Del");
		driver.findElement(By.xpath("//div[text()='To']/ancestor::div[@data-testid='to-testID-destination']/descendant::input[@class='css-1cwyjr8 r-homxoj r-ubezar r-10paoce r-13qz1uu']")).sendKeys("Bangaluru");
		driver.findElement(By.xpath("//div[contains(text(),'"+Month+" ')]/following::div/div/div/div/div[contains(text(),'"+Date+"')]")).click();

		driver.findElement(By.xpath("//div[text()='Passengers']")).click();
		driver.findElement(By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']")).click();
		driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-1pcd2l5 r-1uwte3a r-m611by r-bnwqim']")).click();
		driver.findElement(By.xpath("//div[@data-testid='home-page-flight-cta']")).click();
		
		List<WebElement> flightname = driver.findElements(By.xpath("//div[@class='css-1dbjc4n r-13awgt0']/following::div[@class='css-76zvg2 r-homxoj r-1i10wst']"));
		int Fname = flightname.size();
		
		List<WebElement> flightprice = driver.findElements(By.xpath("//div[@class='css-1dbjc4n r-1awozwy r-1sgu7fw r-1ljd8xs r-1phboty r-1777fci r-e9bn0q']/following::div[@style='position: relative; z-index: 1;']"));
		int Fprice = flightprice.size();

		for (int i = 0; i < flightname.size(); i++) 
		{
			for (int j = 0; j < flightprice.size(); j++) 
			{
				if(i==j)
				{
					 String name = flightname.get(i).getText();
					 String price = flightprice.get(i).getText();
					System.out.println(name+" "+price);
				}
			}
		}
		
		FileInputStream files = new FileInputStream("./src/test/resources/user name and password.xlsx");
		Workbook wb1 = WorkbookFactory.create(files);
		Sheet sh1 = wb1.getSheet("Sheet2");
	
		for (int i = 0; i <Fname  ; i++)
		{
			sh.createRow(i).createCell(0).setCellValue(flightname.get(i).getText());
			sh.getRow(i).createCell(1).setCellValue(flightprice.get(i).getText());
		}

		FileOutputStream fos = new FileOutputStream("./src/test/resources/user name and password.xlsx");
		wb.write(fos);
		wb.close();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-obd0qt r-1n9sb9w']/ancestor::div[@class='css-1dbjc4n r-18u37iz r-1wtj0ep']/descendant::div[@class='css-1dbjc4n r-1awozwy r-1xfd6ze r-1loqt21 r-18u37iz r-1777fci r-1w50u8q r-ah5dr5 r-1otgn73']/following-sibling::div")).click();
		
		WebElement continueclick = driver.findElement(By.xpath("//div[@id='popup-description2']/following-sibling::div/div/following::div/button"));
		js.executeScript("arguments[0].click();", continueclick);
		
		driver.findElement(By.xpath("//input[@class='css-1cwyjr8 r-homxoj r-poiln3 r-ubezar r-1eimq0t r-1e081e0 r-xfkzu9 r-lnhwgy']")).sendKeys(value1,Keys.TAB, value2,Keys.TAB,Keys.TAB,value3,Keys.TAB,value4,Keys.TAB,Keys.TAB,value5,Keys.TAB);
		driver.findElement(By.xpath("//div[text()='Passenger Information']/../descendant::div[@class='css-1dbjc4n r-zso239']/*[name()='svg' and@data-testid='svg-img']")).click();
		
		driver.findElement(By.xpath("//div[text()='Passenger 2']")).click();
		
		driver.findElement(By.xpath("//input[@class='css-1cwyjr8 r-homxoj r-poiln3 r-ubezar r-1eimq0t r-1e081e0 r-xfkzu9 r-lnhwgy' and @data-testid='traveller-1-first-traveller-info-input-box']")).sendKeys(value6,Keys.TAB,value7,Keys.TAB,Keys.TAB,value8,Keys.TAB);
		driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-1xcajam r-1biggbk']/div/div/following::div")).click();
		
		WebElement ad = driver.findElement(By.xpath("//div[@class='at_addon_close']/img"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(ad)).click();
		
		Actions act = new Actions(driver);
		act.moveToElement(ad);
		
		//driver.findElement(By.xpath("//div[text()='Payable Amount']/ancestor::div[@class='css-1dbjc4n r-13awgt0 r-1biggbk']/div[@class='css-1dbjc4n r-1awozwy r-19m6qjp r-z2wwpe r-1loqt21 r-18u37iz r-1777fci r-6ity3w r-d9fdf6 r-9qu9m4 r-ah5dr5 r-1otgn73']/following::div[@class='css-1dbjc4n r-1awozwy r-19m6qjp r-z2wwpe r-1loqt21 r-18u37iz r-1777fci r-6ity3w r-d9fdf6 r-9qu9m4 r-ah5dr5 r-1otgn73']/following::div/div[@class='css-76zvg2 r-jwli3a r-poiln3 r-adyw6z r-1kfrs79']/following::div[@class='css-1dbjc4n r-1niwhzg r-1p0dtai r-1d2f490 r-1udh08x r-u8s1d r-zchlnj r-ipm5af']")).click();
	
	}
}
