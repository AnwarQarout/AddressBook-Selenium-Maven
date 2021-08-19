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

    final private String invalidSecondName = "qarout232";
    final private String invalidZipCode = "12345sad";
    final private String invalidAddress = "";
    final private String invalidCity = "";

    final  private String validFirstName = "anwar";
    final private String validSecondName = "qarout";
    final private String validZipCode = "12345";
    final private String validAddress = "Ramallah";
    final private String validCity = "Ramallah";

    final private String URL = "http://a.testaddressbook.com/";
    final private String addressURL = "http://a.testaddressbook.com/addresses";
    final private String newAddressURL = "http://a.testaddressbook.com/addresses/new";





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
        driver.get("http://a.testaddressbook.com/sign_in");
        SignIn(driver);
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }



    @BeforeMethod
    public void beforeMethod(){
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    }


    @Test
    public void validateMainContents(){
        LoggedInPage loggedInPage = PageFactory.initElements(driver,LoggedInPage.class);

        Assert.assertFalse(driver.findElements(By.xpath("//a[@data-test='home']//span[@class='sr-only']")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),URL);


        String expectedMainHeader = "Welcome to Address Book";
        Assert.assertEquals(expectedMainHeader, loggedInPage.getMainHeader());

        String expectedSubHeader = "A simple web app for showing off your testing";
        Assert.assertEquals(expectedSubHeader, loggedInPage.getSubHeader());

        Assert.assertFalse(driver.findElements(By.xpath("//a[text()='Sign out']")).isEmpty());
        Assert.assertFalse(driver.findElements(By.xpath("//a[text()='Addresses']")).isEmpty());

        Assert.assertFalse(driver.findElements(By.xpath("//span[@class='navbar-text'][text()='"+variables.validUsername+"']")).isEmpty());
    }

    @Test
    public void addressBtn(){
        LoggedInPage loggedInPage = PageFactory.initElements(driver,LoggedInPage.class);
        loggedInPage.clickAddressBtn();
        Assert.assertEquals(driver.getCurrentUrl(),addressURL);
        Assert.assertFalse(driver.findElements(By.xpath("//a[@data-test='addresses']//span[@class='sr-only']")).isEmpty());
    }

    @Test
    public void signOut(){
        LoggedInPage loggedInPage = PageFactory.initElements(driver,LoggedInPage.class);
        loggedInPage.clickSignOutBtn();
        Assert.assertEquals(driver.getCurrentUrl(),"http://a.testaddressbook.com/sign_in");
    }


}

