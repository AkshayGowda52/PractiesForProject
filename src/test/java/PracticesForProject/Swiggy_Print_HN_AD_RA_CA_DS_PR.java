package PracticesForProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.ObjectRepository.SwiggyHomePage;
import com.crm.ObjectRepository.SwiggyHotelManuPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Swiggy_Print_HN_AD_RA_CA_DS_PR {

	public static void main(String[] args) throws Throwable 
	{

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.swiggy.com/");
		
		FileInputStream fis = new FileInputStream("./src/test/resources/UserNames.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s = wb.getSheet("Sheet3");
		
		SwiggyHomePage swiggyHomePage = new SwiggyHomePage(driver);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,600)");
		WebElement element3 = driver.findElement(By.xpath("//h4[text()='WE DELIVER TO']"));
		js.executeScript("arguments[0].scrollIntoView();", element3);
		
		//driver.findElement(By.xpath("//a[text()='bangalore']")).click();
		
		List<WebElement> citynames = driver.findElements(By.xpath("//div[@class='_1_sSy']//ul//li"));
		for (WebElement webElement : citynames) 
		{
			WebElement city = driver.findElement(By.xpath("//div[@class='_1_sSy']//ul//li/a[text()='bangalore']"));
			city.click();
			break;
		}
		
		WebElement element = driver.findElement(By.xpath("//img[@alt='Offers near you']"));
//		js.executeScript("window.scrollBy(0,600)");
		js.executeScript("arguments[0].scrollIntoView();", element);
		//North Indian,South Indian,Biryani,Super Fast Delivery,Top Rated,Offers near you,
//		driver.findElement(By.xpath("//img[@alt='Offers near you']")).click();
//		driver.findElement(By.xpath("//div[text()='Offers near you' and@class='_17unN']")).click();
		driver.findElement(By.xpath("//div[@class='TIAfU']/div//div[text()='South Indian']")).click();
		
//		js.executeScript("window.scrollBy(0,1000)");
		

		boolean flag = true;
		int k =1;
		while(flag) {
		try {
			driver.findElement(By.xpath("//div/a[@aria-label='Go to page "+k+"']")).click();
			
			
			int rr = 0;
			List<ArrayList> hlist = new ArrayList<ArrayList>();
//		    Set<LinkedHashSet>	list = new LinkedHashSet<LinkedHashSet>();
			for (int i = 0; i <swiggyHomePage.getHotels().size() ; i++)
			{
				String h = swiggyHomePage.getHotels().get(i).getText();
				System.out.println("===========================================");
				System.out.println(h);
				System.out.println("===========================================");
//				int y = swiggyHomePage.getHotels().get(i).getLocation().getY();
				WebElement element1 = driver.findElement(By.xpath("//span[text()='South Indian']"));
//				js.executeScript("window.scrollBy(0,-1200)");
				js.executeScript("arguments[0].scrollIntoView();", element1);
				swiggyHomePage.getHotels().get(i).click();
				
				
				List<WebElement> dishname = driver.findElements(By.xpath("//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemName__hLfgz']"));
				List<WebElement> dishprices = driver.findElements(By.xpath("//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemPortionContainer__1u_tj']/span/span[@class='rupee']"));
				
			
				for (int j=0;j<dishname.size();j++)
				{
					ArrayList<String> a1 = new ArrayList<String>();
					a1.add(h);
					String hotellocation = driver.findElement(By.xpath("//div[@class='Gf2NS _2Y6HW']")).getText();
					a1.add(hotellocation);
					List<WebElement> ratings = driver.findElements(By.xpath("//div[@class='_1WDSQ']/div[@class='_1BpLF']/div[@class='_2aZit _2fC4N']/div[@class='_2iUp9 ']/div/span"));
					for (int l = 0; l < ratings.size(); l++) {
						String rat = ratings.get(l).getText();
						a1.add(rat);
						break;
					}

					a1.add(dishname.get(j).getText());
					a1.add(dishprices.get(j).getText());
					hlist.add(a1);
				}
		   
				for (ArrayList arrayList : hlist) {
					System.out.println(arrayList);				
				}
				


//				LinkedHashSet<String> category = new LinkedHashSet<String>();
//				List<WebElement> ManuNames = driver.findElements(By.xpath("//div[@class='_2dS-v']"));
//				for (int j = 1; j < ManuNames.size(); j++) {
//					List<WebElement> cat = driver.findElements(By.xpath("//div[@class='_2dS-v']/h2"));
//					for (int k2 = 1; k2 < cat.size(); k2++) {
//						category.add(cat.get(k2).getText());
//					List<WebElement> dish = driver.findElements(By.xpath("//div[@id='h-619397792']/div/div[@class='_2wg_t']/div/div/div/div[@class='styles_itemName__hLfgz']"));
//					for (int l = 0; l < dish.size(); l++) {
//						category.add(dish.get(l).getText());
//					}
//					}
//				}
//				list.add(category);
				
//				for (int j = 1; j < dishcategory.size(); j++) {
//					LinkedHashSet<String> category = new LinkedHashSet<String>();
//					category.add(dishcategory.get(j).getText());
//					dishcategory.get(j).click();
//					List<WebElement> id = driver.findElements(By.xpath("//div[@class='_2dS-v']"));
//					innerloop:
//					for (int h1 = 0; h1 < id.size(); h1++) {
//						String id1 = id.get(h1).getAttribute("id"); 
//						List<WebElement> dish = driver.findElements(By.xpath("//div[@id='"+id1+"'and@class='_2dS-v']/div/div/div/div/div/div[@class='styles_itemName__hLfgz']"));
//						for (int l = 0; l < dish.size(); l++) {
//								category.add(dish.get(l).getText());
//						}
//
//						list.add(category);
//						break innerloop;
//					}
				
//				}
			
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
