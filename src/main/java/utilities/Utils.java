package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    private static WebDriver driver;

    /**
     * This method is used to launch the browser based on browserName
     * @param browserName
     * @return WebDriver
     * @throws Exception
     * @autor Lakshman
     */
    public static WebDriver launchBrowser(String browserName) throws Exception{

        if(browserName.equalsIgnoreCase("Chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized"); //To Maximize the screen
            options.addArguments("--incognito"); // To open the browser in Incognito mode
            options.addArguments("--disable-popup-blocking");//To Disable the pop-up blocker
            options.addArguments("--ignore-certificate-errors");//To Ignore the certificate related errors
            options.setAcceptInsecureCerts(true);// To accept the websites which are not having security certificate
            driver = new ChromeDriver(options);
        }else if(browserName.equalsIgnoreCase("Firefox")){
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("Edge")){
            driver = new EdgeDriver();
        }else {
            throw new Exception("Invalid browserName is provided");
        }
        System.out.println(browserName+" browser is launched");
        driver.manage().window().maximize();
        System.out.println(browserName+" is maximized");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("WebDriver implicitly timeout configured with 20 seconds of time");
        return driver;
    }

    public static String createScreenshotsFolder(String path){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
        String timestamp = dateFormat.format(date);
        String screenshotPath = path+"\\screenshots\\"+timestamp;
        new File(screenshotPath).mkdir();
        System.out.println("Screenshots folder is created with the timestamp :"+timestamp);
        return screenshotPath;
    }

    public static void captureScreenshot(String timestamp, String screenshotName) throws Exception{
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String screenshotPath = timestamp+"\\"+screenshotName+".jpg";
        File dest = new File(screenshotPath);
        FileUtils.copyFile(src, dest);
        System.out.println(screenshotName+ " Screenshot is captured");
    }
}
