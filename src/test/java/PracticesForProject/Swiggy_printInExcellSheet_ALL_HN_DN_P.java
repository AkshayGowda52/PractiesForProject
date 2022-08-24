package PracticesForProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.ObjectRepository.SwiggyHomePage;
import com.crm.ObjectRepository.SwiggyHotelManuPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Swiggy_printInExcellSheet_ALL_HN_DN_P {
	
		public static void main(String[] args) throws Throwable 
		{
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().window().maximize();
			driver.get("https://www.swiggy.com/");
			
			SwiggyHomePage swiggyHomePage = new SwiggyHomePage(driver);
			SwiggyHotelManuPage swiggyHotelManuPage = new SwiggyHotelManuPage(driver);
			Swiggy_printHotleName_andIts_dishnames_price ho = new Swiggy_printHotleName_andIts_dishnames_price();
			
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,600)");
			
			
			
			List<WebElement> citynames = driver.findElements(By.xpath("//div[@class='_1_sSy']//ul//li"));
			for (WebElement webElement : citynames) 
			{
				WebElement city = driver.findElement(By.xpath("//div[@class='_1_sSy']//ul//li/a[text()='bangalore']"));
				city.click();
				break;
			}
			
			js.executeScript("window.scrollBy(0,600)");
			
			driver.findElement(By.xpath("//img[@alt='South Indian']")).click();
			List<WebElement> hotelnames = driver.findElements(By.xpath("//div[@class='_129-b']/div[@class='MZy1T']/div/a/div/div/div/div[@class='nA6kb']"));
			
			

				for (int i = 0; i < swiggyHomePage.getHotels().size(); i++) {
				
				System.out.println(swiggyHomePage.getHotels().get(i).getText());
				System.out.println("==========================");
				swiggyHomePage.getHotels().get(i).click();
				List<WebElement> dishname = driver.findElements(By.xpath("//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemName__hLfgz']"));
				List<WebElement> dishprices = driver.findElements(By.xpath("//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemPortionContainer__1u_tj']/span/span[@class='rupee']"));

				for (int k = 0; k < dishname.size(); k++)
				{
					System.out.println(dishname.get(k).getText()+"==="+dishprices.get(k).getText());
				}

				driver.navigate().back();
			}
				
				FileInputStream file = new FileInputStream("./src/test/resources/user name and password.xlsx");
				Workbook wb = WorkbookFactory.create(file);
				Sheet sh = wb.getSheet("Sheet2");
				for (int i = 0; i < swiggyHomePage.getHotels().size()-1; i++) 
				{
					sh.createRow(i+1).createCell(0).setCellValue(swiggyHomePage.getHotels().get(i).getText());
					sh.createRow(i+1).createCell(1).setCellValue(swiggyHotelManuPage.getDishName().get(i).getText());
					sh.createRow(i+1).createCell(2).setCellValue(swiggyHotelManuPage.getDishPrice().get(i).getText());
				}
				FileOutputStream fos = new FileOutputStream("./src/test/resources/user name and password.xlsx");
				wb.write(fos);
				wb.close();

		}
	}


