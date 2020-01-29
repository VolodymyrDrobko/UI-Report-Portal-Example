package core;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Optional;

public class TestListener implements ITestListener {
    public static ExtentHtmlReportManager reportLogger;
    static String browserName;

    @Override
    public void onStart(ITestContext iTestContext) {
        browserName = Optional.ofNullable(Configuration.BROWSER).orElse(iTestContext.getCurrentXmlTest().getParameter("browserName"));
        String testName = iTestContext.getCurrentXmlTest().getName();

        reportLogger = new ExtentHtmlReportManager().setUpExtentHtmlReport(testName, browserName);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String testCaseName = iTestResult.getMethod().getMethodName();
        reportLogger.createExtentHtmlReport(testCaseName);
        System.out.println("Test case is started - " + testCaseName);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        reportLogger.log(Status.PASS, iTestResult.getMethod().getMethodName());
        System.out.println(iTestResult.getMethod().getMethodName() + " - PASSED");
        reportLogger.flushReport();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        reportLogger.log(Status.FAIL, iTestResult.getMethod().getMethodName());
        System.out.println(iTestResult.getMethod().getMethodName() + " >>>>> FAILED");
        reportLogger.flushReport();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        reportLogger.log(Status.SKIP, iTestResult.getMethod().getMethodName());
        System.out.println(iTestResult.getMethod().getMethodName() + " >>>>> SKIPPED");
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
