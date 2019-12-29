package page;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = BaseTest.driver;
        PageFactory.initElements(driver, this);
    }
}
