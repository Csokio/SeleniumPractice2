package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.Duration;
import java.util.*;

public abstract class Pages {

    public static WebDriver driver;

    protected Pages(WebDriver driver)
    {
        if(Pages.driver == null){
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("ignore-certificate-errors");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-extensions");
            //options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("start-maximized");
            options.addArguments("--remote-allow-origins=*");

            Pages.driver = new ChromeDriver(options);
            Pages.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));}
    }

    public static void closeDriver()
    {
        if(driver != null){
            driver.close();
            driver = null;
        }
    }

    public void executeJavaScript(String functionName)
    {
        ((JavascriptExecutor) driver).executeScript(functionName);
    }

    protected void scrollIntoView(By xpath)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView", driver.findElement(xpath));
    }
            //"window.scrollTo[0]"

    protected void waitMethod(By xpath)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    protected void navigate(String url)
    {
        driver.navigate().to(url);
    }

    public static String[] readFile(String fileName)
    {
        List<String> resultList = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                resultList.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return resultList.toArray(new String[0]);
    }

    public static void writeFile(HashMap<String, String> map, String fileName)
    {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            for (Map.Entry<String, String> entry: map.entrySet()){
                fileWriter.write(entry.getKey() + ":" + entry.getValue());
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
