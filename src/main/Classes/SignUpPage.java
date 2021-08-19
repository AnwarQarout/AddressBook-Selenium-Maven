package main.Classes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import main.resources.variables;

public class SignUpPage {

    ChromeDriver driver;

    @FindBy(how = How.XPATH,using = variables.signUpHeader)
    WebElement signUpHeader;

    @FindBy(how = How.XPATH,using = variables.email)
    WebElement email;

    @FindBy(how = How.XPATH,using = variables.password)
    WebElement password;

    @FindBy(how = How.XPATH,using=variables.signUpBtn)
    WebElement signUpBtn;

    @FindBy(how = How.XPATH,using = variables.signInBtn)
    WebElement signInBtn;

    public SignUpPage(){

    }

    public SignUpPage(ChromeDriver driver){
        this.driver = driver;
    }

    public String getSignInHeader(){
        return signUpHeader.getText();
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
