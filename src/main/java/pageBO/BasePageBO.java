package pageBO;

import com.aventstack.extentreports.ExtentTest;
import core.CustomSoftAssert;
import core.ReportManager;
import page.BasePage;

public class BasePageBO {
    private BasePage basePage;
    protected ExtentTest reportLogger;
    protected static CustomSoftAssert softAssert;

    public BasePageBO() {
        basePage = new BasePage();
        reportLogger = ReportManager.getReportLogger();
        softAssert = new CustomSoftAssert();
    }

    public void checkAllAssertions() {
        softAssert.assertAll();
    }
}
