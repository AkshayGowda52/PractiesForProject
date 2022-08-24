package PracticesForProject;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileViewTesting {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
//		DevTools devtool = driver.getDevTools();
//		devtool.createSession();
//		DevTools dTool= driver.maybeGetDevTools()
		
		Map<String, Object> device = new HashMap<String, Object>();
		device.put("width", 418);
		device.put("height", 896);
		device.put("deviceScaleFactor", 50);
		device.put("mobile", true);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", device);
		driver.get("https://www.selenium.dev/downloads/");
	}

}
