package com.epam.homework.test.listener;

import com.epam.homework.framework.logging.Log;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestStart(ITestResult result) {
        Log.info(result.getName() + " starting...");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info(result.getName() + " skipped");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info(result.getName() + " passed successful");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error(result.getName() + " failed!", new Throwable(result.getThrowable().getMessage()));
    }
}
