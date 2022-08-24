package com.crm.PRACTICE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class flipkart_print_all_mobilename_and_price {

	public static void main(String[] args) throws Throwable
	{

		WebDriverManager.firefoxdriver().setup();
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//button[.='✕']")).click();
		driver.findElement(By.xpath("//input[@class='_3704LK']")).sendKeys("mobiles",Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	//	driver.findElement(By.xpath("//*[name()='svg' and @xmlns='http://www.w3.org/2000/svg'  and @width='20']")).click();
		List<WebElement> all_mobile = driver.findElements(By.xpath("//div[@class='_1AtVbE col-12-12']//div[@class='_4rR01T']"));
		List<WebElement> prices = driver.findElements(By.xpath("//div[@class='_1AtVbE col-12-12']//div[@class='_30jeq3 _1_WHN1']"));
		
		List<ArrayList> mobile_list = new ArrayList<ArrayList>();
		
		for(int i=0;i<all_mobile.size();i++)
		{
			ArrayList<String> a = new ArrayList<String>();
			
			a.add(all_mobile.get(i).getText());
			a.add(prices.get(i).getText());
			mobile_list.add(a);
		}
		
		for (ArrayList arrayList : mobile_list) 
		{
			System.out.println(arrayList);
		}
		
		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\Test data1.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		org.apache.poi.ss.usermodel.Sheet s = wb.getSheet("Sheet1");
		for(int i = 0; i<all_mobile.size(); i++)
		{
			Row r = s.createRow(i);
			for(int j = 0; j<mobile_list.get(i).size();j++)
			{
				r.createCell(j).setCellValue(mobile_list.get(i).get(j).toString());
			}
			FileOutputStream fout = new FileOutputStream(".\\src\\test\\resources\\Test data1.xlsx");
			wb.write(fout);
		}
		driver.close();

		
	

	}

}
