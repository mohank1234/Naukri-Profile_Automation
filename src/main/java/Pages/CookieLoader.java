import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CookieLoader {

    public static void loadCookies(WebDriver driver) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray cookies = (JSONArray) parser.parse(new FileReader("src/test/resources/cookies.json"));

        for (Object obj : cookies) {
            JSONObject jsonObject = (JSONObject) obj;

            String name = (String) jsonObject.get("name");
            String value = (String) jsonObject.get("value");
            String domain = (String) jsonObject.get("domain");
            String path = (String) jsonObject.get("path");

            Boolean isSecure = (Boolean) jsonObject.get("secure");
            Boolean isHttpOnly = (Boolean) jsonObject.get("httpOnly");

            Cookie cookie = new Cookie.Builder(name, value)
                    .domain(domain)
                    .path(path)
                    .isSecure(isSecure)
                    .isHttpOnly(isHttpOnly)
                    .build();

            driver.manage().addCookie(cookie);
        }
    }
}
