package core;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext iTestContext) {
        String testName = iTestContext.getCurrentXmlTest().getName();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String testCaseName = iTestResult.getMethod().getMethodName();
        System.out.println("Test case is started - " + testCaseName);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(iTestResult.getMethod().getMethodName() + " - PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(iTestResult.getMethod().getMethodName() + " >>>>> FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println(iTestResult.getMethod().getMethodName() + " >>>>> SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
