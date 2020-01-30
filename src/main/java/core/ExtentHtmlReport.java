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

    static ExtentReports report;
    ExtentHtmlReporter htmlReporter;

    public ExtentHtmlReport setUpExtentHtmlReport(String testName, String browserName) {
            reportName = "Report-" + testName + "-" + browserName + ".html";
            reportPathName = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + reportName;
            htmlReporter = new ExtentHtmlReporter(reportPathName);

            report = new ExtentReports();
            report.attachReporter(htmlReporter);
        return this;
    }

    public void log(ExtentTest report, Status logStatus, String message) {
        if (logStatus == Status.FAIL || logStatus == Status.FATAL)
            createScreenshot(report, message);
        else if (logStatus == Status.SKIP)
            report.log(logStatus, message + " >>>>> SKIPPED");
        else if (logStatus == Status.PASS)
            report.log(logStatus, message + " - PASSED SUCCESSFULLY");
        else
            report.log(logStatus, message);
    }

    private void createScreenshot(ExtentTest report, String message) {
        String date = Utils.getCurrentDate();
        String screenShotName = message.replaceAll(" ", "_") + date + ".png";
        String screenShotPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "Screenshots" + File.separator;
        String screenShotFileWithPath = screenShotPath + screenShotName;
        ScreenShotManager.takeScreenshot(DriverManager.getDriver(), screenShotFileWithPath);
        try {
            report.fail(message + " >>>>> FAILED", MediaEntityBuilder.createScreenCaptureFromPath("Screenshots" + File.separator + screenShotName).build());
        } catch (IOException e) {
            report.fail(message);
        }
    }

    public ExtentTest createExtentHtmlReport(String testCaseName) {
        testCaseCounter++;
        ExtentTest reportLogger = report.createTest(testCaseCounter + ". " + testCaseName);
        return reportLogger;
    }

    public void flushReport() {report.flush();}

}
