package ru.tinab.samokat.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.tinab.samokat.driver.WebDriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverFactory.create();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}