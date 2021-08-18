package main.Classes;

import main.java.EditAddressPageTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EditAddressPage {
    ChromeDriver driver;

    @FindBy(how = How.XPATH,using = "//a[contains(@data-test,'edit')][text()='Edit']")
    WebElement editBtn;

    @FindBy(how = How.XPATH,using = "//input[@type='submit']")
    WebElement updateBtn;

    @FindBy(how = How.XPATH,using = "//a[text()='List']")
    WebElement listBtn;




    public EditAddressPage(){

    }

    public EditAddressPage(ChromeDriver driver){
        this.driver = driver;
    }

    public void clickEditBtn(){
        editBtn.click();
    }

    public void clickUpdateBtn(){
        updateBtn.click();
    }

    public void clickListBtn(){
        listBtn.click();
    }



}
