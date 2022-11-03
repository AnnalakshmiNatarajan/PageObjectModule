package PageObjectModule;



import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;


public class SeleniumJavaPage {

    By searchInput = By.cssSelector("input[type='text'] ");

    By stats_label = By.cssSelector("div#result-stats");

    static By email = By.id("email");

    static By password = By.xpath("//input[@type=\"password\"]");

    static By login_btn = By.cssSelector("button[name='login']");

    static Actions actions;
    static By roleButton = By.xpath( "//*/div[2]/div/div[1]/div[3]/div/div[1]");

    static By logOut = By.linkText("Log Out");

    static By alertButton = By.id("alertbtn");

    static By file_upload = By.id("file-upload");

    static  By file_submit = By.xpath("//input[contains(id,file-submit) and @class=\"button\"]");

    static By drag_locator = By.xpath("//div[@id=\"column-a\"]/header");

    static By drop_locator = By.xpath("//div[@id=\"column-b\"]/header");

    public void setSearchInput(WebDriver webdriver_obj,String inputValue) throws InterruptedException {

        webdriver_obj.findElement(searchInput).sendKeys(inputValue, Keys.ENTER);
        Thread.sleep(1000);
    }



    public static void loginFaceBook(WebDriver webdriver_obj,String userValue,String passwordValue) throws InterruptedException {
        webdriver_obj.findElement(email).sendKeys(userValue);
        webdriver_obj.findElement(password).sendKeys(passwordValue);
        webdriver_obj.findElement(login_btn).click();
        FluentWait wait = new FluentWait(webdriver_obj).withTimeout(Duration.ofMinutes(1)).ignoring(NoSuchElementException.class);
        webdriver_obj.findElement(roleButton).click();
        Wait Wait = new WebDriverWait(webdriver_obj,Duration.ofMinutes(1));
        Wait.until(ExpectedConditions.elementToBeClickable(webdriver_obj.findElement(roleButton)));
        webdriver_obj.findElement(logOut).click();

    }


    public String setGoogleSearchInput(WebDriver webdriver_obj,String inputValue) throws InterruptedException {
        Thread.sleep(1000);
        webdriver_obj.findElement(searchInput).sendKeys(inputValue, Keys.ENTER);
        Thread.sleep(1000);
        String result_label = webdriver_obj.findElement(stats_label).getText();
        return result_label;
    }

    public void clickLoginButton(WebDriver webDriver_obj){
        webDriver_obj.findElement(login_btn).click();
    }

    public void takeSnapShot(WebDriver webDriver_obj, String destinationPath) throws IOException {
        File screenshot  = ((TakesScreenshot) webDriver_obj).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(destinationPath);
        FileUtils.copyFile(screenshot,destinationFile);
    }

    public void doFileUpload(WebDriver webDriver,String path) throws IOException {
        WebElement file_element =webDriver.findElement(file_upload);
        file_element.sendKeys(path);
        webDriver.findElement(file_submit).submit();

    }

    public void dragAndDrop(WebDriver webDriver) throws IOException {
        actions = new Actions(webDriver);
        WebElement drag = webDriver.findElement(drag_locator);
        WebElement drop =  webDriver.findElement(drop_locator);
/*        int x_location = drop.getLocation().x;
        int y_location = drop.getLocation().y;*/
       /* actions.moveToElement(drag)
                .pause(Duration.ofSeconds(1))
                .clickAndHold(drag)
                .pause(Duration.ofSeconds(1))
                .moveByOffset(x_location, y_location)
                .moveToElement(drop)
                .moveByOffset(x_location,y_location)
                .pause(Duration.ofSeconds(1))
                .release().build().perform();*/
        actions.dragAndDrop(drag,drop).build().perform();
        Thread.currentThread().getName();
        takeSnapShot(webDriver,"E://outpufile.png");
    }

    public void clickAlertButton(WebDriver webdriver_obj){
        webdriver_obj.findElement(alertButton).click();
        webdriver_obj.switchTo().alert().dismiss();
        webdriver_obj.findElement(alertButton).sendKeys(Keys.F5);
    }
    public String getBorder(WebDriver webDriver_obj) throws InterruptedException {
        Thread.sleep(1000);
        return webDriver_obj.findElement(email).getCssValue("border");
    }
}
