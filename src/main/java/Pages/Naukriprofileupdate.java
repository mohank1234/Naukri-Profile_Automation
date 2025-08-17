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
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Open Naukri and login
            driver.get("https://www.naukri.com/");
            driver.findElement(By.xpath("//a[@title='Jobseeker Login']")).click();
            Thread.sleep(5000);
            System.out.println("Got the login page");
            
           // Get credentials from environment variables (GitHub Secrets or local system)
            String username = System.getenv("NAUKRI_USERNAME");
            String password = System.getenv("NAUKRI_PASSWORD");
            
            System. out.println("Got the credintailas");
            
            driver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']"))
                  .sendKeys(username);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@placeholder='Enter your password']"))
                  .sendKeys(password);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()='Login']")).click();
            Thread.sleep(5000);
			System.out.println("Login done");
  
            //close the side popup
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), Paths.get("headless_debug.png"), StandardCopyOption.REPLACE_EXISTING);

            
            // Navigate to profile edit page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

	         // Wait until the element is clickable
	         WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
	             By.xpath("//div[@class='view-profile-wrapper']//a[@href='/mnjuser/profile']")
	         ));
	         // Click the element
	         profileLink.click();
	         // Wait for demonstration purposes (not recommended in real automation)
	         Thread.sleep(10000);
	         System.out.println("Successfully logged into Naukri website");


            // Click on the edit option
            driver.findElement(By.xpath("//em[text()='editOneTheme']")).click();
            Thread.sleep(5000);

            // Locate the name input field
            WebElement nameField = driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']"));
            String currentName = nameField.getAttribute("value");
            System.out.println("printing the name before changing:"+currentName);
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
            driver.findElement(By.id("saveBasicDetailsBtn")).click();

            System.out.println("Changed the name successfully");
            System.out.println("Profile name updated successfully!");
            String afterChangingName = nameField.getAttribute("value");
            System.out.println("printing the name after changing:"+afterChangingName);
            System.out.println("changed the name successfully");
                          	
        } finally {
            // Close the browser
            Thread.sleep(5000);
            System.out.println("Update done");
            driver.quit();
        }
    }
}
