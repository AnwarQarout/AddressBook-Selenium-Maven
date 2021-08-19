package main.java;

import main.Classes.AddressPage;
import main.Classes.NewAddressPage;
import main.Classes.SignInPage;
import org.openqa.selenium.By;
import main.resources.variables;
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

public class AddressPageTest extends ChromeDriverInit {

    private ChromeDriver driver;

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
        driver.get(variables.signInURL);
        SignIn(driver);

        driver.get(variables.newAddressURL);
        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(variables.validFirstName,variables.validSecondName,variables.validAddress,variables.validCity,variables.validZipCode);
        newAddressPage.clickSubmitBtn();

    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }


    @BeforeMethod
    public void beforeMethod() {
        driver.get(variables.addressURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @Test
    public void testNewAddressBtn(){
        AddressPage addressPage = PageFactory.initElements(driver,AddressPage.class);
        addressPage.clickNewAddressBtn();
        Assert.assertEquals(driver.getCurrentUrl(),variables.newAddressURL);
    }

    @Test
    public void destroyAddress(){
        AddressPage addressPage = PageFactory.initElements(driver,AddressPage.class);

        String text = addressPage.getFirstNameOfFirstAddress();

        addressPage.clickDestroyButton();
        driver.switchTo().alert().accept();

        Assert.assertTrue(driver.findElements(By.xpath("//table//tbody//tr//td[text()='"+text+"']")).isEmpty());
    }

    @Test
    public void showAddress(){
        AddressPage addressPage = PageFactory.initElements(driver,AddressPage.class);

        String text = addressPage.getFirstNameOfFirstAddress();

        addressPage.clickShowButton();

        Assert.assertFalse(driver.findElements(By.xpath("//table//tbody//tr//td[text()='"+text+"']")).isEmpty());
    }

    @Test
    public void editAddress(){
        AddressPage addressPage = PageFactory.initElements(driver,AddressPage.class);

        String text = addressPage.getFirstNameOfFirstAddress();

        addressPage.clickEditButton();

        Assert.assertFalse(driver.findElements(By.xpath(variables.editingAddressXPath)).isEmpty());

    }

}
