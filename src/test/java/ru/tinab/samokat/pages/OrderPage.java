package ru.tinab.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==========================
    // Локаторы
    // ==========================
    public By nameField = By.xpath("//input[@placeholder='* Имя']");
    public By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    public By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    public By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    public By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    public By nextButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM')]");
    public By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    public By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    public By colorBlackCheckbox = By.xpath("//label[@for='black']");
    public By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    public By orderButton = By.xpath("(//button[contains(@class,'Button_Middle__1CSJM')])[2]");
    public By orderConfirmationModal = By.className("Order_Modal__YZ-d3");
    public By datePicker = By.className("react-datepicker");

    // ==========================
    // Методы
    // ==========================

    public void fillName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    public void fillSurname(String surname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(surnameField)).sendKeys(surname);
    }

    public void fillAddress(String address) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressField)).sendKeys(address);
    }

    public void selectMetro(String metro) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(metroField));
        input.click();
        input.sendKeys(metro);

        // Ждем автокомплит
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        // Выбираем первый вариант через клавиши
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);
    }

    public void fillPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField)).sendKeys(phone);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void fillDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        dateInput.click();
        dateInput.clear();
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER); // закрываем календарь
    }

    public void selectRentalPeriod(String period) {
        // Скрываем календарь, если он ещё открыт
        wait.until(ExpectedConditions.invisibilityOfElementLocated(datePicker));

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown));
        // Делаем клик через Actions, чтобы Selenium точно попал
        new Actions(driver).moveToElement(dropdown).click().perform();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + period + "']")));
        option.click();
    }

    public void chooseBlackColor() {
        wait.until(ExpectedConditions.elementToBeClickable(colorBlackCheckbox)).click();
    }

    public void fillComment(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentField)).sendKeys(comment);
    }

    public void clickOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public boolean isOrderConfirmed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationModal)).isDisplayed();
    }
}
