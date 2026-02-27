package ru.tinab.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }


    // Локаторы для Шага 1.

    private final By nameField =
            By.xpath("//input[@placeholder='* Имя']");

    private final By surnameField =
            By.xpath("//input[@placeholder='* Фамилия']");

    private final By addressField =
            By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    private final By metroField =
            By.xpath("//input[@placeholder='* Станция метро']");

    private final By phoneField =
            By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    private final By nextButton =
            By.xpath("//button[contains(@class,'Button_Middle__1CSJM')]");

    private final By metroOptions =
            By.className("select-search__option");

    // Локаторы для Шага 2.

    private final By dateField =
            By.xpath("//input[@placeholder='* Когда привезти самокат']");

    private final By datePicker =
            By.className("react-datepicker");

    private final By rentalPeriodDropdown =
            By.className("Dropdown-placeholder");

    private final By blackColorCheckbox =
            By.xpath("//label[@for='black']");

    private final By commentField =
            By.xpath("//input[@placeholder='Комментарий для курьера']");

    private final By orderButton =
            By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    // Локаторы для модального окна.

    private final By orderModal =
            By.xpath("//div[@class='Order_Modal__YZ-d3']");

    private final By orderModalHeader =
            By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    private final By confirmButton =
            By.xpath("//button[contains(text(),'Да')]");

    // Методы для Шага 1.

    public void fillName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField))
                .sendKeys(name);
    }

    public void fillSurname(String surname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(surnameField))
                .sendKeys(surname);
    }

    public void fillAddress(String address) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressField))
                .sendKeys(address);
    }

    public void selectMetro(String metro) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(metroField)
        );
        input.click();
        input.sendKeys(metro);

        wait.until(ExpectedConditions.visibilityOfElementLocated(metroOptions));
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);
    }

    public void fillPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField))
                .sendKeys(phone);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton))
                .click();
    }

    // Методы для Шага 2.

    public void fillDate(String date) {
        WebElement dateInput = wait.until(
                ExpectedConditions.elementToBeClickable(dateField)
        );
        dateInput.click();
        dateInput.clear();
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(datePicker));
    }

    public void selectRentalPeriod(String period) {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(rentalPeriodDropdown)
        );
        new Actions(driver).click(dropdown).perform();

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[text()='" + period + "']")
                )
        );
        option.click();
    }

    public void chooseBlackColor() {
        wait.until(ExpectedConditions.elementToBeClickable(blackColorCheckbox))
                .click();
    }

    public void fillComment(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentField))
                .sendKeys(comment);
    }

    public void clickOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton))
                .click();
    }

    // Проверка оформления заказа.

    /** Проверка появления окна "Хотите оформить заказ?" */
    public boolean isOrderConfirmationModalDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderModal));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderModalHeader))
                .getText()
                .equals("Хотите оформить заказ?");
    }

    /** Нажатие кнопки "Да" (известный баг: может не сработать) */
    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton))
                .click();
    }

    /** Проверка, что заказ оформлен */
    public boolean isOrderConfirmed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderModal));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderModalHeader))
                .getText()
                .equals("Заказ оформлен");
    }
}