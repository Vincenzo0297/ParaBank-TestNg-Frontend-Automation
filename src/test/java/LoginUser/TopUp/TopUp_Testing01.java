package LoginUser.TopUp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TopUp_Testing01 {

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

        //Login to user
        WebElement ClickLoginUser = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/button[1]")));
        ClickLoginUser.click();

        //Navigate to Top Up
        WebElement navigateToTopUp = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/aside/nav/a[7]")));
        navigateToTopUp.click();

        // Validate current Balance
        WebElement validateCurrentBalance = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[1]/div[1]/div[2]")));
        String currentBalanceText = validateCurrentBalance.getText().trim();
        if (currentBalanceText.matches("\\$\\d+\\.\\d{2}")) {
            System.out.println("Valid amount: " + currentBalanceText);
        } else {
            System.out.println("Invalid amount: " + currentBalanceText);
        }

        //Enter Amount
        WebElement enterAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"amount\"]")));
        enterAmount.sendKeys("1000");

        //Click proceed Payment
        WebElement clickProceedPayment = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[1]/form/button")));
        clickProceedPayment.click();

        //Card number
        WebElement enterCardNumber = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"cardNumber\"]")));
        enterCardNumber.sendKeys("1234567890123456");

        // cardholder name
        WebElement enterCardHolderName = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"cardholderName\"]")));
        enterCardHolderName.sendKeys("John Doe");

        //Expiry Date
        WebElement enterExpiryDate = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"expiry\"]")));
        enterExpiryDate.sendKeys("0626");

        //CVV
        WebElement enterCVV = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"cvv\"]")));
        enterCVV.sendKeys("5678");

        //Click on Pay button
        WebElement clickPayButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/div/form/div[4]/button[1]")));
        clickPayButton.click();

        // Validate Bill paid successfully
        WebElement validateSuccessfullBillPayment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/span")));
        String billPaymentText = validateSuccessfullBillPayment.getText();
        if(billPaymentText.matches("[A-Za-z ]+")) {
            System.out.println("Display: " + billPaymentText);
        }

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
