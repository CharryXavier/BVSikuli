package testcase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class DemoTest_Calc {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		
		DesktopOptions option=new DesktopOptions();
		
		option.setApplicationPath("C:\\Windows\\System32\\calc.exe");
		WiniumDriver driver=new WiniumDriver(new URL("http://localhost:9999"),option);
		Thread.sleep(5000);
		
	
		driver.findElement(By.name("Siete")).click();
		
		driver.findElement(By.id("multiplyButton")).click();
		
		driver.findElement(By.name("Cuatro")).click();
		
		driver.findElement(By.name("Es igual a")).click();
		Thread.sleep(5000);
		
		String output=driver.findElement(By.id("CalculatorResults")).getAttribute("Name");
		System.out.print("El resultado es: "+output);
		
	}

}
