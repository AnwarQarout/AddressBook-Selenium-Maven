package main.java;

import main.Classes.SignInPage;
import main.Classes.SignUpPage;
import main.resources.variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignUpPageTest extends ChromeDriverInit {

    private ChromeDriver driver;

    public SignUpPageTest(){

    }

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get(variables.signUpURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }




    @Test
    public void testSignUpPageTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Address Book - Sign Up",actualTitle);
    }

    @Test
    public void validSignUpTest(){
        SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);

        signUpPage.setEmail(variables.invalidEmail);

        signUpPage.setPassword(variables.validPassword);

        signUpPage.clickSignUpBtn();

        Assert.assertEquals(variables.URL,driver.getCurrentUrl());
    }

    @Test
    public void invalidSignUpTest(){
        SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);

        signUpPage.setEmail(variables.invalidEmail);

        signUpPage.setPassword(variables.validPassword);

        signUpPage.clickSignUpBtn();

        Assert.assertEquals(variables.signUpURL,driver.getCurrentUrl());
    }

    @Test
    public void clickSignIn(){
        SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);
        signUpPage.clickSignInBtn();
        Assert.assertEquals(variables.signInURL,driver.getCurrentUrl());
    }
}
