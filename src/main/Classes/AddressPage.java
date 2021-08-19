package main.Classes;

import main.resources.variables;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddressPage {
    ChromeDriver driver;

    @FindBy(how = How.XPATH,using = variables.newAddressBtn)
    WebElement newAddressBtn;

    @FindBy(how = How.XPATH,using = variables.firstNameOfFirstAddress)
    WebElement firstNameOfFirstAddress;

    @FindBy(how = How.XPATH,using = variables.destroyBtn)
    WebElement destroyBtn;

    @FindBy(how = How.XPATH,using = variables.editingBtn)
    WebElement editBtn;

    @FindBy(how = How.XPATH,using = variables.showBtn)
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
