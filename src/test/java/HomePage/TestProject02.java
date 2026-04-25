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
        options.addArguments("--incognito"); // opens private window
        options.addArguments("--start-maximized"); // Maximize window

        // Create WebDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFormFilling() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to login page
        driver.get("https://parabank.parasoft.com/parabank/index.htm;jsessionid=39809168774D6EA48CC2E1A187C8CD23");

        // =========================
        // LOGIN
        // =========================
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input")).sendKeys("user01");
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input")).sendKeys("user01");
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input")).click();

        // Wait for error message to appear
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rightPanel']/p"))
        );

        // Get text
        String actualText = errorMsg.getText();
        String expectedText = "The username and password could not be verified.";

        // Assertion
        System.out.println(expectedText);
        Assert.assertEquals(actualText, expectedText);

        // =========================
        // Register
        // =========================
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"customer.firstName\"]")).sendKeys("Johnny");
        driver.findElement(By.xpath("//*[@id=\"customer.lastName\"]")).sendKeys("Sin");
        driver.findElement(By.xpath("//*[@id=\"customer.address.street\"]")).sendKeys("California Street 01");
        driver.findElement(By.xpath("//*[@id=\"customer.address.city\"]")).sendKeys("California");
        driver.findElement(By.xpath("//*[@id=\"customer.address.state\"]")).sendKeys("New Tork 14325");
        driver.findElement(By.xpath("//*[@id=\"customer.address.zipCode\"]")).sendKeys("975342");
        driver.findElement(By.xpath("//*[@id=\"customer.phoneNumber\"]")).sendKeys("987-43958359");
        driver.findElement(By.xpath("//*[@id=\"customer.ssn\"]")).sendKeys("123456");

        driver.findElement(By.xpath("//*[@id=\"customer.username\"]")).sendKeys("John");
        driver.findElement(By.xpath("//*[@id=\"customer.password\"]")).sendKeys("Sin");
        driver.findElement(By.xpath("//*[@id=\"repeatedPassword\"]")).sendKeys("Sin1234");
        driver.findElement(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")).click();

        //Internal error message
        WebElement errorText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rightPanel']/p"))
        );

        // Get text
        String errorActualText = errorText.getText();
        String expectedInternalText = "An internal error has occurred and has been logged.";

        // Assertion
        System.out.println(expectedInternalText);
        Assert.assertEquals(errorActualText, expectedInternalText);

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
