package core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotManager {
    public static void takeScreenshot(WebDriver driver, String screenShotFileWithPath) {
        TakesScreenshot screenShot = ((TakesScreenshot)driver);
        File screenShotSource = screenShot.getScreenshotAs(OutputType.FILE);
        File screenShotDestination = new File(screenShotFileWithPath);
        try {
            FileUtils.deleteQuietly(screenShotDestination);
            FileUtils.copyFile(screenShotSource, screenShotDestination);
        } catch (IOException e) {
            System.out.println("ScreenShot moving error");
            e.printStackTrace();
        }
    }
}
