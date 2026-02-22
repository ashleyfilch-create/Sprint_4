package ru.tinab.samokat.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.tinab.samokat.pages.MainPage;
import ru.tinab.samokat.pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String comment;

    public OrderTest(String name, String surname, String address,
                     String metro, String phone, String date,
                     String period, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Анна", "Иванова", "Москва, Тверская 1", "Бульвар Рокоссовского", "79999999999", "20.02.2026", "сутки", "Позвоните заранее"},
                {"Иван", "Петров", "Москва, Арбат 5", "Сокольники", "78888888888", "22.02.2026", "двое суток", "Оставьте у двери"}
        });
    }

    @Test
    public void testPositiveOrderFlow() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.fillName(name);
        orderPage.fillSurname(surname);
        orderPage.fillAddress(address);
        orderPage.selectMetro(metro);
        orderPage.fillPhone(phone);
        orderPage.clickNext();

        orderPage.fillDate(date);
        orderPage.selectRentalPeriod(period);
        orderPage.chooseBlackColor();
        orderPage.fillComment(comment);
        orderPage.clickOrder();

        Assert.assertTrue(
                "Заказ не был подтверждён",
                orderPage.isOrderConfirmed()
        );
    }
}