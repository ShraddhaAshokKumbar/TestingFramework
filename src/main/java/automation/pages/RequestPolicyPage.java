package automation.pages;

import automation.base.BasePage;
import automation.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestPolicyPage extends BasePage {

    public RequestPolicyPage(WebDriver driver) {
        super(driver);
    }


    // webelements
    By Policies=By.xpath("//main[@class='relative z-10 flex-1 overflow-y-auto p-4 md:p-8']//a[1]");
    By policyname=By.linkText("Bike Damages Insurance Policy");
    By request=By.xpath("//button[normalize-space()='Request this policy']");


    //Actions

    public void ViewPolices(){
        clickElement(Policies);
    }

    public void clickPolicy(){
       clickElement(policyname);

    }
    public void request(){
       clickElement(request);
    }

}
