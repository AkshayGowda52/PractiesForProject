package com.crm.PRACTICE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {

	public static void main(String[] args) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
    ChromeDriver driver = new ChromeDriver();
    Thread.sleep(1000);
    driver.get("https://www.flipkart.com/");
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@class='InyRMC _3Il5oO']")).click();
    Thread.sleep(1000);
    WebElement e1 = driver.findElement(By.xpath("//div[text()='Electronics']"));
    
	}

}
