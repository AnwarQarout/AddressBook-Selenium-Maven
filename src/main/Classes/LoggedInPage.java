package main.Classes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoggedInPage {
    ChromeDriver driver;

    @FindBy(how = How.XPATH,using = "//div[@class='text-center']//child::h1")
    WebElement mainHeader;

    @FindBy(how = How.XPATH,using = "//div[@class='text-center']//descendant::h4")
    WebElement subHeader;

    @FindBy(how = How.XPATH,using = "//a[text()='Addresses']")
    WebElement addressBtn;

    @FindBy(how = How.XPATH,using = "//a[text()='Sign out']")
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
