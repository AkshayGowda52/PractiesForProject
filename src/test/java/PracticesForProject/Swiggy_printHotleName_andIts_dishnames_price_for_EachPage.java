package PracticesForProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
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

public class Swiggy_printHotleName_andIts_dishnames_price_for_EachPage
{
	static WebDriver driver;
	static SwiggyHomePage swiggyHomePage;
	static SwiggyHotelManuPage swiggyHotelManuPage;


	public static void main(String[] args) throws Throwable 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.swiggy.com/");
		
		FileInputStream fis = new FileInputStream("./src/test/resources/UserNames.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s = wb.getSheet("Sheet2");
		
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
		

		boolean flag = true;
		int k =1;
		while(flag) {
		try {
			driver.findElement(By.xpath("//div/a[@aria-label='Go to page "+k+"']")).click();
			
			int rr = 0;
			List<ArrayList> hlist = new ArrayList<ArrayList>();
			
			for (int i = 0; i < swiggyHomePage.getHotels().size(); i++)
			{
				String h = swiggyHomePage.getHotels().get(i).getText();
				System.out.println("===========================================");
				System.out.println(h);
				System.out.println("===========================================");
				int y = swiggyHomePage.getHotels().get(i).getLocation().getY();
				js.executeScript("window.scrollBy(0,-1200)");
				swiggyHomePage.getHotels().get(i).click();
				
				
				List<WebElement> dishname = driver.findElements(By.xpath("//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemName__hLfgz']"));
				List<WebElement> dishprices = driver.findElements(By.xpath("//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemPortionContainer__1u_tj']/span/span[@class='rupee']"));


				for (int j=0;j<dishname.size();j++)
				{
					ArrayList<String> a1 = new ArrayList<String>();
					a1.add(h);
					String hotellocation = driver.findElement(By.xpath("//div[@class='Gf2NS _2Y6HW']")).getText();
					a1.add(hotellocation);
					a1.add(dishname.get(j).getText());
					a1.add(dishprices.get(j).getText());
					hlist.add(a1);
				}

				for (ArrayList arrayList : hlist) {
					System.out.println(arrayList);				
				}

				driver.navigate().back();
			}
			int o = rr;
			for (int l = o; l < hlist.size(); l++)
			{
				Row r = s.createRow(l);

				for (int j = 0; j < hlist.get(l).size(); j++) 
				{
					r.createCell(j).setCellValue(hlist.get(l).get(j).toString());
				}

				rr=l+1;
			}
			rr=rr;
			FileOutputStream fout = new FileOutputStream("./src/test/resources/UserNames.xlsx");
			wb.write(fout);
		
			
		} catch (Exception e) {
			e.printStackTrace();
			break;

		}
		k++;
		}

	}
}




