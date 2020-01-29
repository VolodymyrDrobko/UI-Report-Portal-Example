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

    public static ThreadLocal<ExtentTest> reportPool = new ThreadLocal<>();

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
                getReport().fail(message, MediaEntityBuilder.createScreenCaptureFromPath("Screenshots" + File.separator + message + date + ".png").build());
            } catch (IOException e) {
                getReport().fail(message);
            }
        } else {
            getReport().log(logStatus, message);
        }
    }

    public void createExtentHtmlReport(String testCaseName) {
        testCaseCounter++;
        ExtentTest reportLogger = report.createTest(testCaseCounter + ". " + testCaseName);
        setReport(reportLogger);
        log(Status.INFO,"Test started - " + testCaseName);
    }

    public static void setReport(ExtentTest reportLogger) {
        reportPool.set(reportLogger);
    }

    public static ExtentTest getReport() {
        return reportPool.get();
    }

    public void flushReport() {report.flush();}
}
