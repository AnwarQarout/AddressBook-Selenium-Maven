package main.Classes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInPage {
    ChromeDriver driver;

    public SignInPage(){

    }

    public SignInPage(ChromeDriver driver){
        this.driver = driver;
    }

    @FindBy(how = How.XPATH,using = "//div[@id='clearance']//child::h2")
    WebElement signInHeader;

    @FindBy(how = How.XPATH,using = "//input[@type='email'][@placeholder='Email']")
    WebElement email;

    @FindBy(how = How.XPATH,using = "//input[@placeholder='Password'][@type='password']")
    WebElement password;

    @FindBy(how = How.XPATH,using="//input[@type='submit'][@value='Sign in']")
    WebElement signInBtn;

    @FindBy(how = How.XPATH,using = "//a[@href='/sign_up'][text()='Sign up']")
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
