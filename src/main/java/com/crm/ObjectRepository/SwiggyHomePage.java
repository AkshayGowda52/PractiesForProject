package com.crm.ObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwiggyHomePage 
{
  //Step 1:declaration
	@FindBy(xpath = "//div[@class='_129-b']/div[@class='MZy1T']/div/a/div/div/div/div[@class='nA6kb']")
	private static List<WebElement> hotels;
	
	@FindBy(xpath = "//div[@class='_2OmLw']/a")
	private static List<WebElement> page;

	//utilization
	public static List<WebElement> getHotels() {
		return hotels;
	}
	public static List<WebElement> getPage() {
		return page;
	}
	//Step 2: initialization
	public SwiggyHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

}
