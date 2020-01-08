package core;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Optional;

import static utils.Constants.DEFAULT_BROWSER;

public class TestListener implements ITestListener {
    private String testName = "";
    public static String testCaseName = "";
    private ThreadLocal<ExtentHtmlReport> reportsThread = new ThreadLocal<>();
    public static ExtentHtmlReport reportLogger;
    public ThreadLocal<String> browserThread = new ThreadLocal<String>();
    public static String browserName = "";

    @Override
    public void onStart(ITestContext iTestContext) {
        browserThread.set(Configuration.BROWSER);
        browserName = Optional.ofNullable(browserThread.get()).orElse(DEFAULT_BROWSER);
        testName = iTestContext.getCurrentXmlTest().getName();
        reportsThread.set(new ExtentHtmlReport().setUpExtentHtmlReport(testName, BaseTest.browserName));
        reportLogger = reportsThread.get();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        testCaseName = iTestResult.getName();
        reportLogger.startTest(testCaseName);
        System.out.println("***** Test started ***** " + testCaseName);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        reportLogger.log(Status.PASS, testCaseName + " - PASSED");
        reportLogger.flushReport();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        reportLogger.log(Status.FAIL, testCaseName + " - FAILED");
        reportLogger.flushReport();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        reportLogger.log(Status.SKIP, testCaseName + " - SKIPPED");
        System.out.println(testCaseName + " - SKIPPED");
        reportLogger.flushReport();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        reportLogger.flushReport();
    }
}
