package PageObjectModule.PageObjectModule;

import PageObjectModule.SeleniumJavaPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends SeleniumJavaPage
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {

    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    static WebDriver webDriver;

    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.google.co.in/");
        String path = takeScreenShot();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("outputfile.html");
        ExtentSparkReporterConfig extentSparkConfig = extentSparkReporter.config();
        extentSparkConfig.setTheme(Theme.DARK);
        extentSparkConfig.setReportName("Launch GooGle");
        extentSparkConfig.setDocumentTitle("GooGle Test");
        extentSparkConfig.setCss(".badge-primary {background-color: red;}");
        extentSparkConfig.setTimeStampFormat("YYYY-MM-DD HH:MM:SS");
        extentSparkConfig.setJs("document.getElementsByClassName('search-icon fa fa-search')[0].click()\n");
        ExtentReports extentReports = new ExtentReports();
        extentSparkReporter.viewConfigurer().viewOrder().as(new ViewName[]{ ViewName.AUTHOR,ViewName.TEST, ViewName.EXCEPTION}).apply();
        extentSparkReporter.filter().statusFilter().as(new Status[]{Status.FAIL,Status.INFO}).apply();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.createTest("Google Launch", "Successfully launched")
                .info(MarkupHelper.createLabel("Launched", ExtentColor.BLUE))
                .addScreenCaptureFromPath(path,"1").addScreenCaptureFromPath(path,"2")
                .fail(MediaEntityBuilder.createScreenCaptureFromPath(path,"Log Level").build())
                .assignAuthor("Annalakshmi N")
                .assignCategory("Smoke 1")
                .assignDevice("Chrome 99");
        extentReports.setSystemInfo("OS",System.getProperty("os.name"));
        Capabilities capabilities = ((RemoteWebDriver)webDriver).getCapabilities();
        extentReports.setSystemInfo("Browser Detail",capabilities.getBrowserName()+" V "+capabilities.getBrowserVersion());
        extentReports.setSystemInfo("Domain Name","user.name");
        extentReports.flush();
        webDriver.quit();
        Desktop.getDesktop().browse(new File("outputfile.html").toURI());
    }

    public static String takeScreenShot() throws IOException {
        File outputFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("C://Users//DELL//IdeaProjects//PageObjectModule//outputFile.png");
        FileUtils.copyFile(outputFile,destinationFile);
        System.out.println(destinationFile.getCanonicalPath());
        return destinationFile.getAbsolutePath();
    }
}
