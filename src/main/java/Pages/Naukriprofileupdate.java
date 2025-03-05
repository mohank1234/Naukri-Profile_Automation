package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Naukriprofileupdate {
    public static void NakuriChange() throws InterruptedException {
        
        // Setup Chrome options
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("--headless=new"); // Run Chrome in headless mode
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-features=TranslateUI");
        //options.addArguments("--user-data-dir=D:/Personal/Naukri-Profile_Automation/chrome-profile");
        
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

            driver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']"))
                  .sendKeys("krishnam.qaengineer@gmail.com");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@placeholder='Enter your password']"))
                  .sendKeys("@Mohan@1");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()='Login']")).click();
            Thread.sleep(5000);

            // Navigate to profile edit page
            driver.findElement(By.xpath("//div[@class='view-profile-wrapper']//a[@href='/mnjuser/profile']")).click();
            Thread.sleep(10000);
            System.out.println("Successfully logged into Naukri website");

            // Click on the edit option
            driver.findElement(By.xpath("//em[text()='editOneTheme']")).click();
            Thread.sleep(5000);

            // Locate the name input field
            WebElement nameField = driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']"));
            String currentName = nameField.getAttribute("value");

            System.out.println("Started changing the name");
            
            // Toggle name value
            if (currentName.equals("Krishnna Mohan B")) {
                nameField.clear();
                nameField.sendKeys("Krishnna Mohan Batthini");
            } else if (currentName.equals("Krishnna Mohan Batthini")) {
                nameField.clear();
                nameField.sendKeys("Krishnna Mohan B");
            }

            // Save changes
            driver.findElement(By.id("saveBasicDetailsBtn")).click();

            System.out.println("Changed the name successfully");
            System.out.println("Profile name updated successfully!");
            
            //Logout
            Thread.sleep(5000);
             driver.findElement(By.xpath("//img[@alt='naukri user profile img']")).click();
             
             driver.findElement(By.xpath("//a[@data-type='logoutLink']")).click();
             
             By confrimlogout = By.xpath("//h1[text()='Find your dream job now']");
             
             WebDriverWait logout = new WebDriverWait(driver,Duration.ofSeconds(15));
             WebElement loginField = logout.until(ExpectedConditions.visibilityOfElementLocated(confrimlogout)); // Replace with actual login field locator
             
             if (loginField.isDisplayed()) {
            	    System.out.println("Logout successful!");
            	} else {
            	    System.out.println("Logout failed!");
            	}
              	
             
        } finally {
            // Close the browser
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
