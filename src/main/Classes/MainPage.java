package main.Classes;

import main.java.MainPageTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    ChromeDriver driver;

    @FindBy(how = How.XPATH, using = "// div[@class='text-center']//child::h1")
    WebElement mainHeader;

    @FindBy(how=How.XPATH,using = "// div[@class='text-center']//descendant::h4")
    WebElement subHeader;

    @FindBy(how = How.XPATH,using = "//a[contains(@class,'nav')][@data-test='home']")
    WebElement homeButton;

    @FindBy(how = How.XPATH,using = "//a[@id='sign-in'][text()='Sign in']")
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
