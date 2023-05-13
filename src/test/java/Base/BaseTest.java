package Base;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import pages.Pages;


public class BaseTest {


    public WebDriver getDriver()
    {
        return Pages.driver;
    }

    @AfterAll
    public static void quitDriver()
    {
        Pages.closeDriver();
    }
}
