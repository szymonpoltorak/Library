package pl.edu.pw.ee.cinemabackend.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class MoviesPage {

    private WebDriver webDriver;

    public MoviesPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movielist/div/div[1]/table/tbody/tr[1]/td[1]")
    private WebElement firstMovieId;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movielist/div/div[1]/mat-paginator/div/div/div[2]/div")
    private WebElement txtMoviesOnPage;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movielist/div/div[1]/mat-paginator/div/div/div[1]/mat-form-field/div[1]/div/div[2]/mat-select/div/div[1]/span/span")
    private WebElement itemsPerPageDrop;
    @FindBy(xpath = "/html/body/div[2]/div[2]/div/div/mat-option[2]")
    private WebElement itemsPerPageTen;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movielist/div/div[2]/mat-card/mat-calendar/mat-calendar-header/div/div/button[1]/span[2]")
    private WebElement calendarMonthAndYear;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movielist/div/div[2]/mat-card/mat-calendar/div/mat-multi-year-view/table/tbody/tr[1]/td[2]/button/span[1]")
    private WebElement calendarPastYear;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movielist/div/div[2]/mat-card/mat-calendar/div/mat-year-view/table/tbody/tr[3]/td[2]/button/span[1]")
    private WebElement calendarPastMonth;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movielist/div/div[2]/mat-card/mat-calendar/div/mat-month-view/table/tbody/tr[3]/td[3]/button/span[1]")
    private WebElement calendarPastDay;
    @FindBy(xpath = "/html/body/app-root/app-nav/div/app-movielist/div/div[1]/mat-paginator/div/div/div[2]/button[3]/span[3]")
    private WebElement nextPageButton;

    public void clickFirstMovie() {
        firstMovieId.click();
    }

    public void clickItemsPerPageDrop() {
        itemsPerPageDrop.click();
    }

    public void clickItemsPerPageTen() {
        itemsPerPageTen.click();
    }

    public void clickCalendar() {
        calendarMonthAndYear.click();
    }

    public void clickPastYear() {
        calendarPastYear.click();
    }

    public void clickPastMonth() {
        calendarPastMonth.click();
    }

    public void clickPastDay() {
        calendarPastDay.click();
    }

    public void clickNextPage() {
        nextPageButton.click();
    }
}
