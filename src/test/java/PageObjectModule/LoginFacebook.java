package PageObjectModule;

import PageObjectModule.SeleniumJavaPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Listeners(ListenerClass.class)
public class LoginFacebook extends SeleniumJavaPage {

    static WebDriver webDriver;

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
    // headlessmode    webDriver = new HtmlUnitDriver();
        //head mode
        webDriver = new ChromeDriver();
    }

    @Test(dataProvider = "credential" , dataProviderClass = GetDataProvider.class)
    public void doLogin(String userName, String password) throws Exception {
        webDriver.navigate().to("https://www.facebook.com/");
        webDriver.manage().deleteAllCookies();
        loginFaceBook(webDriver,userName,password);
        takeSnapShot(webDriver,"E://outputFile2.jpg");
    }


    @Test
    public void loginPopUp() throws Exception {
        String userName = "admin";
        String password = "admin";
        String URL = "the-internet.herokuapp.com/basic_auth";
        webDriver.get("https://"+userName+":"+password + "@" + URL );
        takeSnapShot(webDriver,"E://outpufile.png");
    }

    @Test
    public void checkFileUpload() throws IOException {
        webDriver.get("https://the-internet.herokuapp.com/upload");
        Dimension minimum_resolution = new Dimension(1024,480);
        webDriver.manage().window().setSize(minimum_resolution);
        doFileUpload(webDriver,"E://outpufile.png");
    }

    public static void main(String[] args) throws IOException {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("report.html");
        extentSparkReporter.loadJSONConfig(new File("./src/test/resources/extent-report.json"));
        extentReports.attachReporter(extentSparkReporter);
        extentReports.createTest("Test 12").pass(MarkupHelper.createLabel("Passed", ExtentColor.AMBER));
        extentReports.createTest("Skip").skip("<b><i>Successfully Skipped.</i></b>");
        String xmlFile ="<menu id=\"file\" value=\"File\">\n" +
                "  <popup>\n" +",,"+
                "    <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\n" +
                "    <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\n" +
                "    <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\n" +
                "  </popup>\n" +
                "</menu>";
        Map<String, Integer> dataMap = new HashMap();
        dataMap.put("Annalakshmi",1);
        dataMap.put("Lakshmi Narayanan",2);
        dataMap.put("Yazhini",3);
        try{
            int j = 7/0;
        }catch(Exception exception){
            extentReports.createTest("Exception").fail(exception);
        }
        extentReports.createTest("Map Values").skip(MarkupHelper.createOrderedList(dataMap.keySet()));
        extentReports.createTest("XML Reports").pass(MarkupHelper.createCodeBlock(xmlFile,CodeLanguage.XML));
        String jsonFile = "{\"widget\": {\n" +
                "    \"debug\": \"on\",\n" +
                "    \"window\": {\n" +
                "        \"title\": \"Sample Konfabulator Widget\",\n" +
                "        \"name\": \"main_window\",\n" +
                "        \"width\": 500,\n" +
                "        \"height\": 500\n" +
                "    },\n" +
                "    \"image\": { \n" +
                "        \"src\": \"Images/Sun.png\",\n" +
                "        \"name\": \"sun1\",\n" +
                "        \"hOffset\": 250,\n" +
                "        \"vOffset\": 250,\n" +
                "        \"alignment\": \"center\"\n" +
                "    },\n" +
                "    \"text\": {\n" +
                "        \"data\": \"Click Here\",\n" +
                "        \"size\": 36,\n" +
                "        \"style\": \"bold\",\n" +
                "        \"name\": \"text1\",\n" +
                "        \"hOffset\": 250,\n" +
                "        \"vOffset\": 100,\n" +
                "        \"alignment\": \"center\",\n" +
                "        \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\"\n" +
                "    }\n" +
                "}}  ";
        extentReports.createTest("JSON Sample").pass(MarkupHelper.createCodeBlock(jsonFile, CodeLanguage.JSON));
        extentReports.flush();
        Desktop.getDesktop().browse(new File("report.html").toURI());
    }
/*
 * timeout example and drag and drop
 */
    @Test(timeOut = 1000)
    public void doDragAndDrop() throws IOException {
        webDriver.get("https://the-internet.herokuapp.com/drag_and_drop");
        dragAndDrop(webDriver);
    }

    @Test(priority = 1)
    public void automateThePractice(){
        webDriver.get("https://rahulshettyacademy.com/AutomationPractice/");
        clickAlertButton(webDriver);
    }

    @AfterTest
    public void afterTest(){
        webDriver.quit();
    }
}
