package pl.edu.pw.ee.cinemabackend.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class HomePage {

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "/html/body/app-root/app-nav/mat-toolbar/mat-toolbar-row/button")
    private WebElement logoutButton;
    @FindBy(xpath = "/html/body/app-root/app-nav/mat-toolbar/mat-toolbar-row/div/button[1]")
    private WebElement homeButton;
    @FindBy(xpath = "/html/body/app-root/app-nav/mat-toolbar/mat-toolbar-row/div/button[2]")
    private WebElement moviesButton;

    public final void clickLogout() {
        logoutButton.click();
    }

    public final void clickHome() {
        homeButton.click();
    }

    public final void clickMovies() {
        moviesButton.click();
    }

}
