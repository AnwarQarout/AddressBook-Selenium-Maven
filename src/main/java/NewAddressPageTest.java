package main.java;

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


    public NewAddressPageTest(){

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
    public void createInvalidFirstNameAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(invalidFirstName,validSecondName,validAddress,validCity,validZipCode);

       newAddressPage.clickSubmitBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),newAddressURL); //new
    }

    @Test
    public void createInvalidLastNameAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(validFirstName,invalidSecondName,validAddress,validCity,validZipCode);

        newAddressPage.clickSubmitBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),newAddressURL); //new
    }

    @Test
    public void createInvalidZipCodeAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(validFirstName,validSecondName,validAddress,validCity,invalidZipCode);

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
        newAddressPage.createValidAddressWithArgs(validFirstName,validSecondName,validAddress,validCity,validZipCode);

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
        newAddressPage.createValidAddressWithArgs(validFirstName,validSecondName,validAddress,validCity,validZipCode);

        newAddressPage.setBirthday(date.toString());

        newAddressPage.clickSubmitBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(),newAddressURL); //new

    }

    @Test
    public void createValidAddress(){
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(validFirstName,validSecondName,validAddress,validCity,validZipCode);

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
        newAddressPage.createValidAddressWithArgs(validFirstName,validSecondName,validAddress,validCity,validZipCode);

        newAddressPage.clickListBtn();

        Assert.assertEquals(driver.getCurrentUrl(), addressURL);
        Assert.assertFalse(driver.findElements(By.xpath("//tr//td[text()='" + validFirstName + "']")).isEmpty());
    }
    /*
    @Test
    public void editAddressCorrectlyAfterCreation(){
        createValidAddress();
        WebElement edit = driver.findElement(By.xpath("//a[@data-test='edit'][text()='Edit']"));
        edit.click();
        Assert.assertFalse(driver.findElements(By.xpath("//h2[text()='Editing Address']")).isEmpty());
        WebElement updateBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        updateBtn.click();
        Assert.assertFalse(driver.findElements(By.xpath("//div[contains(@class,'alert')][text()='Address was successfully updated.']")).isEmpty());
    }

    @Test
    public void editAddressIncorrectlyAfterCreation(){
        createValidAddress();
        WebElement edit = driver.findElement(By.xpath("//a[@data-test='edit'][text()='Edit']"));
        edit.click();
        Assert.assertFalse(driver.findElements(By.xpath("//h2[text()='Editing Address']")).isEmpty());

        String beforeEdit = driver.getCurrentUrl();

        WebElement firstName = driver.findElement(By.xpath("//input[@id=\"address_first_name\"]"));
        firstName.click();
        firstName.sendKeys(invalidFirstName);

        WebElement lastName = driver.findElement(By.xpath("//input[@id=\"address_last_name\"]"));
        firstName.click();
        firstName.clear();
        firstName.sendKeys(" ");


        WebElement updateBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        updateBtn.click();

        String afterEdit = driver.getCurrentUrl();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(beforeEdit,afterEdit);
    }

    @Test
    public void listAddressAfterCreation(){
        createValidAddressWithArgs(validFirstName,validSecondName,validAddress,validCity,validZipCode);
        WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        submitBtn.click();

        WebElement list = driver.findElement(By.xpath("//a[@data-test='list'][text()='List']"));
        list.click();

        Assert.assertEquals(driver.getCurrentUrl(),addressURL);
        Assert.assertFalse(driver.findElements(By.xpath("//tr//td[text()='"+validFirstName+"']")).isEmpty());
    }


    }*/
}
