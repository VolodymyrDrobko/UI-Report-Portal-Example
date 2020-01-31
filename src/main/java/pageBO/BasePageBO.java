package pageBO;

import core.CustomSoftAssert;
import core.LoggerManager;

public class BasePageBO {
    protected static CustomSoftAssert softAssert;

    public BasePageBO() {
        softAssert = new CustomSoftAssert();
    }

    public void LOG(String message) {
        LoggerManager.log(message);
    }

    public void checkAllAssertions() {
        softAssert.assertAll();
    }
}
