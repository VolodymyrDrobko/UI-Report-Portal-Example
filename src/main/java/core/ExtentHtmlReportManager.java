package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import utils.Utils;

import java.io.File;
import java.io.IOException;

public class ExtentHtmlReportManager {
    private String reportName, reportPathName;
    private static int testCaseCounter = 0;

    private ExtentReports report;
    private ExtentHtmlReporter htmlReporter;
    private ExtentTest reportLogger;

    public ExtentHtmlReportManager setUpExtentHtmlReport(String testName, String browserName) {
            reportName = "Report-" + testName + "-" + browserName + ".html";
            reportPathName = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + reportName;
            htmlReporter = new ExtentHtmlReporter(reportPathName);
            report = new ExtentReports();
            report.attachReporter(htmlReporter);
        return this;
    }

    public void log(Status logStatus, String message) {
        if (logStatus == Status.FAIL || logStatus == Status.FATAL) {
            message = message.replaceAll(" ", "_").replaceAll(">>>>>", "_");
            String date = Utils.getCurrentDate();
            String screenShotName = message + date + ".png";
            String screenShotPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "Screenshots" + File.separator;
            String screenShotFileWithPath = screenShotPath + screenShotName;
            ScreenShotManager.takeScreenshot(DriverManager.getDriver(), screenShotFileWithPath);
            try {
                reportLogger.fail(message, MediaEntityBuilder.createScreenCaptureFromPath("Screenshots" + File.separator + message + date + ".png").build());
            } catch (IOException e) {
                reportLogger.fail(message);
            }
        } else {
            reportLogger.log(logStatus, message);
        }
    }

    public void startTest(String testCaseName) {
        testCaseCounter++;
        reportLogger = report.createTest(testCaseCounter + ". " + testCaseName);
        log(Status.INFO,"Test started - " + testCaseName);
    }

    public void flushReport() {report.flush();}
}
