package Tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.Naukriprofileupdate;

public class NakuriUpdateTest 
{
	@BeforeTest
    public void setup() throws Exception {
        // Kill existing Chrome processes before starting the test
       // Runtime.getRuntime().exec("pkill -f chrome");
        Thread.sleep(2000); // Small delay to ensure process is killed
    }

	@Test
   static void Nakuriupdate() throws Exception
   {
	   Naukriprofileupdate.NakuriChange();
   }
}