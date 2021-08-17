package main.Classes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignUpPage {

    ChromeDriver driver;

    @FindBy(how = How.XPATH,using = "//div[@id='clearance']//child::h2")
    WebElement signUpHeader;

    @FindBy(how = How.XPATH,using = "//input[@type='email'][@placeholder='Email']")
    WebElement email;

    @FindBy(how = How.XPATH,using = "//input[@placeholder='Password'][@type='password']")
    WebElement password;

    @FindBy(how = How.XPATH,using="//input[@type='submit'][@value='Sign up']")
    WebElement signUpBtn;

    @FindBy(how = How.XPATH,using = "//a[@href='/sign_in'][text()='Sign in']")
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
