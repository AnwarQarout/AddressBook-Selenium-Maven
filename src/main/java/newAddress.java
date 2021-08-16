package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class newAddress extends ChromeDriverInit {

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


    public void SignIn(ChromeDriver driver) {
        WebElement usernameField = driver.findElement(By.xpath("//input[@type='email'][@placeholder='Email']"));
        usernameField.sendKeys(validUsername);

        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password'][@type='password']"));
        passwordField.sendKeys(validPassword);

        WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit'][@value='Sign in']"));
        submitBtn.click();

    }

    public void createValidAddressWithArgs(String name1, String name2, String addr, String City, String zip) {
        //System.out.println(flag);
        driver.get(newAddressURL);
        WebElement firstName = driver.findElement(By.xpath("//input[@id=\"address_first_name\"]"));
        firstName.click();
        firstName.sendKeys(name1);

        WebElement lastName = driver.findElement(By.xpath("//input[@id=\"address_last_name\"]"));
        lastName.click();
        lastName.sendKeys(name2);

        WebElement address1 = driver.findElement(By.xpath("//input[@id=\"address_street_address\"]"));
        address1.click();
        address1.sendKeys(addr);

        WebElement city = driver.findElement(By.xpath("//input[@id='address_city']"));
        city.click();
        city.sendKeys(City);

        WebElement zipCode = driver.findElement(By.xpath("//input[@id=\"address_zip_code\"]"));
        zipCode.click();
        zipCode.sendKeys(zip);


      /*  WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        submitBtn.click();

        Assert.assertNotEquals(driver.getCurrentUrl(),newAddressURL); //new
        Assert.assertFalse(driver.findElements(By.xpath("//div[contains(@class,'alert')][text()='Address was successfully created.']")).isEmpty());*/
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
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


}
