package PageObjectModule;

import org.testng.ITestListener;
import org.testng.ITestResult;

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
