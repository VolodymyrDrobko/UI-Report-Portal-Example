package core;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ReportManager {
    static ExtentHtmlReport reportLogger = new ExtentHtmlReport();
    public static ThreadLocal<ExtentTest> reportLoggerPool = new ThreadLocal<>();

    public static void setUpReport(String testName, String browserName) {
        reportLogger.setUpExtentHtmlReport(testName, browserName);
    }

    public static void createReport(String testCaseName) {
        setReport(reportLogger.createExtentHtmlReport(testCaseName));
        log(Status.INFO,"Test started - " + testCaseName);
    }

    public static void flushReport() {
        reportLogger.flushReport();
    }

    public static void log(Object status, String message) {
        reportLogger.log(getReportLogger(), (Status)status, message);
    }

    public static ExtentTest getReportLogger() {
        return reportLoggerPool.get();
    }

    public static void setReport(ExtentTest reportLogger) {
        reportLoggerPool.set(reportLogger);
    }
}
