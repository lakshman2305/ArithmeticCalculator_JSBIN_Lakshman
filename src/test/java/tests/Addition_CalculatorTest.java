package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pages.CalculatorPage;
import utilities.Utils;
import utilities.YamlUtils;

public class Addition_CalculatorTest {

    private static WebDriver driver;
    private static String screenshotPath;
    private static String yamlPath;
    private static CalculatorPage calculatorPage;

    @BeforeClass
    public void beforeClass() throws Exception{
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);
        screenshotPath = Utils.createScreenshotsFolder(path);
        yamlPath=path+"\\src\\main\\resources\\Config.yaml";

        String browserName= YamlUtils.getYamlData(yamlPath,"browser");
        driver= Utils.launchBrowser(browserName);
    }


    @Test
    public void testAddition() throws Exception{
        String expression="10+153";
        String url= YamlUtils.getYamlData(yamlPath,"url");
        driver.get(url);
        System.out.println(url+" is loaded");
        calculatorPage=new CalculatorPage(driver);
        calculatorPage= PageFactory.initElements(driver,CalculatorPage.class);
        calculatorPage.verifyTest(expression);
        Utils.captureScreenshot(screenshotPath,"testAddition");
        System.out.println("DemoWebShop Login Screenshot is captured for testAddition");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
