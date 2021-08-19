package main.java;
import main.resources.variables;
import main.Classes.NewAddressPage;
import main.Classes.SignInPage;
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

public class NewAddressPageTest extends ChromeDriverInit {
    private ChromeDriver driver;

    final private String URL = "http://a.testaddressbook.com/";
    final private String addressURL = "http://a.testaddressbook.com/addresses";
    final private String newAddressURL = "http://a.testaddressbook.com/addresses/new";


    public NewAddressPageTest(){

    }

    public void SignIn(ChromeDriver driver){
        SignInPage signInPage = PageFactory.initElements(driver,SignInPage.class);
        signInPage.setEmail(variables.validUsername);

        signInPage.setPassword(variables.validPassword);

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


    @BeforeMethod
    public void beforeMethod() {
        driver.get(newAddressURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void createEmptyAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.clickSubmitBtn();
        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),newAddressURL); //new
    }

    @Test
    public void invalidFirstNameAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(variables.invalidFirstName,variables.validSecondName,variables.validAddress,variables.validCity,variables.validZipCode);

       newAddressPage.clickSubmitBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),newAddressURL); //new
    }

    @Test
    public void createInvalidLastNameAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(variables.validFirstName,variables.invalidSecondName,variables.validAddress,variables.validCity,variables.validZipCode);

        newAddressPage.clickSubmitBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),newAddressURL); //new
    }

    @Test
    public void createInvalidZipCodeAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(variables.validFirstName,variables.validSecondName,variables.validAddress,variables.validCity,variables.invalidZipCode);

        newAddressPage.clickSubmitBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),newAddressURL); //new

    }

    @Test
    public void createInvalidAgeAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        int randChoice = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        int randomNum;
        if(randChoice ==0) {
            randomNum = ThreadLocalRandom.current().nextInt(-20, 0 + 1);
        }
        else{
            randomNum = ThreadLocalRandom.current().nextInt(120, 1000 + 1);
        }
        newAddressPage.createValidAddressWithArgs(variables.validFirstName,variables.validSecondName,variables.validAddress,variables.validCity,variables.validZipCode);

        newAddressPage.setAge(Integer.toString(randomNum));

        newAddressPage.clickSubmitBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),newAddressURL); //new
    }

    @Test
    public void createInvalidBirthdayAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);

        /* Select Random Date */
        LocalDate date;
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);

        /* select randomly between two cases: either a very old, impossible birthdate, or a future birthdate. Both are invalid. */
        if(randomNum == 0) {
            date = LocalDate.of((int) ThreadLocalRandom.current().nextInt(0, 1900 + 1), (int) ThreadLocalRandom.current().nextInt(1, 12 + 1), (int) ThreadLocalRandom.current().nextInt(1, 31 + 1));
        }
        else {
            date = LocalDate.of((int) ThreadLocalRandom.current().nextInt(2021, 3000 + 1), (int) ThreadLocalRandom.current().nextInt(1, 12 + 1), (int) ThreadLocalRandom.current().nextInt(1, 31 + 1));
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
        date.format(formatter);
        newAddressPage.createValidAddressWithArgs(variables.validFirstName,variables.validSecondName,variables.validAddress,variables.validCity,variables.validZipCode);

        newAddressPage.setBirthday(date.toString());

        newAddressPage.clickSubmitBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),newAddressURL); //new

    }

    @Test
    public void createValidAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(variables.validFirstName,variables.validSecondName,variables.validAddress,variables.validCity,variables.validZipCode);

        newAddressPage.clickSubmitBtn();

        Assert.assertNotEquals(driver.getCurrentUrl(),newAddressURL); //new
        Assert.assertFalse(driver.findElements(By.xpath("//div[contains(@class,'alert')][text()='Address was successfully created.']")).isEmpty());
    }

    @Test
    public void listInvalidAddressBeforeCreation() {
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);

        newAddressPage.clickListBtn();

        Assert.assertEquals(driver.getCurrentUrl(), newAddressURL);
    }

    @Test
    public void listValidAddressBeforeCreation() {
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(variables.validFirstName,variables.validSecondName,variables.validAddress,variables.validCity,variables.validZipCode);

        newAddressPage.clickListBtn();

        Assert.assertEquals(driver.getCurrentUrl(), addressURL);
        Assert.assertFalse(driver.findElements(By.xpath("//tr//td[text()='" + variables.validFirstName + "']")).isEmpty());
    }
}
