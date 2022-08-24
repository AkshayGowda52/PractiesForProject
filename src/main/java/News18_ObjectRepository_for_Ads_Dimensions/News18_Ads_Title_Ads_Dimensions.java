package News18_ObjectRepository_for_Ads_Dimensions;

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

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.mysql.cj.jdbc.Driver;

public class News18_Ads_Title_Ads_Dimensions {

	private static final int Empty = 0;
	@FindBy(xpath = "//div[@class='nw18-dfp-ad  ' and contains(@data-adcode,'/1039154/NW18_ENG_Desktop/NW18_ENG_Home/NW18_ENG_Home_Home/NW18_ENG_HOM_')]")
	private List<WebElement> AdsBlocks;

	@FindBy(xpath = "//div[@class='nw18-dfp-ad  ' and contains(@data-adcode,'/1039154/NW18_ENG_Desktop/NW18_ENG_Home/NW18_ENG_Home_Home/NW18_ENG_HOM_')]/descendant::iframe")
	private List<WebElement> OnlyAdsBlocks;

	public News18_Ads_Title_Ads_Dimensions(WebDriver driver)
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
	Connection con;

	LinkedList<String> list;
	LinkedHashSet<String> set;
	ArrayList<String> arr;

	LinkedList list1;

	ArrayList<String> arrheight;
	ArrayList<String> arrwidth;


	Dimension dimension;
	//	String[] dimensionads;
	int height;
	int width;
	String height1 = null;
	String width1 = null;


	public void ClickOnadsblocks(WebDriver driver) throws Throwable
	{
		list = new LinkedList<String>();

		Dbdriver = new Driver();	
		DriverManager.registerDriver(Dbdriver);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/news18", "root", "root");
		state = con.createStatement();

		for (int i = 0; i < AdsBlocks.size(); i++) {

			set = new LinkedHashSet<String>();

			arrheight = new ArrayList<String>();
			arrwidth = new ArrayList<String>();

			JavascriptExecutor js = (JavascriptExecutor)driver;
			Thread.sleep(6000);
			js.executeScript("arguments[0].scrollIntoView();", AdsBlocks.get(i));

			String ParentWindow = driver.getWindowHandle();
			if (AdsBlocks.get(i).isDisplayed()) {
				AdsBlocks.get(i).click();

				dimension = AdsBlocks.get(i).getSize();
				height = dimension.getHeight();
				width = dimension.getWidth();
				height1 = String.valueOf(height);
				width1 = String.valueOf(width);

				System.out.println(height1+" "+width1);
//				arrheight.add(height1);
//				arrwidth.add(width1);
//
//				System.out.println(arrheight+" "+arrwidth);

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
						driver.close();
					}

				}	

				if(!(title==null)) {

					list.add(title+" "+height1+" "+width1);
					//					list.add(height1);
					//					list.add(width1);
					//					list.add(title);
					System.out.println(title);	
				}

//				int result = state.executeUpdate("insert into adstitleanddimensions(adstitle_height_width)values('"+title+"    "+height1+"    "+width1+"')");
//				if(result==1) {
//					System.out.println("data enetred into database");
//				}else {
//					System.out.println("data is not entred");
//				}
				driver.switchTo().window(ParentWindow);
			}
		}
		System.out.println("ad title list"+" "+list);

	}

	String dbheight1;
	String dbwidth1;
	String dbadstitle1;
	public void databases(WebDriver driver) throws Throwable
	{
		Dbdriver = new Driver();	
		DriverManager.registerDriver(Dbdriver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/news18", "root", "root");
		state = con.createStatement();

		arr = new ArrayList<String>();

		ResultSet result = state.executeQuery("select * from adstitleanddimensions;");
		while(result.next())
		{

			String adstitleanddimensions = result.getString(2);
			arr.add(adstitleanddimensions);
		
		}

		System.out.println("Checking Db list value:"+arr);
		//				org.testng.Assert.assertEquals(arr, list);
		//				if(arr.equals(list))
		//				{
		//					System.out.println("data is verified");
		//				}
		//				else
		//				{
		//					System.out.println("data is not verified");
		//				}

	}

	public void comperingDb_values(WebDriver driver) throws SQLException
	{

		try {
			for (int i = 0; i < arr.size(); i++) {

				for (int j = 0; j < list.size(); j++) {

					
					if(!(arr).contains(list.get(j).toString()))
					{

						SoftAssert sa = new SoftAssert();
						sa.assertEquals(arr.get(i).toString(), list.get(j).toString());
						System.out.println("soft assert pass"+" "+arr.get(i).toString()+" "+list.get(j).toString());
						System.out.println("values are not duplicate");
						state.executeUpdate("insert into adstitleanddimensions(adstitle_height_width)values('"+list.get(j)+"')");
						String localS= list.get(j);
						arr.add(localS);

					}
					else {
						System.out.println("soft assert fail"+" "+arr.get(i).toString()+" "+list.get(j).toString());
					}
				}

			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}

}
