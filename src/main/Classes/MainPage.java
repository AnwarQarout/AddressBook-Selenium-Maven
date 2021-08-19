package main.Classes;

import main.java.MainPageTest;
import main.resources.variables;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    ChromeDriver driver;

    @FindBy(how = How.XPATH, using = variables.mainHeader)
    WebElement mainHeader;

    @FindBy(how=How.XPATH,using = variables.subHeader)
    WebElement subHeader;

    @FindBy(how = How.XPATH,using = variables.homeButton)
    WebElement homeButton;

    @FindBy(how = How.XPATH,using = variables.signInBt)
    WebElement signInButton;

    public MainPage(){

    }

    public MainPage(ChromeDriver driver){
        this.driver = driver;
    }



    public String getMainHeader() {
        return mainHeader.getText();
    }

    public String getSubHeader() {
        return subHeader.getText();
    }


    public void clickHomeButton() {
        this.homeButton.click();
    }

    public void clickSignInButton() {
        this.homeButton.click();
    }

}
