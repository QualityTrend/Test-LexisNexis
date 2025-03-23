package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setup() {
        boolean isMac = System.getProperty("os.name").startsWith("Mac");
        boolean isLinux = System.getProperty("os.name").startsWith("Linux");
        String driverPath ;

        if (isMac) {
            driverPath = System.getProperty("user.dir") + "/src/test/java/Drivers/Mac/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else if (isLinux) {
            driverPath = System.getProperty("user.dir") + "/src/test/java/Drivers/Linux/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else {
            driverPath = System.getProperty("user.dir") + "/src/test/java/Drivers/Windows/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverPath);
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
