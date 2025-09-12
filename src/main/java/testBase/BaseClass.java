package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;  
    public Properties p;
    
    @BeforeClass(groups= {"Sanity","Master","DataDriven","Regression"})
    @Parameters({"os","browser"})
    public void setup(@Optional("Windows") String os, 
                      @Optional("chrome") String br) throws IOException {
        
        FileInputStream file=new FileInputStream("./src/main/resources/config.properties"); 
        p=new Properties();
        p.load(file);
        logger = LogManager.getLogger(this.getClass());
        
        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			{
	
				DesiredCapabilities cap=new DesiredCapabilities();
				//cap.setPlatform(Platform.WIN11);
				//cap.setBrowserName("chrome");
				
				//os
				if(os.equalsIgnoreCase("windows"))
				{
					cap.setPlatform(Platform.WIN11);
				}
				else if(os.equalsIgnoreCase("linux"))
				{
					cap.setPlatform(Platform.LINUX);
				}
				else
				{
					System.out.println("no matching os");
					return;
					
				}
				//browser
				
				switch (br.toLowerCase()) {
				case "chrome":cap.setBrowserName("chrome");break;
				case "edge":cap.setBrowserName("MicrosoftEdge")	;break;
				case "firefox": cap.setBrowserName("firefox"); break;
				default:System.out.println("no matching browser");return;
				}
				driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap); 
				
			}

        
        if(p.getProperty("execution_env").equalsIgnoreCase("local"))
        {
        // Launch browser
        switch(br.toLowerCase()) {
            case "chrome": driver = new ChromeDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            default: 
                System.out.println("Invalid browser! Launching Chrome as default.");
                driver = new ChromeDriver();
        }
        }
        
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appUrl"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups= {"Sanity","Master","DataDriven","Regression"})
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphanumeric() {
        return RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomNumeric(10);
    }
    public String captureScreen(String tname) throws IOException
    {
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH,mm,ss").format(new Date());
    	
    	TakesScreenshot takecreenshot=(TakesScreenshot) driver;
    	File sourceFile=takecreenshot.getScreenshotAs(OutputType.FILE);
    	
    	String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tname+"_"+timeStamp+".png";
    	File targetFile=new File(targetFilePath);
    	sourceFile.renameTo(targetFile);
		return targetFilePath;
    	
    }
}
