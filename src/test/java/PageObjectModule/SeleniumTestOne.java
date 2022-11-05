package PageObjectModule;

import PageObjectModule.SeleniumJavaPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;
@Test(groups="all")
public class SeleniumTestOne {
    WebDriver webdriver_obj;
    SeleniumJavaPage seleniumJavaPage = new SeleniumJavaPage();

    @Parameters("browser")
    @BeforeClass
    public void beforeTest(@Optional("chrome") String browserName){
        switch(browserName.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webdriver_obj = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                webdriver_obj = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webdriver_obj = new FirefoxDriver();
                break;
            default:
                System.err.println("Input Valid Browser");
                break;
        }


    }

    @Test(priority = 1,groups = {"google","facebook"})
    public void doTestFirst() throws InterruptedException {

        webdriver_obj.get("https://www.youtube.com/");
        seleniumJavaPage.setSearchInput(webdriver_obj,"ANNALAKSHMI");
    }


    @Test(priority = 2,groups = {"facebook,google"})
    public void doTestSecond() throws InterruptedException {
        webdriver_obj.navigate().to("https://www.google.com/");
        String resultLabel = seleniumJavaPage.setGoogleSearchInput(webdriver_obj,"Annalakshmi");
        assertTrue(resultLabel.contains("results"),"Not Matched");
        System.out.println("Stop");
    }

    @Test(priority = 2,groups = {"facebook"})
    public void doTestFacebook()  throws InterruptedException {
        webdriver_obj.navigate().to("https://www.facebook.com/");
        String facebookTitle = webdriver_obj.getTitle();
        seleniumJavaPage.clickLoginButton(webdriver_obj);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Log in to Facebook",facebookTitle,"Title NotMatched");
        String borderColour = seleniumJavaPage.getBorder(webdriver_obj);
        softAssert.assertEquals("1px solid rgb(240, 40, 73)",borderColour,"No Error Thrown");
        softAssert.assertAll();
    }

    @AfterClass
    public void afterTest(){
        webdriver_obj.quit();
    }
}
