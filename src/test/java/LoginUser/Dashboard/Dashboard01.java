package LoginUser.Dashboard;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Dashboard01 {

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

        // Validate name
        WebElement validateAccountName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/header/div/div/div[2]/div[1]")));
        String accountName = validateAccountName.getText();
        if(accountName.matches("[A-Za-z ]+")) {
            System.out.println("Valid account name: " + accountName);
        } else {
            System.out.println("Invalid account name: " + accountName);
        }

        // Validate Total Balance
        WebElement validateTotalNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[1]/div[1]/h2")));
        String totalBalance = validateTotalNumber.getText();
        if(totalBalance.matches("^\\d+(\\.\\d{2})?$")) {
            System.out.println("Valid Total Balance: " + totalBalance);
        } else {
            System.out.println("Invalid Total Balance: " + totalBalance);
        }

        // Validate account number
        WebElement validateAccountNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='root']/div/div/main/div/div/div[2]/div[2]/div/span[2]")));
        String accountNumber = validateAccountNumber.getText();
        if (accountNumber.matches("\\d{10}")) {
            System.out.println("Valid account number: " + accountNumber);
        } else {
            System.out.println("Invalid account number: " + accountNumber);
        }

        //Valiate total deposits
        WebElement validateTotalDeposits = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[3]/div[1]/p[1]")));
        String totalDeposits = validateTotalDeposits.getText();
        if(totalDeposits.matches("\\d+(\\.\\d{2})?$")) {
            System.out.println("Valid Total Deposits: " + totalDeposits);
        } else {
            System.out.println("Invalid Total Deposits: " + totalDeposits);
        }

        //Valiate total transfer out
        WebElement validateTotalTransferOut = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[3]/div[2]/p[1]")));
        String totalTransferOut = validateTotalTransferOut.getText();
        if(totalTransferOut.matches("\\d+(\\.\\d{2})?$")) {
            System.out.println("Valid Total Transfer Out: " + totalTransferOut);
        } else {
            System.out.println("Invalid Total Transfer Out: " + totalTransferOut);
        }

        //validate total transaction
        WebElement validateTotalTransaction = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[3]/div[3]/p[1]")));
        String totalTransaction = validateTotalTransaction.getText();
        if(totalTransaction.matches("\\d+(\\.\\d{2})?$")) {
            System.out.println("Valid Total Transaction: " + totalTransaction);
        } else {
            System.out.println("Invalid Total Transaction: " + totalTransaction);
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
