package main.Classes;

import main.resources.variables;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoggedInPage {
    ChromeDriver driver;

    @FindBy(how = How.XPATH,using = variables.mainHeader)
    WebElement mainHeader;

    @FindBy(how = How.XPATH,using = variables.subHeader)
    WebElement subHeader;

    @FindBy(how = How.XPATH,using = variables.address)
    WebElement addressBtn;

    @FindBy(how = How.XPATH,using = variables.signOutBtn)
    WebElement signOutBtn;

    public LoggedInPage(){

    }

    public LoggedInPage(ChromeDriver driver){
        this.driver = driver;
    }

    public String getMainHeader(){
        return mainHeader.getText();
    }

    public String getSubHeader(){
        return subHeader.getText();
    }

    public void clickAddressBtn(){
        addressBtn.click();
    }

    public void clickSignOutBtn(){
        signOutBtn.click();
    }


}
