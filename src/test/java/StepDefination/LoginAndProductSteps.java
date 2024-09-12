package StepDefination;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ExtentReportUtil;

public class LoginAndProductSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
        ExtentReportUtil.setupReport(); // Initialize Extent Reports
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportUtil.tearDown(); // Finalize Extent Reports
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://www.automationexercise.com/login");
        ExtentReportUtil.startTest("Navigate to Login Page");
        ExtentReportUtil.addScreenshotToReport(takeScreenshot("loginPage"));
        System.out.println("Navigated to login page.");
    }

    @When("I login with valid credentials")
    public void i_login_with_valid_credentials() {
        try {
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));

            emailField.sendKeys("gadgeshwetal@gmail.com");
            passwordField.sendKeys("Sweety12@");
            loginButton.click();
            ExtentReportUtil.startTest("Login with Valid Credentials");
            ExtentReportUtil.addScreenshotToReport(takeScreenshot("validLogin"));
            System.out.println("Logged in with valid credentials.");
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            ExtentReportUtil.startTest("Login with Valid Credentials - Failed");
            ExtentReportUtil.addScreenshotToReport(takeScreenshot("loginFailed"));
            throw e;
        }
    }

    @When("I navigate to the products page")
    public void i_navigate_to_the_products_page() {
        try {
            WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']")));
            productsLink.click();
            ExtentReportUtil.startTest("Navigate to Products Page");
            ExtentReportUtil.addScreenshotToReport(takeScreenshot("productsPage"));
            System.out.println("Navigated to products page.");
        } catch (Exception e) {
            System.out.println("Failed to navigate to products page: " + e.getMessage());
            ExtentReportUtil.startTest("Navigate to Products Page - Failed");
            ExtentReportUtil.addScreenshotToReport(takeScreenshot("navigationFailed"));
            throw e;
        }
    }

    @Then("I print all product names and prices")
    public void i_print_all_product_names_and_prices() {
        try {
            List<WebElement> products = driver.findElements(By.xpath("//div[@class='productinfo text-center']"));
            System.out.println("Total products: " + products.size());

            for (WebElement product : products) {
                String productName = product.findElement(By.tagName("p")).getText();
                String productPrice = product.findElement(By.tagName("h2")).getText();

                System.out.println("Product Name: " + productName);
                System.out.println("Product Price: " + productPrice);
                System.out.println("----------------------");
            }
            ExtentReportUtil.startTest("Print Product Names and Prices");
            ExtentReportUtil.addScreenshotToReport(takeScreenshot("productList"));
        } catch (Exception e) {
            System.out.println("Error interacting with product: " + e.getMessage());
            ExtentReportUtil.startTest("Print Product Names and Prices - Failed");
            ExtentReportUtil.addScreenshotToReport(takeScreenshot("productListFailed"));
            throw e;
        }
    }

    // Utility method to capture screenshots
    private String takeScreenshot(String screenshotName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "target/screenshots/" + screenshotName + ".png";
        try {
            File screenshotFile = new File(screenshotPath);
            com.google.common.io.Files.copy(screenshot, screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }
}
