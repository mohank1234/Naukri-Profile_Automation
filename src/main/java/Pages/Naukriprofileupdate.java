package Pages;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



import java.util.concurrent.TimeUnit;

public class Naukriprofileupdate 
{
        public static void NakuriChange() throws InterruptedException  {

        
        WebDriverManager.chromedriver().setup();
        WebDriver driver =new ChromeDriver();

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
                  .sendKeys("Username/Email");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@placeholder='Enter your password']"))
                  .sendKeys("Password");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()='Login']")).click();
            Thread.sleep(5000);

            // Navigate to profile edit page
            driver.findElement(By.xpath("//div[@class='view-profile-wrapper']//a[@href='/mnjuser/profile']")).click();
            Thread.sleep(10000);
            System.out.println("successfully logged into nakuri website");

            // Click on the edit option
            driver.findElement(By.xpath("//em[text()='editOneTheme']")).click();
            Thread.sleep(5000);

            // Locate the name input field
            WebElement nameField = driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']"));
            String currentName = nameField.getAttribute("value");

            System.out.println("started changing the name");
            
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

            System.out.println("changed the name successfully");
            System.out.println("Profile name updated successfully!");

        } finally {
            // Close the browser
            Thread.sleep(5000);
            driver.quit();
        }
    }
}