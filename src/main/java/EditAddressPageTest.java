package main.java;

import main.Classes.EditAddressPage;
import main.Classes.NewAddressPage;
import main.Classes.SignInPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class EditAddressPageTest extends ChromeDriverInit {
    private ChromeDriver driver;
    final private String validUsername = "anwartrue@gmail.com";
    final private String validPassword = "root1234";

    final private String invalidFirstName = "root1234";
    final private String invalidSecondName = "qarout232";
    final private String invalidZipCode = "12345sad";
    final private String invalidAddress = "";
    final private String invalidCity = "";

    final private String validFirstName = "anwar";
    final private String validSecondName = "qarout";
    final private String validZipCode = "12345";
    final private String validAddress = "Ramallah";
    final private String validCity = "Ramallah";

    final private String URL = "http://a.testaddressbook.com/";
    final private String addressURL = "http://a.testaddressbook.com/addresses";
    final private String newAddressURL = "http://a.testaddressbook.com/addresses/new";

    public static String editingURL;

    public EditAddressPageTest(){

    }

    public void SignIn(ChromeDriver driver){
        SignInPage signInPage = PageFactory.initElements(driver,SignInPage.class);
        signInPage.setEmail(validUsername);

        signInPage.setPassword(validPassword);

        signInPage.clickSignInBtn();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get("http://a.testaddressbook.com/sign_in");
        SignIn(driver);
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }


    @BeforeTest
    public void beforeTest() {
        driver.get(newAddressURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        NewAddressPage newAddressPage = PageFactory.initElements(driver, NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(validFirstName,validSecondName,validAddress,validCity,validZipCode);
        newAddressPage.clickSubmitBtn();
        editingURL =
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get(newAddressURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


}
