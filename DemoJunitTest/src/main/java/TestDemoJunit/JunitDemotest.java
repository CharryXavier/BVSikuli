package TestDemoJunit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.sikuli.script.Screen;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class JunitDemotest {

	
	   /* Varialbes declaradas antes de cada metodo.*/	
	 static ExtentTest logger;
	 static ExtentHtmlReporter htmlReporter;
	 static ExtentReports extent;
	 private static ExtentReports report;
	 static ExtentTest test;
	 public static WiniumDriverService service = null;
	 public static DesktopOptions options = null;
	private WebDriver driver;  

	    /* Antes de iniciar la prueba este abrira el servicio de winium de forma automatica,
	     * y abrira la calculadora.*/	  
	     
		@Before
	    public  void main() throws IOException, InterruptedException {
			WiniumDriver driver = null;
	    	DesktopOptions options = null;
	    	WiniumDriverService service = null;

	    	options = new DesktopOptions();
	    	options.setApplicationPath("C:\\Windows\\System32\\calc.exe");

	    	File driverPath = new File("C:\\winium\\Winium.Desktop.Driver.exe");
	    	System.setProperty("webdriver.winium.desktop.driver", "C:\\winium\\Winium.Desktop.Driver.exe");    	
	    	service = new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
	    	try {
	    	service.start();
	    	driver = new WiniumDriver(service, options);
	    	} catch (IOException e) {
	    		System.out.println("Excepción al iniciar el servicio WINIUM");
		        e.printStackTrace();
	    	}
	    }
		
	
	public static Screen s = new Screen();

	//@Before
	public void startReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
        	// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Prueba de Escritorio", "Calculadora Windwos");
        	extent.setSystemInfo("Ambiente de Pruebas", "UAT");
		extent.setSystemInfo("Jrcharrry", "");
		htmlReporter.config().setDocumentTitle("Prueba Calculadora Charry"); 
                // Name of the report
		htmlReporter.config().setReportName("Javier Ricardo Charry "); 
                // Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD);			
	}
	
	//Este metodo captura los screenshot y genera la ubicacción de cada screenshot.
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	
	/* Este es el metodo de las acciones de la prueba en la calculadora con SIKULI Y WINIUM.*/
	
	@Test
	public void test() throws Exception{

		
		DesktopOptions option=new DesktopOptions();
		
		option.setApplicationPath("C:\\Windows\\System32\\calc.exe");
		WiniumDriver driver=new WiniumDriver(new URL("http://localhost:9999"),option);
		Thread.sleep(5000);
		
	
       s.click("C:\\SikuliX\\Scripts\\SikuliCalcJava\\Ocho", 0);
       s.click("C:\\SikuliX\\Scripts\\SikuliCalcJava\\Suma", 0);
       s.click("C:\\SikuliX\\Scripts\\SikuliCalcJava\\Uno", 0);
       s.click("C:\\SikuliX\\Scripts\\SikuliCalcJava\\Igual", 0);  	
	   driver.findElement(By.name("Siete")).click();		
	   driver.findElement(By.id("multiplyButton")).click();
	   driver.findElement(By.name("Cuatro")).click();	
	   driver.findElement(By.name("Es igual a")).click();
		Thread.sleep(5000);
		
		String output=driver.findElement(By.id("CalculatorResults")).getAttribute("Name");
		System.out.println("El resultado es: " +output);
		
		}
		
	
	//@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method. 
		//	String Scrnshot = TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
			String screenshotPath = getScreenShot(driver, result.getName());
			//To add it in the extent report 
			logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
		}
		else if(result.getStatus() == ITestResult.SKIP){
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
		//driver.quit();
	}

	//@After
	public void endReport() {
		extent.flush();
	}
}
