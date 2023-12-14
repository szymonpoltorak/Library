package pl.edu.pw.ee.cinemabackend.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class MovieDetailsPage {

    private WebDriver webDriver;

    public MovieDetailsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movie-details/div/div[1]/div/button/span[2]")
    private WebElement returnButton;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movie-details/div/div[2]/div[1]/mat-calendar/mat-calendar-header/div/div/button[1]/span[2]/span")
    private WebElement calendarMonthAndYear;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movie-details/div/div[2]/div[1]/mat-calendar/div/mat-multi-year-view/table/tbody/tr[2]/td[4]/button/span[1]")
    private WebElement calendarFutureYear;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movie-details/div/div[2]/div[1]/mat-calendar/div/mat-year-view/table/tbody/tr[4]/td[4]/button/span[1]")
    private WebElement calendarFutureMonth;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movie-details/div/div[2]/div[1]/mat-calendar/div/mat-month-view/table/tbody/tr[4]/td[5]/button/span[1]")
    private WebElement calendarFutureDay;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movie-details/div/div[2]/div[1]/div/button/span[2]")
    private WebElement addButton;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movie-details/div/div[2]/div[20]/div/p[1]")
    private WebElement addedScreening;
    @FindBy(xpath = "/html/body/app-root/app-nav/mat-toolbar/mat-toolbar-row/div/button[1]/span[3]")
    private WebElement homeButton;

    public void clickReturnButton(){
        returnButton.click();
    }
    public void clickCalendar(){
        calendarMonthAndYear.click();
    }
    public void clickFutureYear(){
        calendarFutureYear.click();
    }
    public void clickFutureMonth(){
        calendarFutureMonth.click();
    }
    public void clickFutureDay(){
        calendarFutureDay.click();
    }
    public void clickAddButton(){
        addButton.click();
    }
    public void clickHomeButton(){
        homeButton.click();
    }
}
