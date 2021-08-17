package main.Classes;

import main.java.ChromeDriverInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewAddressPage {
    ChromeDriver driver;

    @FindBy(how = How.XPATH,using = "//input[@type='submit']")
    WebElement submitBtn;

    @FindBy(how = How.XPATH,using = "//input[@id=\"address_first_name\"]")
    WebElement firstName;

    @FindBy(how = How.XPATH,using = "//input[@id=\"address_last_name\"]")
    WebElement lastName;

    @FindBy(how = How.XPATH,using = "//input[@id=\"address_street_address\"]")
    WebElement address1;

    @FindBy(how = How.XPATH,using = "//input[@id='address_city']")
    WebElement city;

    @FindBy(how = How.XPATH,using = "//input[@id=\"address_zip_code\"]")
    WebElement zipCode;

    @FindBy(how = How.XPATH,using = "//input[@id='address_age']")
    WebElement age;

    @FindBy(how = How.XPATH,using = "//input[@type='date']")
    WebElement birthday;

    @FindBy(how = How.XPATH,using = "//a[@data-test='list'][text()='List']")
    WebElement listBtn;


    public NewAddressPage(){
    }

    public NewAddressPage(ChromeDriver driver){
        this.driver = driver;
    }

    public void clickListBtn(){
        listBtn.click();
    }

    public void setAge(String text){
        age.sendKeys(text);
    }

    public void setBirthday(String text){
        birthday.sendKeys(text);
    }

    public void clickSubmitBtn(){
        submitBtn.click();
    }

    public void setFirstName(String text){
        firstName.sendKeys(text);
    }

    public void setLastName(String text){
        lastName.sendKeys(text);
    }

    public void setAddress1(String text){
        address1.sendKeys(text);
    }

    public void setCity(String text){
        city.sendKeys(text);
    }

    public void setZipCode(String text){
        zipCode.sendKeys(text);
    }

    public void createValidAddressWithArgs(String name1, String name2, String addr, String City, String zip) {

        firstName.click();
        firstName.sendKeys(name1);

        lastName.click();
        lastName.sendKeys(name2);

        address1.click();
        address1.sendKeys(addr);

        city.click();
        city.sendKeys(City);

        zipCode.click();
        zipCode.sendKeys(zip);
    }
}
