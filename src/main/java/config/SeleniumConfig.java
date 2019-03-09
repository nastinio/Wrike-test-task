package config;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SeleniumConfig {

    private WebDriver driver;
    public WebDriverWait wait;

    public SeleniumConfig() {
        Capabilities capabilities = new FirefoxOptions(); //DesiredCapabilities.firefox();
        driver =  new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 5);
    }

    static {
        System.setProperty("webdriver.gecko.driver", findFile("geckodriver.exe"));
    }

    static private String findFile(String filename) {
        String paths[] = {"", "bin/", "target/classes"};
        for (String path : paths) {
            if (new File(path + filename).exists())
                return path + filename;
        }
        return "";
    }

    public void close() {
        driver.close();
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitLoadElement(WebElement element) throws WebDriverException {
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
        }catch (WebDriverException e){
            throw e;
        }
    }
}
