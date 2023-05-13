package Base;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import pages.Pages;


public class BaseTest {



    @AfterAll
    public static void quitDriver()
    {
        Pages.closeDriver();
    }
}
