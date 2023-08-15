package steps;

import Utils.CommonMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class EmployeeSearchSteps extends CommonMethods {
   // public WebDriver driver;

    @When("user clicks on PIM option and Employee list option")
    public void user_clicks_on_pim_option_and_employee_list_option() {
      //  WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
      //  pimOption.click();
        click(dashboardPage.pimOption);
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // WebElement EmpListOption = driver.findElement(By.id("menu_pim_viewEmployeeList"));
      //  EmpListOption.click();
        click(dashboardPage.empListOption);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        // Write code here that turns the phrase above into concrete actions
       // WebElement searchIdTextBox = driver.findElement(By.id("empsearch_id"));
       // searchIdTextBox.sendKeys("54469A");
        sendText("54469A", employeeSearchPage.idTextField);
    }
    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        // Write code here that turns the phrase above into concrete actions
      //  WebElement searchButton = driver.findElement(By.id("searchBtn"));
      //  searchButton.click();
        click(employeeSearchPage.searchButton);
    }
    @Then("user is able to see employee information")
    public void user_is_able_to_see_employee_information() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Employee is displayed");
    }

    @When("user enters valid employee name in name text box")
    public void user_enters_valid_employee_name_in_name_text_box() {
     //  WebElement name=driver.findElement(By.id("empsearch_employee_name_empName"));
     //  name.sendKeys("selab");
        sendText("selab",employeeSearchPage.nameTextField);
    }


}
