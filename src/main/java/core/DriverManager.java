package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static String browser;
    private static WebDriver driver = null;
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver(String browserName) {
        browser = browserName;
        if (browser.equals("Chrome")) {
            driver = chromeDriverConfiguration();
        }
        driverThread.remove();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver chromeDriverConfiguration() {
        WebDriverManager.chromedriver().setup();
        driverThread.set(new ChromeDriver());
        return driverThread.get();
    }
}
