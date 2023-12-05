package pl.edu.pw.ee.cinemabackend.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage {

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "/html/body/app-root/app-login/div/form/app-email-field/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtEmail;

    @FindBy(xpath = "/html/body/app-root/app-login/div/form/app-password-field/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtPassword;

    @FindBy(xpath = "/html/body/app-root/app-login/div/form/div/button[1]")
    private WebElement lnkRegister;

    @FindBy(xpath = "/html/body/app-root/app-login/div/form/div/button[2]")
    private WebElement lnkLogin;

    @FindBy(xpath = "/html/body/app-root/app-login/div/form/app-email-field/mat-form-field/div[2]/div/mat-error")
    private WebElement emailErrorMsg;

    @FindBy(xpath = "/html/body/app-root/app-login/div/form/app-password-field/mat-form-field/div[2]/div/mat-error")
    private WebElement passwordErrorMsg;

    public void clickRegister(){
        lnkRegister.click();
    }

    public void clickLogin(){
        lnkLogin.click();
    }

}
