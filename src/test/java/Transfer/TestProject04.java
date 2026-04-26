package Transfer;

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

public class TestProject04 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize window

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
        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/form/div[1]/input")
                )
        );
        username.sendKeys("user01");

        //Password
        WebElement password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/form/div[2]/input")
                )
        );
        password.sendKeys("user01");

        //login button
        WebElement loginButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/form/button")
                )
        );
        loginButton.click();

        //navigate to transfer
        WebElement navigateToTransfer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/*[@id=\"root\"]/div/aside/nav/a[2]")
                )
        );
        navigateToTransfer.click();

        //Recipient Account Number
        WebElement RecipientAccountNumber = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[3]/div[1]/form/div[1]/input")
                )
        );
        RecipientAccountNumber.sendKeys("user03");

        //Amount (USD)
        WebElement enterAmount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[3]/div[1]/form/div[2]/input")
                )
        );
        enterAmount.sendKeys("50");

        //Description (Optional)
        WebElement Description = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[3]/div[1]/form/div[3]/textarea")
                )
        );
        Description.sendKeys("Hello, here is the money");

        //Click Transfer Money
        WebElement clickTransfer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[3]/div[1]/form/button")
                )
        );
        clickTransfer.click();

        try {
            Thread.sleep(5000); // Sleep for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
