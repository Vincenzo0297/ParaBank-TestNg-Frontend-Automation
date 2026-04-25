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

public class TestProject02 {

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

        //navigate to register
        WebElement navigateToRegister = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/p/a")
                )
        );
        navigateToRegister.click();

        //Fullname
        WebElement RegisterfullName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/form/div[1]/input")
                )
        );
        RegisterfullName.sendKeys("user04");

        //username
        WebElement Registerusername = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/form/div[2]/input")
                )
        );
        Registerusername.sendKeys("user04");

        //email
        WebElement RegisterEmail = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/form/div[3]/input")
                )
        );
        RegisterEmail.sendKeys("user04@gmail.com");

        //password
        WebElement RegisterPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/form/div[4]/input")
                )
        );
        RegisterPassword.sendKeys("user04");

        //confirm password
        WebElement RegisterConfirmPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/form/div[5]/input")
                )
        );
        RegisterConfirmPassword.sendKeys("user04");

        //click create account
        WebElement createAccount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/div[1]/form/button")
                )
        );
        createAccount.click();

        //validate account name
        WebElement validateAccountname = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(), 'user04')]")
                )
        );
        String validateText = validateAccountname.getText().trim();
        Assert.assertEquals(validateText, "user04");
        System.out.println(validateText);

        //click logout
        WebElement logoutButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"root\"]/div/aside/div[2]/button")
                )
        );
        logoutButton.click();

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
