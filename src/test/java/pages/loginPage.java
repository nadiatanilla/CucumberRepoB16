package pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage extends CommonMethods {
    // this object repository of POM
    @FindBy(id="txtUsername")
    public WebElement userNameField;

    @FindBy(id="txtPassword")
    public WebElement passwordField;

    @FindBy(id="btnLogin")
    public WebElement loginButton;
    @FindBy(xpath = "//span[@id='spanMessage']")
    public WebElement messageError;

    //TO INITLISE ELEMENTS OF THIS PAGE WE HAVE TO CALL IT IN CONSTRUCTOR
    // CONSTRUCTOR BLOCK OF CODE
   public  loginPage(){
       PageFactory.initElements(driver,this);
    }

}
