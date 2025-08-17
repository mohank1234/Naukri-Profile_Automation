package Tests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.*;

public class BaseTest {
    protected WebDriver driver;

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path dest = Paths.get("target/screenshots/failure_" + result.getName() + ".png");
                Files.createDirectories(dest.getParent());
                Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Failure screenshot saved at: " + dest.toAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
