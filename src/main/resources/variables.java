package main.resources;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class variables {
    final public static String URL = "http://a.testaddressbook.com/";
    final public static String signInURL = "http://a.testaddressbook.com/sign_in";
    final public static String signUpURL = "http://a.testaddressbook.com/sign_up";
    final public static String addressURL = "http://a.testaddressbook.com/addresses";
    final public static String newAddressURL = "http://a.testaddressbook.com/addresses/new";

    final public static String signInCurrentSpanXPath = "//a[@data-test='sign-in']//span[@class='sr-only']";
    final public static String homeCurrentSpanXPath = "//a[@data-test='home']//span[@class='sr-only']";
    final public static String ulXPath = "//ul";
    final public static String XPathByValidUserName = "//span[@class='navbar-text'][text()='"+variables.validUsername+"']";
    final public static String addressCurrentSpanXPath = "//a[@data-test='addresses']//span[@class='sr-only']";
    final public static String addressSuccessfullyCreatedXPath = "//div[contains(@class,'alert')][text()='Address was successfully created.']";
    final public static String emailXPath = "//input[@type='email'][@placeholder='Email']";
    final public static String passwordXPath = "//input[@placeholder='Password'][@type='password']";
    final public static String signInXPath = "//input[@type='submit'][@value='Sign in']";
    final public static String signUpXPath = "//a[@href='/sign_up'][text()='Sign up']";
    final public static String firstNameExistInAddressXPath = "//tr//td[text()='" + variables.validFirstName + "']";
    final public static String editingAddressXPath = "//h2[text()='Editing Address']";
    final public static String updatedXPath = "//div[contains(@class,'alert')][text()='Address was successfully updated.']";

    final public static String signOutTextXPath = "//a[text()='Sign out']";
    final public static String addressTextXPath = "//a[text()='Addresses']";

    final public static String badEmailOrPasswordXPath = "//div[contains(@class,'alert')][text()='Bad email or password.']";
    final public static String validUsername = "anwartrue@gmail.com";
    final public static String validPassword = "root1234";

    final public static String signUpHeader = "//div[@id='clearance']//child::h2";
    final public static String email = "//input[@type='email'][@placeholder='Email']";
    final public static String password = "//input[@placeholder='Password'][@type='password']";
    final public static String signUpBtn = "//input[@type='submit'][@value='Sign up']";
    final public static String signInBtn = "//a[@href='/sign_in'][text()='Sign in']";

    final public static String signInHeader = "//div[@id='clearance']//child::h2";
    final public static String signInEmail = "//input[@type='email'][@placeholder='Email']";
    final public static String signInPassword = "//input[@placeholder='Password'][@type='password']";
    final public static String signInButton = "//input[@type='submit'][@value='Sign in']";
    final public static String signUpButton = "//a[@href='/sign_up'][text()='Sign up']";

    final public static String submitBtn = "//input[@type='submit']";
    final public static String addressFirstName = "//input[@id=\"address_first_name\"]";
    final public static String addressLastName = "//input[@id=\"address_last_name\"]";
    final public static String addressAdress = "//input[@id=\"address_street_address\"]";
    final public static String addressCity = "//input[@id='address_city']";
    final public static String addressZipCode = "//input[@id=\"address_zip_code\"]";
    final public static String addressAge = "//input[@id='address_age']";
    final public static String addressBirthday = "//input[@type='date']";
    final public static String addressListBtn = "//a[@data-test='list'][text()='List']";

    final public static String mainHeader = "// div[@class='text-center']//child::h1";
    final public static String subHeader = "// div[@class='text-center']//descendant::h4";
    final public static String homeButton = "//a[contains(@class,'nav')][@data-test='home']";
    final public static String signInBt = "//a[@id='sign-in'][text()='Sign in']";
    final public static String address = "//a[text()='Addresses']";
    final public static String signOutBtn = "//a[text()='Sign out']";

    final public static String editBtn = "//a[contains(@data-test,'edit')][text()='Edit']";
    final public static String updateBtn = "//input[@type='submit']";
    final public static String listBtn = "//a[text()='List']";

    final public static String invalidFirstName = "root1234";
    final public static String invalidSecondName = "qarout232";
    final public static String invalidZipCode = "12345sad";
    final public static String invalidAddress = "";
    final public static String invalidCity = "";

    final public static String newAddressBtn = "//a[contains(@class,'row')][@data-test='create']";
    final public static String firstNameOfFirstAddress = "//table//tbody//tr//td";
    final public static String badPassword = "badPassword123";
    final public static String badUsername = "badUsername123@gmail.com";
    final public static String invalidEmail = "badusername";
    final public static String destroyBtn = "//table//tbody//tr//td//a[@data-method='delete']";
    final public static String editingBtn = "//table//tbody//tr//td//a[contains(@data-test,'edit')]";
    final public static String showBtn = "//table//tbody//tr//td//a[contains(@data-test,'show')]";


    private static String[] strArr;

    static {
        try {
            strArr = myMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String validFirstName = strArr[0];
    public static String validSecondName = strArr[1];
    public static String validZipCode = strArr[2];
    public static String validAddress = strArr[3];
    public static String validCity = strArr[4];

    public static String[] myMethod() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader("main/resources/credentials.csv"));
        String[] strArr = csvReader.readNext();
        return strArr;
    }


    public variables() throws IOException {

    }
}
