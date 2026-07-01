package automation.pages;

import automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreatePolicyPage extends BasePage {
    WebDriver driver;
    public CreatePolicyPage(WebDriver driver) {
        super(driver);

    }

    // Locators
    By createpolicybtn = By.xpath("//h3[normalize-space()='New Policy']");
    //By newPolicy = By.linkText("+ New policy");
   // By newPolicy = By.xpath("//button[text()='Create policy']");
    By policyname = By.xpath("(//input[@class='input-field ng-untouched ng-pristine ng-invalid'])[1]");
    By dropdown = By.xpath("//select[@class='input-field ng-pristine ng-valid ng-touched']");
    By price = By.xpath("(//input[@class='input-field ng-untouched ng-pristine ng-invalid'])[2]");
    By createpolicy = By.xpath("//button[normalize-space()='Create policy']");

    // Actions
    public void clickPolicyTab() {
        clickElement(createpolicybtn);
    }


    public void setPolicyName(String NameOfPolicy) {

        enterText(policyname,NameOfPolicy);
    }

    public void setPremiumType(String premium) {

        Select st = new Select((WebElement) dropdown);
        if(premium.equals("Monthly"))
            st.selectByIndex(0);
        else
            st.selectByIndex(1);

    }

    public void setAmount(String amount) {
        enterText(price,amount);
    }

    public void clickPolicy() {
        clickElement(createpolicy);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}