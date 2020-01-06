package core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Optional;

import static utils.Constants.DEFAULT_BROWSER;
import static utils.Constants.DEFAULT_URL;

public class BaseTest {
    public static WebDriver driver;
    public ThreadLocal<String> browserThread = new ThreadLocal<String>();
    public static String browserName = "";



    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() {
        startBrowser();
        navigateTo(Configuration.URL);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }

    private void navigateTo(String url) {
        driver.get(Optional.ofNullable(url).orElse(DEFAULT_URL));
    }

    private void startBrowser() {
        browserThread.set(Configuration.BROWSER);
        browserName = Optional.ofNullable(browserThread.get()).orElse(DEFAULT_BROWSER);
        driver = DriverManager.getDriver(browserName);
    }
}
