package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import dev.failsafe.Timeout;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Naukriprofileupdate {
    public static void NakuriChange() throws InterruptedException, IOException {
        
        // Setup Chrome options
    	ChromeOptions options = new ChromeOptions();

    	// Run in headless mode (modern headless)
    	options.addArguments("--headless=new");

    	// Make viewport big enough so elements are visible
    	options.addArguments("--window-size=1920,1080");
		options.addArguments("--start-maximized");
    	// Prevent resource/memory issues in CI (Docker/GitHub Actions)
    	options.addArguments("--disable-dev-shm-usage");
    	options.addArguments("--no-sandbox");

    	// Stability & compatibility
    	options.addArguments("--disable-gpu");
    	options.addArguments("--remote-allow-origins=*");


		
    	// Optional: disable unnecessary features
    	options.addArguments("--disable-extensions");
    	options.addArguments("--disable-popup-blocking");
    	options.addArguments("--disable-features=TranslateUI");
    	options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
    		    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/139.0.0.0 Safari/537.36");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);

      try {
    // Browser settings
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    // Open Naukri and login
    driver.get("https://www.naukri.com/");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    // Click Login
	WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Jobseeker Login']")));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
	wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    System.out.println("Got the login page");

    // Get credentials from environment variables (GitHub Secrets or local system)
    String username = System.getenv("NAUKRI_USERNAME");
    String password = System.getenv("NAUKRI_PASSWORD");

    System.out.println("Got the credentials");

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your active Email ID / Username']")))
        .sendKeys(username);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your password']")))
        .sendKeys(password);

	System.out.println("Username length: " + (username == null ? "null" : username.length()));
    System.out.println("Password length: " + (password == null ? "null" : password.length()));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']"))).click();
    System.out.println("Login done");

    // Take screenshot after login
    File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    Files.copy(src1.toPath(), Paths.get("screenshot_login.png"), StandardCopyOption.REPLACE_EXISTING);
		  
// // Navigate to profile edit page
	WebElement profileLink = wait.until(
	ExpectedConditions.visibilityOfElementLocated(
	By.xpath("//div[@class='view-profile-wrapper']//a[@href='/mnjuser/profile']")
	));
	System.out.println("✅ Able to see the profile link");
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", profileLink);
	profileLink.click();
	
	System.out.println("🎉 Successfully logged into Naukri website");

 // Screenshot after navigating to profile
    File src2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    Files.copy(src2.toPath(), Paths.get("screenshot_profile.png"), StandardCopyOption.REPLACE_EXISTING);

    // Click on the edit option
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//em[text()='editOneTheme']"))).click();

    // Screenshot after opening edit
    File src3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    Files.copy(src3.toPath(), Paths.get("screenshot_edit.png"), StandardCopyOption.REPLACE_EXISTING);

    // Locate the name input field
    WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//input[@placeholder='Enter Your Name']"))
    );
    String currentName = nameField.getAttribute("value");
    System.out.println("printing the name before changing:" + currentName);
    System.out.println("Started changing the name");

    // Toggle name value
    if (currentName.equals("Krishnna Mohan B")) {
        nameField.clear();
        System.out.println("Cleared the name");
        nameField.sendKeys("Krishnna Mohan Batthini");
    } else if (currentName.equals("Krishnna Mohan Batthini")) {
        nameField.clear();
        System.out.println("Cleared the name");
        nameField.sendKeys("Krishnna Mohan B");
    }

    // Save changes
    wait.until(ExpectedConditions.elementToBeClickable(By.id("saveBasicDetailsBtn"))).click();

    System.out.println("Changed the name successfully");
    System.out.println("Profile name updated successfully!");
    String afterChangingName = nameField.getAttribute("value");
    System.out.println("printing the name after changing:" + afterChangingName);

    // Screenshot after saving
    File src4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    Files.copy(src4.toPath(), Paths.get("screenshot_after_save.png"), StandardCopyOption.REPLACE_EXISTING);

} finally {
    Thread.sleep(5000);
    System.out.println("Update done");
    driver.quit();
  }
 }
}
