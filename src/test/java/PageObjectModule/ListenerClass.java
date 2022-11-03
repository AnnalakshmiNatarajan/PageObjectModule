package PageObjectModule;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

public class ListenerClass implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("Strt" + result.getStatus());

    }

    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getStatus());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println(result.getStatus());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getStatus());
    }
}
