package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import utils.Utils;

import java.io.File;
import java.io.IOException;

public class ExtentHtmlReport {
    private String reportName, reportPathName;
    private static int testCaseCounter = 0;

    private ExtentHtmlReporter htmlReporter;
    private ExtentReports report;
    private ExtentTest reportLogger;

    public ExtentHtmlReport setUpExtentHtmlReport(String testName, String browserName) {
        reportName = "Report-" + testName + "-" + browserName + ".html";
        reportPathName = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + reportName;
        htmlReporter = new ExtentHtmlReporter(reportPathName);
        report = new ExtentReports();
        report.attachReporter(htmlReporter);
        return this;
    }

    public void log(Status logStatus, String message) {
        String date = Utils.getCurrentDate();
        String screenShotName = TestListener.testCaseName + date + ".png";
        String screenShotPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "Screenshots" + File.separator;
        String screenShotFileWithPath = screenShotPath + screenShotName;

        if (logStatus == Status.FAIL || logStatus == Status.FATAL) {
            ScreenShotManager.takeScreenshot(BaseTest.driver, screenShotFileWithPath);
            try {
                reportLogger.fail(message, MediaEntityBuilder.createScreenCaptureFromPath("Screenshots" + File.separator + TestListener.testCaseName + date + ".png").build());
            } catch (IOException e) {
                reportLogger.fail(message);
            }
            System.out.println("FAIL >>> " + message);
        } else {
            reportLogger.log(logStatus, message);
            System.out.println(message);
        }
    }

    public void startTest(String testCaseName) {
        testCaseCounter++;
        reportLogger = report.createTest(testCaseCounter + ". " + testCaseName);
    }

    public void flushReport() {report.flush();}
}
