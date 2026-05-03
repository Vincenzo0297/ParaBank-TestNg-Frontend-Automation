package HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestProject03 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Required for Jenkins
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        // Create WebDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFormFilling() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Navigate to login page
        driver.get("https://vb-bank-demo.vercel.app/login");

        //user name
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div[1]/form/div[1]/input")));
        username.sendKeys("user01");

        //Password
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div[1]/form/div[2]/input")));
        password.sendKeys("user01");

        //login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div[1]/form/button")));
        loginButton.click();

        // Validate account name
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//*[contains(text(),'user01')]"),
                "user01"));
        String validateAccountname = driver.findElement(By.xpath("//*[contains(text(),'user01')]")).getText();
        System.out.println(validateAccountname);

        // Validate account number
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//span[contains(text(), '3127555071')]"),
                "3127555071"));
        String validateAccountNumber = driver.findElement(By.xpath("//span[contains(text(), '3127555071')]")).getText();
        System.out.println(validateAccountNumber);

//        try {
//            Thread.sleep(5000); // Sleep for 5 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
