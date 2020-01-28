package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    static WebDriver createDriverInstance(String browserName) {
        WebDriver driver = null;
        if ("Chrome".equalsIgnoreCase(browserName)) {
            driver = chromeDriverConfiguration();
        }
        else if ("Firefox".equalsIgnoreCase(browserName)) {
            driver = firefoxDriverConfiguration();
        }
        else
            driver = chromeDriverConfiguration();
        return driver;
    }

    private static WebDriver chromeDriverConfiguration() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver firefoxDriverConfiguration() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
