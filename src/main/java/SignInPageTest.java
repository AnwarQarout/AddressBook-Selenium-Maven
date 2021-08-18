package main.java;

import main.Classes.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SignInPageTest extends ChromeDriverInit {
    final private static String badPassword = "badPassword123";
    final private static String badUsername = "badUsername123@gmail.com";
    final private static String invalidEmail = "badusername";
    final private  String validUsername = "anwarTrue@gmail.com";
    final  private String validPassword = "root1234";
    final private String URL = "http://a.testaddressbook.com/sign_in";
     private ChromeDriver driver;

    public SignInPageTest(){

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
    public void testSignInPageTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Address Book - Sign In",actualTitle);
    }

    @Test
    public void testSignInPageElementsExist(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        SignInPage signInPage = PageFactory.initElements(driver,SignInPage.class);
        String actualText = signInPage.getSignInHeader();

        //assert that every element exists
        Assert.assertEquals(actualText,"Sign in");
        Assert.assertFalse(driver.findElements(By.xpath("//input[@type='email'][@placeholder='Email']")).isEmpty());
        Assert.assertFalse(driver.findElements(By.xpath("//input[@placeholder='Password'][@type='password']")).isEmpty());
        Assert.assertFalse(driver.findElements(By.xpath("//input[@type='submit'][@value='Sign in']")).isEmpty());
        Assert.assertFalse(driver.findElements(By.xpath("//a[@href='/sign_up'][text()='Sign up']")).isEmpty());
    }

    /* Method to enter empty credentials, or empty username */
    @Test
    public void testBadCredentialsInForm(){
        SignInPage signInPage = PageFactory.initElements(driver,SignInPage.class);
        //submit without entering anything
        signInPage.clickSignInBtn();
        Assert.assertFalse(driver.findElements(By.xpath("//div[contains(@class,'alert')][text()='Bad email or password.']")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),URL);

        //only enter password
        driver.get(URL);

        signInPage.setPassword(badPassword);

        signInPage.clickSignInBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//div[contains(@class,'alert')][text()='Bad email or password.']")).isEmpty());

        //enter invalid username and password
        driver.get(URL);

        signInPage.setEmail(badUsername);

        signInPage.setEmail(badPassword);

        signInPage.clickSignInBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//div[contains(@class,'alert')][text()='Bad email or password.']")).isEmpty());

        // Only enter username
        driver.get(URL);
        signInPage.setEmail(validUsername);

        signInPage.clickSignInBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//div[contains(@class,'alert')][text()='Bad email or password.']")).isEmpty());

        // enter invalid username with no @ in it
        driver.get(URL);
        signInPage.setEmail(invalidEmail);

        signInPage.clickSignInBtn();

        Assert.assertEquals(URL,driver.getCurrentUrl());
    }

    @Test
    public void signIn(){
        SignInPage signInPage = PageFactory.initElements(driver,SignInPage.class);
        signInPage.setEmail(validUsername);

        signInPage.setPassword(validPassword);

        signInPage.clickSignInBtn();

        Assert.assertEquals("http://a.testaddressbook.com/",driver.getCurrentUrl());
    }

    @Test
    public void clickSignUpButton(){
        SignInPage signInPage = PageFactory.initElements(driver,SignInPage.class);
        signInPage.clickSignUpBtn();
        Assert.assertEquals("http://a.testaddressbook.com/sign_up",driver.getCurrentUrl());
    }
}
