package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.BaseClass;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.testng.Assert;

public class CalculatorPage extends BaseClass {

    WebDriver driver;
    public CalculatorPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(xpath="//button[@value='1']")
    WebElement btn_One;

    @FindBy(xpath="//button[@value='2']")
    WebElement btn_Two;

    @FindBy(xpath="//button[@value='3']")
    WebElement btn_Three;

    @FindBy(xpath="//button[@value='4']")
    WebElement btn_Four;

    @FindBy(xpath="//button[@value='5']")
    WebElement btn_Five;

    @FindBy(xpath="//button[@value='6']")
    WebElement btn_Six;

    @FindBy(xpath="//button[@value='7']")
    WebElement btn_Seven;

    @FindBy(xpath="//button[@value='8']")
    WebElement btn_Eight;

    @FindBy(xpath="//button[@value='9']")
    WebElement btn_Nine;

    @FindBy(xpath="//button[@value='0']")
    WebElement btn_Zero;

    @FindBy(xpath="//button[@value='DEL']")
    WebElement btn_Delete;

    @FindBy(xpath="//button[@value='=']")
    WebElement btn_Equals;

    @FindBy(xpath="//button[@value='+']")
    WebElement btn_Plus;

    @FindBy(xpath="//button[@value='-']")
    WebElement btn_Minus;

    @FindBy(xpath="//button[@value='×']")
    WebElement btn_Mult;

    @FindBy(xpath="//button[@value='/']")
    WebElement btn_Div;

    @FindBy(xpath="//div[@id='output']")
    WebElement element_Output;


    public String evaluateExpression(String expr){
        String result="";
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            Object obj = engine.eval(expr);
            result = obj.toString();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getJSBinCalculator(String exp){

        for(int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            String str = Character.toString(ch);
            performClick(str);
        }
        btn_Equals.click();
        System.out.println("Equals button is clicked");

        String actual = element_Output.getText();
        return actual;
    }

    public void performClick(String str){
        if(str.equalsIgnoreCase("1")){
            btn_One.click();
            System.out.println("Button 1 is clicked");
        }else if(str.equalsIgnoreCase("2")){
            btn_Two.click();
            System.out.println("Button 2 is clicked");
        }else if(str.equalsIgnoreCase("3")){
            btn_Three.click();
            System.out.println("Button 3 is clicked");
        }else if(str.equalsIgnoreCase("4")){
            btn_Four.click();
            System.out.println("Button 4 is clicked");
        }else if(str.equalsIgnoreCase("5")){
            btn_Five.click();
            System.out.println("Button 5 is clicked");
        }else if(str.equalsIgnoreCase("6")){
            btn_Six.click();
            System.out.println("Button 6 is clicked");
        }else if(str.equalsIgnoreCase("7")){
            btn_Seven.click();
            System.out.println("Button 7 is clicked");
        }else if(str.equalsIgnoreCase("8")){
            btn_Eight.click();
            System.out.println("Button 8 is clicked");
        }else if(str.equalsIgnoreCase("9")){
            btn_Nine.click();
            System.out.println("Button 9 is clicked");
        }else if(str.equalsIgnoreCase("0")){
            btn_Zero.click();
            System.out.println("Button 0 is clicked");
        }else if(str.equalsIgnoreCase("DEL")){
            btn_Delete.click();
            System.out.println("Button Deleted is clicked");
        }else if(str.equalsIgnoreCase("+")){
            btn_Plus.click();
            System.out.println("Button Plus is clicked");
        }else if(str.equalsIgnoreCase("-")){
            btn_Minus.click();
            System.out.println("Button Minus is clicked");
        }else if(str.equalsIgnoreCase("*")){
            btn_Mult.click();
            System.out.println("Button Mult is clicked");
        }else if(str.equalsIgnoreCase("/")){
            btn_Div.click();
            System.out.println("Button Div is clicked");
        }else if(str.equalsIgnoreCase("EQ")){
            btn_Equals.click();
            System.out.println("Button Equals is clicked");
        }
    }


    public void verifyTest(String expr){
        String expected=evaluateExpression(expr);
        System.out.println("Expected Result is "+expected);

        String actual = getJSBinCalculator(expr);
        System.out.println("Actual Result is "+actual);

        Assert.assertEquals(actual, expected,expr+" is evaluated and result is not correct");
        System.out.println(expr+" is evaluated and result is correct");
    }

    public void verifyDelete(String expr) throws Exception{
        String expected=expr.substring(0, expr.length()-1);
        System.out.println("Expected Result is "+expected);

        getJSBinCalculator(expr);

        btn_Delete.click();
        System.out.println("Delete button is clicked");

        btn_Equals.click();
        System.out.println("Equals button is clicked");

        Thread.sleep(2000);

        String actual = element_Output.getText();
        System.out.println("Actual Result is "+actual);

        Assert.assertEquals(actual, expected,expr+" is evaluated and result is not correct");
        System.out.println(expr+" is evaluated and result is correct");
    }

    public String checkInvalidExpression(String expr){
        char lastChar = expr.charAt(expr.length()-1);
        String str=" ";
        String last = Character.toString(lastChar);
        if(last.equals("+") || last.equals("-") || last.equals("*") || last.equals("/")){
            str="ERR";
        }
        return str;
    }

    public void verifyInvalidExpression(String expr){

        String expected = checkInvalidExpression(expr);
        System.out.println("Expected Result is "+expected);

        String actual = getJSBinCalculator(expr);
        System.out.println("Actual Result is "+actual);

        Assert.assertEquals(actual, actual,expr+" is evaluated and result is not correct");
        System.out.println(expr+" is evaluated and result is correct");
    }
}
