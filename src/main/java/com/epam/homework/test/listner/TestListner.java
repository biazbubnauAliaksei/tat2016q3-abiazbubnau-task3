package com.epam.homework.test.listner;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListner extends TestListenerAdapter {
    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
    }
}
