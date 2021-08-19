package main.java;

import main.Classes.LoggedInPage;
import main.Classes.SignInPage;
import main.resources.variables;
import main.java.ChromeDriverInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LoggedInPageTest extends ChromeDriverInit {
    private ChromeDriver driver;


    public LoggedInPageTest(){

    }



    public void SignIn(ChromeDriver driver){
        SignInPage signInPage = PageFactory.initElements(driver,SignInPage.class);
        signInPage.setEmail(variables.validUsername);

        signInPage.setPassword(variables.validPassword);

        signInPage.clickSignInBtn();
    }

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get(variables.signInURL);
        SignIn(driver);
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }



    @BeforeMethod
    public void beforeMethod(){
        driver.get(variables.URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    }


    @Test
    public void validateMainContents(){
        LoggedInPage loggedInPage = PageFactory.initElements(driver,LoggedInPage.class);

        Assert.assertFalse(driver.findElements(By.xpath(variables.homeCurrentSpanXPath)).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),variables.URL);


        String expectedMainHeader = "Welcome to Address Book";
        Assert.assertEquals(expectedMainHeader, loggedInPage.getMainHeader());

        String expectedSubHeader = "A simple web app for showing off your testing";
        Assert.assertEquals(expectedSubHeader, loggedInPage.getSubHeader());

        Assert.assertFalse(driver.findElements(By.xpath(variables.signOutTextXPath)).isEmpty());
        Assert.assertFalse(driver.findElements(By.xpath(variables.addressTextXPath)).isEmpty());

        Assert.assertFalse(driver.findElements(By.xpath(variables.XPathByValidUserName)).isEmpty());
    }

    @Test
    public void addressBtn(){
        LoggedInPage loggedInPage = PageFactory.initElements(driver,LoggedInPage.class);
        loggedInPage.clickAddressBtn();
        Assert.assertEquals(driver.getCurrentUrl(),variables.addressURL);
        Assert.assertFalse(driver.findElements(By.xpath(variables.addressCurrentSpanXPath)).isEmpty());
    }

    @Test
    public void signOut(){
        LoggedInPage loggedInPage = PageFactory.initElements(driver,LoggedInPage.class);
        loggedInPage.clickSignOutBtn();
        Assert.assertEquals(driver.getCurrentUrl(),variables.signInURL);
    }


}

