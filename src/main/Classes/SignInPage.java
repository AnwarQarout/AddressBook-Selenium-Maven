package main.Classes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import main.resources.variables;

public class SignInPage {
    ChromeDriver driver;

    public SignInPage(){

    }

    public SignInPage(ChromeDriver driver){
        this.driver = driver;
    }

    @FindBy(how = How.XPATH,using = variables.signInHeader)
    WebElement signInHeader;

    @FindBy(how = How.XPATH,using = variables.signInEmail)
    WebElement email;

    @FindBy(how = How.XPATH,using = variables.signInPassword)
    WebElement password;

    @FindBy(how = How.XPATH,using=variables.signInButton)
    WebElement signInBtn;

    @FindBy(how = How.XPATH,using = variables.signUpButton)
    WebElement signUpBtn;

    public String getSignInHeader(){
        return signInHeader.getText();
    }

    public void setEmail(String text){
        email.sendKeys(text);
    }

    public void setPassword(String text){
        password.sendKeys(text);
    }

    public void clickSignInBtn(){
        signInBtn.click();
    }

    public void clickSignUpBtn(){
        signUpBtn.click();
    }
}
