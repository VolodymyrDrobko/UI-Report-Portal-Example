package core;

import com.epam.reportportal.message.ReportPortalMessage;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotManager {
    public static void takeScreenshot(WebDriver driver, String screenShotFileWithPath) {
        TakesScreenshot screenShot = ((TakesScreenshot) driver);
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

    public static String getScreenshotPathWithName(String screenShotName) {
        String screenShotFilePath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator;
        String screenshotFileName = screenShotName.replaceAll(" ", "_") + ".png";
        return screenShotFilePath + screenshotFileName;
    }

    public static ReportPortalMessage attachScreenShotToReport(String assertResultMessage, String screenshotHome) {
        ReportPortalMessage message = null;
        String description = assertResultMessage + " >>>>> FAILED";
        try {
            message = new ReportPortalMessage(new File(screenshotHome), description);
        } catch (IOException e) {
            e.printStackTrace();
            LoggerManager.getLogger().log(Level.INFO, "Screenshot do not build");
        }
        return message;
    }
}
