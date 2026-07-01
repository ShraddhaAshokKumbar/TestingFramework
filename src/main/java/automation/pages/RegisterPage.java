package automation.pages;

import automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    By SignUpButton=By.linkText("Start free");
    By FullName=By.xpath("//input[@class='input-field ng-pristine ng-invalid ng-touched']");
    By Email=By.xpath("//input[@type='email']");

    By Password=By.xpath("//input[@type='password']");

    By Phonenumber=By.xpath("//app-ui-input[@type='text']//input[@type='text']");

    By dropdown =By.xpath("//select[@class='input-field ng-pristine ng-valid ng-touched']");


    By dropdown2 = By.xpath("//div[@class='block']//select[@class='input-field ng-pristine ng-valid ng-touched']");


    By Date=By.xpath("//input[@type='date']");

    By Register=By.xpath("//button[normalize-space()='Register']");

     String UserRole;
    // Actions

    public void ClickSignUpBtn(){
        clickElement(SignUpButton);
    }

    public void setFullName(String name) throws InterruptedException {
        Thread.sleep(3000);
        enterText(FullName,name);

    }

    public void setEmailField(String email) {
        enterText(Email, email);
    }

    public void setPasswordField(String password) {
        enterText(Password, password);
    }

    public void setPhoneField(String phone) {
        enterText(Phonenumber, phone);
    }

    public void setRoleSelectField(String Role) {
        UserRole=Role;
        Select role = new Select((WebElement) dropdown);
        role.selectByVisibleText(Role);
    }

    public void setGenderField(String genderField) {
        if(UserRole.equals("Client")) {
            Select gender = new Select((WebElement) dropdown2);
            gender.selectByVisibleText(genderField);
        }
    }

    public void setDate(String date){
        if(UserRole.equals("Client"))
        {
            enterText(Date,date);
        }
    }

    public void register() {
        clickElement(Register);
    }



}
