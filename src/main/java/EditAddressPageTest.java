package main.java;

import main.Classes.AddressPage;
import main.Classes.EditAddressPage;
import main.Classes.NewAddressPage;
import main.Classes.SignInPage;
import main.resources.variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

public class EditAddressPageTest extends ChromeDriverInit {
    private ChromeDriver driver;
    

    public static String editingURL;

    public EditAddressPageTest(){

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
    public void beforeMethod(){
        driver.get(variables.newAddressURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        NewAddressPage newAddressPage = PageFactory.initElements(driver, NewAddressPage.class);
        newAddressPage.createValidAddressWithArgs(variables.validFirstName,variables.validSecondName,variables.validAddress,variables.validCity,variables.validZipCode);
        newAddressPage.clickSubmitBtn();
    }

    @Test
    public void editAddressCorrectlyAfterCreation(){
        EditAddressPage editAddressPage = PageFactory.initElements(driver,EditAddressPage.class);
        editAddressPage.clickEditBtn();
        Assert.assertFalse(driver.findElements(By.xpath("//h2[text()='Editing Address']")).isEmpty());
        editAddressPage.clickUpdateBtn();
        Assert.assertFalse(driver.findElements(By.xpath("//div[contains(@class,'alert')][text()='Address was successfully updated.']")).isEmpty());
    }

    @Test
    public void editAddressIncorrectlyAfterCreation(){
        EditAddressPage editAddressPage = PageFactory.initElements(driver,EditAddressPage.class);
        editAddressPage.clickEditBtn();
        Assert.assertFalse(driver.findElements(By.xpath("//h2[text()='Editing Address']")).isEmpty());

        String beforeEdit = driver.getCurrentUrl();

        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.setFirstName(variables.invalidFirstName);

        newAddressPage.clearLastName();
        newAddressPage.setLastName(" ");


       editAddressPage.clickUpdateBtn();

        String afterEdit = driver.getCurrentUrl();

        Assert.assertFalse(driver.findElements(By.xpath("//ul")).isEmpty());
        Assert.assertEquals(beforeEdit,afterEdit);
    }

    @Test
    public void editAddressCorrectlyAtMainPage(){
        driver.get("http://a.testaddressbook.com/addresses");

        EditAddressPage editAddressPage = PageFactory.initElements(driver,EditAddressPage.class);
        editAddressPage.clickEditBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//h2[text()='Editing Address']")).isEmpty());

        editAddressPage.clickUpdateBtn();
        Assert.assertFalse(driver.findElements(By.xpath("//div[contains(@class,'alert')][text()='Address was successfully updated.']")).isEmpty());
    }

    @Test
    public void listAddressAtMainPage(){
        driver.get("http://a.testaddressbook.com/addresses");
        AddressPage addressPage = PageFactory.initElements(driver,AddressPage.class);
        String beforeUpdate = addressPage.getFirstNameOfFirstAddress();
        EditAddressPage editAddressPage = PageFactory.initElements(driver,EditAddressPage.class);
        editAddressPage.clickEditBtn();

        Assert.assertFalse(driver.findElements(By.xpath("//h2[text()='Editing Address']")).isEmpty());

        NewAddressPage newAddressPage = PageFactory.initElements(driver,NewAddressPage.class);
        newAddressPage.setFirstName("-concat-test");

        editAddressPage.clickListBtn();

        Assert.assertEquals(addressPage.getFirstNameOfFirstAddress(),beforeUpdate.concat("-concat-test"));
    }

}
