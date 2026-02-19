package ru.tinab.samokat.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.tinab.samokat.pages.MainPage;
import ru.tinab.samokat.pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    // Параметры теста
    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String date;
    private String rentalPeriod;
    private String comment;

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "ул. Ленина, 1", "Бульвар Рокоссовского", "+79991234567", "19.02.2026", "сутки", "Комментарий 1"},
                {"Мария", "Петрова", "ул. Пушкина, 10", "Аннино", "+79876543210", "20.02.2026", "двое суток", "Комментарий 2"}
        });
    }

    public OrderTest(String name, String surname, String address, String metro,
                     String phone, String date, String rentalPeriod, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.comment = comment;
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testPositiveOrderFlow() {
        // Нажимаем верхнюю кнопку "Заказать"
        driver.findElement(mainPage.topOrderButton).click();

        // Заполняем форму заказа
        orderPage.fillName(name);
        orderPage.fillSurname(surname);
        orderPage.fillAddress(address);
        orderPage.selectMetro(metro);
        orderPage.fillPhone(phone);
        orderPage.clickNext(); // Переход к следующей части формы

        // Вторая часть формы
        orderPage.fillDate(date);
        orderPage.selectRentalPeriod(rentalPeriod);
        orderPage.chooseBlackColor();
        orderPage.fillComment(comment);
        orderPage.clickOrder();

        // Проверяем, что появилось модальное окно с подтверждением заказа
        Assert.assertTrue("Заказ не был оформлен!", orderPage.isOrderConfirmed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
