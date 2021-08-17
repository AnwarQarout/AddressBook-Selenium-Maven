package main.java;

import main.Classes.SignInPage;
import main.Classes.SignUpPage;
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
    final private static String badPassword = "badPassword123";
    final private static String badUsername = "badUsername123@gmail.com";
    final private static String invalidEmail = "badusername";
    final private  String validUsername = "anwarTrue@gmail.com";
    final  private String validPassword = "root1234";
    final private String URL = "http://a.testaddressbook.com/sign_up";
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
        driver.get(URL);
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

        signUpPage.setEmail(invalidEmail);

        signUpPage.setPassword(validPassword);

        signUpPage.clickSignUpBtn();

        Assert.assertEquals("http://a.testaddressbook.com",driver.getCurrentUrl());
    }

    @Test
    public void invalidSignUpTest(){
        SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);

        signUpPage.setEmail(invalidEmail);

        signUpPage.setPassword(validPassword);

        signUpPage.clickSignUpBtn();

        Assert.assertEquals("http://a.testaddressbook.com/sign_up",driver.getCurrentUrl());
    }

    @Test
    public void clickSignIn(){
        SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);
        signUpPage.clickSignInBtn();
        Assert.assertEquals("http://a.testaddressbook.com/sign_in",driver.getCurrentUrl());
    }
}
