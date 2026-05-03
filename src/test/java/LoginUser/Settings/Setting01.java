package LoginUser.Settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Setting01 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Required for Jenkins
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        // Create WebDriver instance
        driver = new ChromeDriver(options);
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

       WebElement ClickSetting = wait.until(ExpectedConditions.elementToBeClickable(
               By.xpath("//*[@id=\"root\"]/div/aside/nav/a[8]")));
       ClickSetting.click();

        //Fullname
        WebElement EnterFullName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fullName\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"fullName\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='Adam'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterFullName);

        // Email
        WebElement EnterEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"email\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='adam@email.com'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterEmail);

        //Phone
        WebElement EnterPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"phone\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"phone\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='98765432'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterPhone);

        //Passport number
        WebElement EnterPassportNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"passport\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"passport\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='PX12345096'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterPassportNumber);

        //Driver licence
        WebElement EnterDriverLicence = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"license\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"license\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='PX9329392422'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterDriverLicence);

        //Address
        WebElement EnterAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"street\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"city\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='67 Street Ave'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterAddress);

        //City
        WebElement EnterCity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"city\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"city\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='LA'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterCity);

        //State
        WebElement EnterState = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"state\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"state\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='LA'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterState);

        //Zip Code
        WebElement EnterZipCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"zip\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"zip\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='123456'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterZipCode);

        //Country
        WebElement EnterCountry= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"country\"]")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"country\"]"), "value", ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='America'; arguments[0].dispatchEvent(new Event('input',{bubbles:true})); arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", EnterCountry);

        //Click save changes
        WebElement ClickSaveButton= wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[2]/form/button")));
        ClickSaveButton.click();

        //Validate sucessfull profile update
        WebElement validateProfileUpdate = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/span")));
        String ProfileUpdate = validateProfileUpdate.getText();
        if(!ProfileUpdate.isEmpty()) {
            System.out.println("Valid profile update: " + ProfileUpdate);
        } else {
            System.out.println("Invalid profile update: " + ProfileUpdate);
        }

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
