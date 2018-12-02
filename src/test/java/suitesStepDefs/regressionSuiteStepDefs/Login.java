package suitesStepDefs.regressionSuiteStepDefs;

import base.DriverFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login {

    //The driver instance should be instantiated in TestBase class, not in every test class
    private WebDriver driver = DriverFactory.driver;

    @Given("^a web browser is at the BBC home page$")
    public void aWebBrowserIsAtTheBBCHomePage() {

        driver.navigate().to(DriverFactory.baseUrl);
        try {

            Assert.assertEquals(driver.getTitle(), "BBC - Homepage");

        } catch (AssertionError er) {

            System.out.println("You are not on 'BBC - Homepage'");

        }

    }

    @When("^the user clicks on the Sign In link$")
    public void theUserClicksOnTheSignInLink() {

        driver.findElement(By.id("idcta-link")).click();

    }

    @Then("^the user is redirected to Sign In form$")
    public void theUserIsRedirectedToSignInForm() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submit-button")));
        driver.findElement(By.id("submit-button")).isDisplayed();

    }

    @Given("^a web browser is at the BBC Sign In page$")
    public void aWebBrowserIsAtTheBBCSignInPage() {

        driver.navigate().to("https://account.bbc.com/signin");

        try {

            Assert.assertEquals(driver.getTitle(), "BBC – Sign in");

        } catch (AssertionError er) {

            System.out.println("You are not on 'BBC - Sign in");
        }

    }

    @When("^the user enters \"([^\"]*)\" username$")
    public void theUserEntersUsername(String username) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("user-identifier-input")));
        WebElement usernameField = driver.findElement(By.id("user-identifier-input"));
        usernameField.sendKeys(username);
    }

    @And("^the user clicks on the Sign In button$")
    public void theUserClicksOnTheSignInButton() {

        driver.findElement(By.id("submit-button")).click();

    }

    @And("^the use sees the username error message$")
    public void theUseSeesTheUsernameErrorMessage() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("form-message-general")));
        String actualMessage = driver.findElement(By.id("form-message-general")).getText();
        String expectedMessage = "Sorry, those details don't match. Check you've typed them correctly.";

        try {

            Assert.assertEquals(actualMessage, expectedMessage);

        } catch (AssertionError er) {

            System.out.println("Not valid username field error message.");

        }

    }

    @And("^the use sees the password error message$")
    public void theUseSeesThePasswordErrorMessage() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("form-message-password")));
        String actualMessage = driver.findElement(By.id("form-message-password")).getText();
        String expectedMessage = "Something's missing. Please check and try again.";

        try {

            Assert.assertEquals(actualMessage, expectedMessage);

        } catch (AssertionError er) {

            System.out.println("Not valid password field error message.");

        }

    }
}
