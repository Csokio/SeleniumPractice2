package Base;
import org.junit.jupiter.api.AfterAll;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import pages.Pages;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;


public class BaseTest {



    @AfterAll
    public static void quitDriver()
    {
        Pages.closeDriver();
    }
}
