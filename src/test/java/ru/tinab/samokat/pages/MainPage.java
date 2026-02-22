package ru.tinab.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==========================
    // ЛОКАТОРЫ
    // ==========================

    // Верхняя кнопка "Заказать"
    private By topOrderButton = By.xpath("//button[contains(@class,'Button_Button__ra12g')]");

    // FAQ вопросы
    private By faqQuestion(int index) {
        return By.id("accordion__heading-" + index);
    }

    // FAQ ответы
    private By faqAnswer(int index) {
        return By.id("accordion__panel-" + index);
    }

    // ==========================
    // МЕТОДЫ
    // ==========================

    /** Открываем главную страницу */
    public void openPage() {
        driver.get(url);
    }
    // Локатор кнопки "Принять cookies"
    private By cookieAcceptButton = By.xpath("//button[contains(text(),'да')]");

    public void closeCookieBanner() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (Exception e) {
            // Если баннера нет, ничего не делаем
        }
    }

    /** Кликаем верхнюю кнопку "Заказать" */
    public void clickTopOrderButton() {
        // Переходим на главную страницу, если ещё не открыта
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }

        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(topOrderButton));

        // Клик через JS, если обычный клик не сработает
        try {
            orderButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orderButton);
        }
    }

    /** Скролл к первому вопросу FAQ */
    public void scrollToFaq() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                driver.findElement(faqQuestion(0))
        );
    }

    /** Кликаем на вопрос FAQ по индексу */
    public void clickFaqQuestion(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(faqQuestion(index))).click();
    }
    public String getFaqAnswerText(int index) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(faqAnswer(index)))
                .getText();
    }
    /** Проверяем, отображается ли ответ FAQ по индексу */
    public boolean isFaqAnswerDisplayed(int index) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(faqAnswer(index)))
                .isDisplayed();
    }
}