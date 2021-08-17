package main.java;


import main.Classes.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MainPageTest extends ChromeDriverInit{
    public static ChromeDriver driver;

    public MainPageTest() {
    }

   /* @BeforeSuite
    public void beforeSuite(){
        driver = ChromeDriverInit();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }*/

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.get("http://a.testaddressbook.com/");
        driver.manage().window().maximize();
    }

  /* @AfterClass
    public void afterClass(){
        driver.close();
    }*/



    @Test
    public void testMainPageTitle() {
        MainPage mainPage = PageFactory.initElements(driver,MainPage.class);
        String actualTitle = driver.getTitle();
        String expectedTitle = "Address Book";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testMainPageHeaders(){
        MainPage mainPage = PageFactory.initElements(driver,MainPage.class);
        String expectedMainHeader = "Welcome to Address Book";
        Assert.assertEquals(expectedMainHeader, mainPage.getMainHeader());

        String expectedSubHeader = "A simple web app for showing off your testing";
        Assert.assertEquals(expectedSubHeader, mainPage.getSubHeader());
    }
    @Test
    public void clickHomeButton(){
        MainPage mainPage = PageFactory.initElements(driver,MainPage.class);
        mainPage.clickHomeButton();
        testMainPageTitle();
        testMainPageHeaders();
    }

    @Test
   public void clickLoginButton(){
        MainPage mainPage = PageFactory.initElements(driver,MainPage.class);
        mainPage.clickSignInButton();
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,"Address Book - Sign In");
        Assert.assertFalse(driver.findElements(By.xpath("//a[@data-test='sign-in']//span[@class='sr-only']")).isEmpty());
    }
}
