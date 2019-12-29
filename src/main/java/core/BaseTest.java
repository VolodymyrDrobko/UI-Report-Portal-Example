package core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Optional;

public class BaseTest {
    public static WebDriver driver;
    public ThreadLocal<String> browserThread = new ThreadLocal<String>();
    public static String browserName = "";
    public static String DEFAULT_BROWSER = "Chrome";


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
        driver.get(url);
    }

    private void startBrowser() {
        browserThread.set(Configuration.BROWSER);
        browserName = Optional.ofNullable(browserThread.get()).orElse(DEFAULT_BROWSER);
        driver = DriverManager.getDriver(browserName);
    }
}
