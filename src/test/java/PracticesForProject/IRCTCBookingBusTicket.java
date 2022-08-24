package PracticesForProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JComboBox.KeySelectionManager;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This Class contains end to end scenario book a bus ticket in IRTCT web site,
 * @author akshay 
 *
 */
public class IRCTCBookingBusTicket {

	public static void main(String[] args) throws InterruptedException, Throwable  
	{

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.irctc.co.in/");
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		
		
		 List<WebElement> header1 = driver.findElements(By.xpath("//div[@class='row col-sm-12 h_head1']"));
		for (WebElement webElement1 : header1) 
		{
			System.out.println(webElement1.getText());
		}
		WebElement hindi = driver.findElement(By.xpath("//strong[text()='हिंदी']"));
		System.out.println(hindi.getText());
		
		List<WebElement> header2 = driver.findElements(By.xpath("//nav[@class='nav-bar hidden-xs text-right']"));
		for (WebElement webElement2 : header2) {
			System.out.println(webElement2.getText());
		}
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()=' BUSES ']")).click();
		
		
		Set<String> window = driver.getWindowHandles();
		for (String string : window) {
			 driver.switchTo().window(string);
		}
		
		FileInputStream file = new FileInputStream("src\\test\\resources\\akshay.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet("Sheet1");
		DataFormatter formatter = new DataFormatter();
		String value1 = formatter.formatCellValue(sh.getRow(4).getCell(0));
		String value2 = formatter.formatCellValue(sh.getRow(4).getCell(1));
		String value3 = formatter.formatCellValue(sh.getRow(5).getCell(0));
		String value4 = formatter.formatCellValue(sh.getRow(6).getCell(0));
		String value5 = formatter.formatCellValue(sh.getRow(7).getCell(0));
		String value6 = formatter.formatCellValue(sh.getRow(7).getCell(1));
		String value7 = formatter.formatCellValue(sh.getRow(7).getCell(2));
		String value8 = formatter.formatCellValue(sh.getRow(7).getCell(3));
		String value9 = formatter.formatCellValue(sh.getRow(7).getCell(4));
		String value10 = formatter.formatCellValue(sh.getRow(5).getCell(1));
		String value11 = formatter.formatCellValue(sh.getRow(7).getCell(5));
		String value12 = formatter.formatCellValue(sh.getRow(7).getCell(6));
		String value13 = formatter.formatCellValue(sh.getRow(5).getCell(2));
		String date = formatter.formatCellValue(sh.getRow(9).getCell(0));
		String year = formatter.formatCellValue(sh.getRow(9).getCell(1));
		

		
		WebElement departure = driver.findElement(By.xpath("//input[@id='departFrom']"));
		departure.sendKeys(value1);
		driver.findElement(By.xpath("//div[text()='Bangalore']")).click();
			
		
		driver.findElement(By.xpath("//input[@id='goingTo']")).sendKeys(value2);
		driver.findElement(By.xpath("//div[text()='Chennai']")).click();
		
		
		String datexpath = "//span[text()='"+year+"']/ancestor::div[@id='ui-datepicker-div']/descendant::a[text()='"+date+"']";
		try {
		driver.findElement(By.xpath(datexpath)).click();
		}catch (Exception e) {
			driver.findElement(By.xpath("//span[text()='Next']")).click();
		}
		
		driver.findElement(By.xpath("//button[text()='Search Bus ']")).click();
		
		driver.findElement(By.xpath("//h4[text()='Departure Time']/following-sibling::ul//img[@alt='After 6 pm']")).click();
		List<WebElement> buslist = driver.findElements(By.xpath("//div[@class='SearchCard SearchCard01']"));
		for (WebElement webElement3 : buslist) 
		{
			System.out.println(webElement3.getText());
		}
		
		driver.findElement(By.xpath("//label[text()='SRS Travels']/ancestor::div[@class='top-search-result-card']/descendant::button[text()='Select Seat']")).click();
		driver.findElement(By.xpath("//h5[text()='Upper Deck']/following-sibling::div//div/span[@class='Sleeper_V'][5]")).click();
		driver.findElement(By.xpath("//h5[text()='Upper Deck']/following-sibling::div//div/span[@class='Sleeper_V'][4]")).click();
		
		driver.findElement(By.xpath("//input[@name='bordTime']")).click();
		driver.findElement(By.xpath("//button[text()='Proceed to Book']")).click();
		
		driver.findElement(By.xpath("//a[@id='profile-tab']")).click();
				
		
		driver.findElement(By.xpath("//input[@id='emailLogin']")).sendKeys(value3);
		driver.findElement(By.xpath("//input[@id='phoneLogin']")).sendKeys(value13,Keys.ENTER);
		
		driver.findElement(By.xpath("//input[@name='address']")).sendKeys(value4);
		WebElement e1 = driver.findElement(By.xpath("//select[@name='state']"));
		Select sel = new Select(e1);
		sel.selectByVisibleText(value5);
		
		driver.findElement(By.xpath("//input[@name='pincode']")).sendKeys("560056");
		driver.findElement(By.xpath("//input[@placeholder='Traveller Name']")).sendKeys(value6);
		WebElement ele2 = driver.findElement(By.xpath("//select[@name='select']"));
		Select sel1 = new Select(ele2);
		sel1.selectByVisibleText(value8);
		driver.findElement(By.xpath("//input[@placeholder='Age']")).sendKeys(value11);
		
		driver.findElement(By.xpath("//label[text()=' Passenger 2 | Seat No: 15U ']/../following-sibling::div/input[@placeholder='Traveller Name']")).sendKeys(value7);
		WebElement ele3 = driver.findElement(By.xpath("//label[text()=' Passenger 2 | Seat No: 15U ']/../following-sibling::div/select[@name='select']"));
		Select sel2 = new Select(ele3);
		sel2.selectByVisibleText(value9);
		driver.findElement(By.xpath("//label[text()=' Passenger 2 | Seat No: 15U ']/../following-sibling::div/input[@placeholder='Age']")).sendKeys(value12);
		
		driver.findElement(By.xpath("//input[@id='agree']")).click();
		driver.findElement(By.xpath("//button[text()='Continue to Book ']")).click();
		
		String win = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://accounts.google.com/");
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(value3);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//div[text()='Enter your password']")).sendKeys(value10);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File hrd = new File("./ScreenShots/"+"bookticket"+".png");
		Files.copy(src, hrd);
		
		driver.close();
	}

}
