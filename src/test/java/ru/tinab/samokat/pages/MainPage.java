package ru.tinab.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static ru.tinab.samokat.utils.config.BASE_URL;

import java.time.Duration;

public class MainPage {


    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== ЛОКАТОРЫ =====

    // Верхняя кнопка «Заказать»
    private final By topOrderButton =
            By.xpath("//button[contains(@class,'Button_Button__ra12g')]");

    // Cookie
    private final By cookieButton = By.id("rcc-confirm-button");

    // FAQ
    private By faqQuestion(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By faqAnswer(int index) {
        return By.id("accordion__panel-" + index);
    }

    // ===== МЕТОДЫ =====

    public void openPage() {
        driver.get(BASE_URL);
    }

    public void closeCookieBanner() {
        if (!driver.findElements(cookieButton).isEmpty()) {
            driver.findElement(cookieButton).click();
        }
    }

    public void clickTopOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(topOrderButton)).click();
    }

    public void scrollToFaq(int index) {
        WebElement element = driver.findElement(faqQuestion(index));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }

    public void clickFaqQuestion(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(faqQuestion(index))).click();
    }

    public boolean isFaqAnswerDisplayed(int index) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(faqAnswer(index)))
                .isDisplayed();
    }

    public String getFaqAnswerText(int index) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(faqAnswer(index)))
                .getText();
    }
}