package com.epam.homework.test.listener;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onStart(ITestContext testContext) {
        System.out.println("Start test");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        System.out.println("Finish test");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        System.out.println(tr.getName() + " is success.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println(tr.getTestName() + " is failed. ");
    }
}
