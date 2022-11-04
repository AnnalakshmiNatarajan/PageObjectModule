package PageObjectModule;

import PageObjectModule.SeleniumJavaPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SeleniumTestTwo {
    WebDriver driver;

    @Test
    public void doTestThird() throws InterruptedException {
        SeleniumJavaPage seleniumJavaPage = new SeleniumJavaPage();
        seleniumJavaPage.setGoogleSearchInput(driver,"Annalakshmi");
    }
}
