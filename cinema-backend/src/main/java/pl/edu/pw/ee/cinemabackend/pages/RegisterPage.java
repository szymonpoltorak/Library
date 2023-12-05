package pl.edu.pw.ee.cinemabackend.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class RegisterPage {

    public static final String URL = "http://localhost:4200/auth/register:4200/";

    private WebDriver webDriver;

    public RegisterPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[1]/app-name-field[1]/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtName;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[1]/app-name-field[2]/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtSurname;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/app-email-field/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtEmail;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[2]/app-password-field[1]/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtPassword;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[2]/app-password-field[2]/mat-form-field/div[1]/div[2]/div/input")
    private WebElement txtRepeatPassword;

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[3]/button[1]")
    private WebElement lnkReturn;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[3]/button[2]")
    private WebElement lnkRegister;

    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[1]/app-name-field[1]/mat-form-field/div[2]/div/mat-error")
    private WebElement nameErrorMsg;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[1]/app-name-field[2]/mat-form-field/div[2]/div/mat-error")
    private WebElement surnameErrorMsg;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/app-email-field/mat-form-field/div[2]/div/mat-error")
    private WebElement emailErrorMsg;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[2]/app-password-field[1]/mat-form-field/div[2]/div/mat-error")
    private WebElement passwordErrorMsg;
    @FindBy(xpath = "/html/body/app-root/app-register/div/form/div[2]/app-password-field[2]/mat-form-field/div[2]/div/mat-error")
    private WebElement repeatPasswordErrorMsg;

    public void clickReturn(){
        lnkReturn.click();
    }

    public void clickRegister(){
        lnkRegister.click();
    }
}
