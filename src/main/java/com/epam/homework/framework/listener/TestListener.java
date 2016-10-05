package com.epam.homework.framework.listener;

import com.epam.homework.framework.logging.Log;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestStart(ITestResult result) {
        Log.info(result.getTestClass().getName() + " starting...");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info(result.getTestClass().getName() + " SKIPPED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info(result.getTestClass().getName() + " PASSED successful");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error(result.getName() + " FAILED!", new Throwable(result.getThrowable().getMessage()));
    }
}
