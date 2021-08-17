package main.Classes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddressPage {
    ChromeDriver driver;

    @FindBy(how = How.XPATH,using = "//a[contains(@class,'row')][@data-test='create']")
    WebElement newAddressBtn;

    @FindBy(how = How.XPATH,using = "//table//tbody//tr//td")
    WebElement firstNameOfFirstAddress;

    @FindBy(how = How.XPATH,using = "//table//tbody//tr//td//a[@data-method='delete']")
    WebElement destroyBtn;

    @FindBy(how = How.XPATH,using = "//table//tbody//tr//td//a[contains(@data-test,'edit')]")
    WebElement editBtn;

    @FindBy(how = How.XPATH,using = "//table//tbody//tr//td//a[contains(@data-test,'show')]")
    WebElement showBtn;

    public AddressPage(){

    }

    public AddressPage(ChromeDriver driver){
        this.driver = driver;
    }

    public void clickNewAddressBtn(){
        newAddressBtn.click();
    }

    public String getFirstNameOfFirstAddress(){
        return firstNameOfFirstAddress.getText();
    }

    public void clickDestroyButton(){
        destroyBtn.click();
    }

    public void clickShowButton(){
        showBtn.click();
    }

    public void clickEditButton(){
        editBtn.click();
    }
}
