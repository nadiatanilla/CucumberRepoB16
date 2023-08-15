package steps;

import Utils.CommonMethods;
import Utils.ConfigReader;
import Utils.Log;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.loginPage;

import java.time.Duration;

public class LoginSteps extends CommonMethods {

   //  public WebDriver driver;
    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        // Write code here that turns the phrase above into concrete actions
        //launch to website
        openBrowserAndLaunchApplication();
     //  driver=new ChromeDriver();
      // driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
      // driver.manage().window().maximize();
     //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
     //   loginPage loginPage=new loginPage();
        // Write code here that turns the phrase above into concrete actions
     //   WebElement userName=driver.findElement(By.id("txtUsername"));
        // to avoid hard coding we are going to use
     //   userName.sendKeys(ConfigReader.getPropertyValue("username"));
        ///we are calling DOMConfigurator which is asking for the file which we used
        //to integrate logs in our project
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("My batch 16 test case starts here");
        sendText(ConfigReader.getPropertyValue("username"),loginPage.userNameField);
      //  WebElement pass=driver.findElement(By.id("txtPassword"));
       // pass.sendKeys(ConfigReader.getPropertyValue("password"));
        Log.info("my username has been entered");
        sendText(ConfigReader.getPropertyValue("password"),loginPage.passwordField);
        Log.info("my password has been entered");
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        // Write code here that turns the phrase above into concrete actions
       // loginPage loginPage=new loginPage();
      //  WebElement loginButton = driver.findElement(By.name("Submit"));
      //  loginButton.click();
        click(loginPage.loginButton);
    }
    @Then("user is successfully logged in the application")
    public void user_is_successfully_logged_in_the_application() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("succefully passed");
    }

    @When("user enters ess username and password")
    public void user_enters_ess_username_and_password() {
      //  loginPage loginPage = new loginPage();
        //  WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //  WebElement passwordField = driver.findElement(By.id("txtPassword"));
        //logged in via normal employee
        //usernameField.sendKeys("dalima123");
        //  passwordField.sendKeys("Hum@nhrm123");
        sendText("dalima123", loginPage.userNameField);
        sendText("Hum@nhrm123", loginPage.passwordField);
    }

    @When("user enters invalid admin username and password")
    public void user_enters_invalid_admin_username_and_password() {
        // WebElement usernameField = driver.findElement(By.id("txtUsername"));
        // WebElement passwordField = driver.findElement(By.id("txtPassword"));
        // usernameField.sendKeys("admin123");
        //  passwordField.sendKeys("Hum@nhrm123");
       // loginPage loginPage = new loginPage();
        sendText("admin123", loginPage.userNameField);
        sendText("Hum#n", loginPage.passwordField);
    }
    @Then("error message is displayed")
    public void error_message_is_displayed() {
        System.out.println("error message is displayed");
    }

    @When("user enters {string} and {string} and verifying the {string} for the combinations")
    public void user_enters_and_and_verifying_the_for_the_combinations
            (String username, String password, String errorMessage) {
        //we need to write the code here to match the errors
        sendText(username, loginPage.userNameField);
        sendText(password, loginPage.passwordField);
        click(loginPage.loginButton);
        // fetching the error message from the element

        String actualMsgError= loginPage.messageError.getText();
        Assert.assertEquals("Values does not match",errorMessage,actualMsgError);
        //error coming from
    }
}
