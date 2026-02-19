package ru.tinab.samokat.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.tinab.samokat.pages.MainPage;

public class FaqTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void testFaqAccordion() {
        checkFaq(mainPage.faqButton0, mainPage.faqText0);
        checkFaq(mainPage.faqButton1, mainPage.faqText1);
        checkFaq(mainPage.faqButton2, mainPage.faqText2);
        checkFaq(mainPage.faqButton3, mainPage.faqText3);
        checkFaq(mainPage.faqButton4, mainPage.faqText4);
        checkFaq(mainPage.faqButton5, mainPage.faqText5);
        checkFaq(mainPage.faqButton6, mainPage.faqText6);
        checkFaq(mainPage.faqButton7, mainPage.faqText7);
    }

    private void checkFaq(org.openqa.selenium.By button, org.openqa.selenium.By text) {
        driver.findElement(button).click();
        Assert.assertTrue("Текст FAQ не отображается!", driver.findElement(text).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
