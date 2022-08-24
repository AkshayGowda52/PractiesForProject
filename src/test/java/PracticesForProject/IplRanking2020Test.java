package PracticesForProject;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IplRanking2020Test {

	public static void main(String[] args) throws Throwable 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("ipl ranking 2022",Keys.ENTER);
		driver.findElement(By.xpath("//span[@class='z1asCe QFl0Ff']/*[name()='svg' and@focusable='false']")).click();
		Thread.sleep(3000);
		
		Driver driver1 = new Driver();
		DriverManager.registerDriver(driver1);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ipl_rankings","root","root");
		Statement state = con.createStatement();
		
		
		int rows = driver.findElements(By.xpath("(//table[@class='Jzru1c'])[1]//tr[2]//td")).size();
		int coloums = 5;
		
		ArrayList<String> list1 =  new ArrayList<String>();
		ArrayList<String> list2 =  new ArrayList<String>();
		
		
//		/*Attribute fetch*/
//		WebElement att=driver.findElement(By.xpath("//div[@class='jXpA9e Ui5IUc']"));
//		String attValue=att.getAttribute("data-ved");
//		System.out.println(attValue);
		
		String teamName = null;
		String matches=null;
		String  wins=null;
		String losses=null;
		
		
		 String actual = null;
		 String expected = null;
		
	   
		for(int i=2;i<= 11; i++)
		{			 
			
			 teamName=driver.findElement(By.xpath("//table[@class='Jzru1c'][1]/tbody/tr["+i+"]/td[3]")).getText();
			
			 matches = driver.findElement(By.xpath("//table[@class='Jzru1c'][1]/tbody/tr["+i+"]/td[4]")).getText();
			 
			 wins = driver.findElement(By.xpath("//table[@class='Jzru1c'][1]/tbody/tr["+i+"]/td[5]")).getText();
			 
			losses = driver.findElement(By.xpath("//table[@class='Jzru1c'][1]/tbody/tr["+i+"]/td[6]")).getText();
			 
			System.err.println(teamName+"  "+matches+ "  "+wins+"  "+losses+" ");
			list1.add(teamName);
			list1.add(matches);
			list1.add(wins);
			list1.add(losses);
			 int result = state.executeUpdate("insert into ipl_rankings_2022 values("+(i+1)+",'"+teamName+"','"+matches+"','"+wins+"','"+losses+"')");
			if(result==1)
			{
				System.out.println("data is added into table");
			}else
			{
				System.out.println("data is not added into table");
			}
		
		}
		
		
		ResultSet results = state.executeQuery("select * from ipl_rankings_2022");
		while(results.next())
		{
			 String teamname1 = results.getString(2);
			 String matches1 = results.getString(3);
			 String wins1 = results.getString(4);
			 String losses1 = results.getString(5);
			 list2.add(teamname1);
			 list2.add(matches1);
			 list2.add(wins1);
			 list2.add(losses1);
			 
		}
		Assert.assertEquals(list1, list2);
		
		if(list1.equals(list2))
		{
			System.out.println("data is verified");
		}
		else {
			System.out.println("data is not verified");
		}
		
	
		
//		for (int i = 0; i < rows; i++) 
//		{
//			for (int j = 0; j < coloums; j++) 
//			{
//				
//				String teams = driver.findElement(By.xpath("(//table[@class='Jzru1c'])[1]//tr["+(i+2)+"]/td["+(j+3)+"]")).getText();
//				System.out.print(teams+" ");
//			}
//			System.out.println();
//		}
		driver.close();
	}

}
