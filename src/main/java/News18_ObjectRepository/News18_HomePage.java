package News18_ObjectRepository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.google.protobuf.Empty;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.ResultsetRows;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class News18_HomePage {

	private static final int Empty = 0;
	@FindBy(xpath = "//div[@class='nw18-dfp-ad  ' and contains(@data-adcode,'/1039154/NW18_ENG_Desktop/NW18_ENG_Home/NW18_ENG_Home_Home/NW18_ENG_HOM_')]")
	private List<WebElement> AdsBlocks;

	@FindBy(xpath = "//div[@class='nw18-dfp-ad  ' and contains(@data-adcode,'/1039154/NW18_ENG_Desktop/NW18_ENG_Home/NW18_ENG_Home_Home/NW18_ENG_HOM_')]/descendant::iframe")
	private List<WebElement> OnlyAdsBlocks;

	public News18_HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public List<WebElement> getiFrameIds() {
		return AdsBlocks;
	}


	public static int getEmpty() {
		return Empty;
	}

	public List<WebElement> getOnlyAdsBlocks() {
		return OnlyAdsBlocks;
	}
	
	Driver Dbdriver;
	String title = null;
	Statement state = null;

	LinkedList<String> list;
	LinkedHashSet<String> set;
	ArrayList<String> arr;
	ResultSet result;



	public void ClickOnadsblocks(WebDriver driver) throws Throwable
	{
		list = new LinkedList<String>();
		Dbdriver = new Driver();	
		DriverManager.registerDriver(Dbdriver);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/news18", "root", "root");
		state = con.createStatement();

		//		FileInputStream file = new FileInputStream("./src/test/resources/UserNames.xlsx");
		//		Workbook wb = WorkbookFactory.create(file);
		//		Sheet sh = wb.createSheet("Sheet4");


		for (int i = 0; i < AdsBlocks.size(); i++) {

			set = new LinkedHashSet<String>();
			JavascriptExecutor js = (JavascriptExecutor)driver;
			Thread.sleep(6000);
			js.executeScript("arguments[0].scrollIntoView();", AdsBlocks.get(i));

			String ParentWindow = driver.getWindowHandle();
			if (AdsBlocks.get(i).isDisplayed()) {
				AdsBlocks.get(i).click();

				Dimension point = AdsBlocks.get(i).getSize();
				System.out.println(point);

				Set<String> childWindow = driver.getWindowHandles();
				Iterator<String> it = childWindow.iterator();

				Thread.sleep(2000);
				while (it.hasNext()) {
					String windows = it.next();
					if(!ParentWindow.equalsIgnoreCase(windows))
					{
						Thread.sleep(2000);
						driver.switchTo().window(windows);
						title = driver.getTitle();
						//						sh.createRow(i).createCell(0).setCellValue(title);
						driver.close();
					}

				}	
				if(!(title==null)) {
					list.add(title);
					System.out.println(title);	
				}
				//				for (int j = 0; j < list.size(); j++) {
				//					state.executeUpdate("insert into news18(AdsTitle) values('"+list.get(j)+"')");
				//				}

				driver.switchTo().window(ParentWindow);
			}

		}
		//		FileOutputStream file1 = new FileOutputStream("./src/test/resources/UserNames.xlsx");
		//		wb.write(file1);

	}

	Connection con;
	public void databases(WebDriver driver) throws Throwable
	{
		Dbdriver = new Driver();	
		DriverManager.registerDriver(Dbdriver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/news18", "root", "root");
		state = con.createStatement();

		arr = new ArrayList<String>();

		result = state.executeQuery("select AdsTitle from news18;");
		while(result.next())
		{
			String davalue = result.getString("AdsTitle");
			arr.add(davalue);
		}

		//		System.out.println("Checking Db list value:"+arr);

	}


	public void comperingDBvalue_ads(WebDriver driver)
	{
		try {
			for (int i = 0; i < arr.size(); i++) {

				for (int j = 0; j < AdsBlocks.size(); j++) {

					if(!(arr).contains(list.get(j).toString()))
					{
						state.executeUpdate("insert into news18(AdsTitle) values('"+list.get(j)+"')");
						System.out.println("Title send to database");
						String localS= list.get(j);
						arr.add(localS);
					}
					else
					{
						System.out.println("Title is duplicate");
					}

				}

			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void databasesclose(WebDriver driver) throws Throwable
	{
		con.close();
	}
}





