package pageBO;

import core.CustomSoftAssert;
import core.ExtentHtmlReport;
import core.TestListener;
import page.BasePage;

public class BasePageBO {
    private BasePage basePage;
    protected ExtentHtmlReport reportLogger;
    protected static CustomSoftAssert softAssert;

    public BasePageBO() {
        basePage = new BasePage();
        reportLogger = TestListener.reportLogger;
        softAssert = new CustomSoftAssert();
    }

    public void checkAllAssertions() {
        softAssert.assertAll();
    }
}
