package automation.pages;

import automation.base.BasePage;
import automation.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By loginbtn=By.xpath("//div[@class='mt-10 flex flex-wrap gap-4']//a[@class='btn-secondary'][normalize-space()='Sign in']");
    By username= By.xpath ( "//input[@placeholder='you@example.com']");

    By password= By.xpath("//input[@placeholder='••••••••']");

    By signin=By.xpath("//button[@type='submit']");

    By logout=By.xpath("//button[text()='Sign out']");

    By account=By.xpath("//p[contains(normalize-space(),'Welcome')]");


// Actions Methods

    public void clickLoginBtn(){
       clickElement(loginbtn);
    }

    public void setUserName(String user) {
       enterText(username,user);
    }

    public  void setPassword(String pass){
       enterText(password,pass);
    }

    public void clickLogin(){
       clickElement(signin);
    }

    public void clicklogout(){
       clickElement(logout);
    }


    public String checkaccount(){
        return driver.getCurrentUrl();
    }

}


