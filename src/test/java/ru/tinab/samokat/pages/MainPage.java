package ru.tinab.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    // Главные элементы страницы

    // Верхняя кнопка "Заказать"
    public By topOrderButton = By.cssSelector("button.Button_Button__ra12g");


    // FAQ (Вопросы о важном)


    // FAQ: первый вопрос
    public By faqButton0 = By.xpath("//div[@id='accordion__heading-0']");
    public By faqText0 = By.xpath("//p[contains(text(),'Сутки — 400 рублей. Оплата курьеру — наличными или')]");

    // FAQ: второй вопрос
    public By faqButton1 = By.xpath("//div[@id='accordion__heading-1']");
    public By faqText1 = By.xpath("//p[contains(text(),'Пока что у нас так: один заказ — один самокат. Есл')]");

    // FAQ: третий вопрос
    public By faqButton2 = By.xpath("//div[@id='accordion__heading-2']");
    public By faqText2 = By.xpath("//p[contains(text(),'Допустим, вы оформляете заказ на 8 мая. Мы привози')]");

    // FAQ: четвертый вопрос
    public By faqButton3 = By.xpath("//div[@id='accordion__panel-3']");
    public By faqText3 = By.xpath("//p[contains(text(),'Только начиная с завтрашнего дня. Но скоро станем ')]");

    // FAQ: пятый вопрос
    public By faqButton4 = By.xpath("//div[@id='accordion__heading-4']");
    public By faqText4 = By.xpath("//p[contains(text(),'Пока что нет! Но если что-то срочное — всегда можн')]");

    // FAQ: шестой вопрос
    public By faqButton5 = By.xpath("//div[@id='accordion__heading-5']");
    public By faqText5 = By.xpath("//p[contains(text(),'Самокат приезжает к вам с полной зарядкой. Этого х')]");

    // FAQ: седьмой вопрос
    public By faqButton6 = By.xpath("//div[@id='accordion__heading-6']");
    public By faqText6 = By.xpath("//p[contains(text(),'Да, пока самокат не привезли. Штрафа не будет, объ')]");

    // FAQ: восьмой вопрос
    public By faqButton7 = By.xpath("//div[@id='accordion__heading-7']");
    public By faqText7 = By.xpath("//p[contains(text(),'Да, обязательно. Всем самокатов! И Москве, и Моско')]");
}
