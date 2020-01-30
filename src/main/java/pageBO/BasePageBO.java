package pageBO;

import core.CustomSoftAssert;
import page.BasePage;

public class BasePageBO {
    private BasePage basePage;
    protected static CustomSoftAssert softAssert;

    public BasePageBO() {
        basePage = new BasePage();
        softAssert = new CustomSoftAssert();
    }

    public void checkAllAssertions() {
        softAssert.assertAll();
    }
}
