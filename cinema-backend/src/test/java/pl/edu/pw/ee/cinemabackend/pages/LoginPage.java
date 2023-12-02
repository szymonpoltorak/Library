package pl.edu.pw.ee.cinemabackend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class LoginPage {

    @FindBy(how = How.ID, using = "mat-input-0")
    public WebElement txtEmail;

    @FindBy(how = How.ID, using = "mat-input-1")
    public WebElement txtPassword;

    @FindBy(how = How.LINK_TEXT, using = "register")
    public WebElement lnkRegister;

    public void clickRegister(){
        lnkRegister.click();
    }

}
