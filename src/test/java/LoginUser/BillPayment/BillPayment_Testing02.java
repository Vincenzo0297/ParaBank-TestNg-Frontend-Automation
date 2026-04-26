package LoginUser.BillPayment;

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

public class BillPayment_Testing02 {

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

        //Login to user
        WebElement ClickLoginUser = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/button[1]")));
        ClickLoginUser.click();

        //Navigate to Bill Payment
        WebElement navigateToBillPayment = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/aside/nav/a[4]")));
        navigateToBillPayment.click();

        //Select provider
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@id, 'provider')]")));
        Select select = new Select(dropdown);
        select.selectByIndex(2);

        //Amount (USD)
        WebElement enterAmount = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"amount\"]")));
        enterAmount.sendKeys("1000.00");

        //Description (Optional)
        WebElement enterDescription = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"description\"]")));
        enterDescription.sendKeys("Hi, I pay the Billed");

        //Payment Method with Master card
        WebElement selectMasterCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[1]/form/div[4]/div/label[1]/input")));
        selectMasterCard.click();

        // Click on pay bill
        WebElement clickPayBill = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[1]/form/button")));
        clickPayBill.click();

        // Validate Bill paid successfully
        WebElement validateSuccessfullBillPayment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[1]/form/div[1]/span")));
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
