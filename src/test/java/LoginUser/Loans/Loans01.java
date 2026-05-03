package LoginUser.Loans;

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

public class Loans01 {

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

        //Navigate to loans
        WebElement NavigateToLoan = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/aside/nav/a[6]")));
        NavigateToLoan.click();

        //Personal Loan
        WebElement ClickPersonalLoan = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[1]/div[2]/div/label[1]")));
        ClickPersonalLoan.click();

        WebElement ClickNextButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[1]/div[3]/button")));
        ClickNextButton.click();

        WebElement EnterAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"amount\"]")));
        EnterAmount.sendKeys("40000");

        WebElement EnterMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"term\"]")));
        EnterMonth.sendKeys("13");

        // Validate the total amount
        String moneyPattern = "^\\s*[$]?[0-9]{1,3}(,[0-9]{3})*(\\.[0-9]{1,2})?\\s*$|^\\s*[$]?\\d+(\\.\\d{1,2})?\\s*$";
        WebElement ValidateTotalAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/div[1]/div[2]/form/div[3]/div/div[2]")));
        String totalAmount = ValidateTotalAmount.getText();
        if(totalAmount.matches(moneyPattern)) {
            System.out.println("Display Total Amount: " + totalAmount);
        } else {
            System.out.println("Invalid Total Amount: " + totalAmount);
        }

        WebElement Clickbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'wizard-actions')]/button[2]")));
        Clickbutton.click();

        //validate loan amount
        WebElement ValidateLoanAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'review-summary')]/div[2]/span[2]")));
        String totalLoanAmount = ValidateLoanAmount.getText();
        if(totalLoanAmount.matches(moneyPattern)) {
            System.out.println("Display Loan Amount: " + totalLoanAmount);
        } else {
            System.out.println("Invalid Loan Amount: " + totalLoanAmount);
        }

        // validate loan term
        String termRegex = "\\d+\\s*months?";
        WebElement ValidateLoanTerm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'review-summary')]/div[3]/span[2]")));
        String loanTerm = ValidateLoanTerm.getText();
        if(loanTerm.matches(termRegex)) {
            System.out.println("Display Loan Term: " + loanTerm);
        } else {
            System.out.println("Invalid Loan Term: " + loanTerm);
        }

        // validate interest rate
        String rateRegex = "\\d+%(\\s*p\\.?a\\.?)?";
        WebElement ValidateInterestRate = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'review-summary')]/div[4]/span[2]")));
        String InterestRate = ValidateInterestRate.getText();
        if (InterestRate.matches(rateRegex)) {
            System.out.println("Display Interest Rate: " + InterestRate);
        } else {
            System.out.println("Invalid Interest Rate: " + InterestRate);
        }

        // validate monthly payment
        WebElement ValidateMonthlyPayment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'review-summary')]/div[5]/span[2]")));
        String MonthlyPayment = ValidateMonthlyPayment.getText();
        if(MonthlyPayment.matches(moneyPattern)) {
            System.out.println("Display Monthly Payment: " + MonthlyPayment);
        } else {
            System.out.println("Invalid Monthly Payment: " + MonthlyPayment);
        }

        // validate total amount payable
        WebElement ValidateAmountPayable = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'review-summary')]/div[6]/span[2]")));
        String AmountPayable = ValidateAmountPayable.getText();
        if(AmountPayable.matches(moneyPattern)) {
            System.out.println("Display Amount Payable: " + AmountPayable);
        } else {
            System.out.println("Invalid Amount Payable: " + AmountPayable);
        }

        WebElement ClickSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'wizard-actions')]/button[2]")));
        ClickSubmitButton.click();

        //Validate successfull message
        WebElement validateSuccessfullLoanPayment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'alert alert-success')]/span")));
        String loanPaymentText = validateSuccessfullLoanPayment.getText();
        if (loanPaymentText.toLowerCase().contains("success")) {
            System.out.println("Success message: " + loanPaymentText);
        } else {
            System.out.println("Unexpected message: " + loanPaymentText);
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
