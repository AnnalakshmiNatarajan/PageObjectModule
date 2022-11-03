package PageObjectModule;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTestTwo {
    WebDriver driver;

    @Test
    public void doTestThird() throws InterruptedException {
        SeleniumJavaPage seleniumJavaPage = new SeleniumJavaPage();
        seleniumJavaPage.setGoogleSearchInput(driver,"Annalakshmi");
    }
}
