package com.epam.homework.framework.appender;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class TestNGReportAppender extends AppenderSkeleton {

    @Override
    protected void append(LoggingEvent loggingEvent) {
        Reporter.log(layout.format(loggingEvent));
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
