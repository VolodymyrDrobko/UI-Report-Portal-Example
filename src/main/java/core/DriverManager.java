package core;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        driverPool.set(driver);
    }

    public synchronized static WebDriver getDriver() {
        return driverPool.get();
    }
}
