package com.epam.homework.test.listner;

import com.epam.homework.framework.browser.Browser;
import org.testng.*;

public class TestListner implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test starting...");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test success.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test failure.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        onTestFailure(iTestResult);

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Tests are starting...");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("All tests are finished.");
        Browser.getBrowser().close();
    }
}
