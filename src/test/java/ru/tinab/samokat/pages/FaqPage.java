package ru.tinab.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FaqPage extends BasePage {

    public FaqPage(WebDriver driver) {
        super(driver);
    }

    public void clickQuestion(int index) {
        By question = By.xpath("//div[@id='accordion__heading-" + index + "']");
        click(question);
    }

    public boolean isAnswerVisible(String text) {
        By answer = By.xpath("//p[contains(text(),'" + text + "')]");
        return find(answer).isDisplayed();
    }
}