package ru.tinab.samokat.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.tinab.samokat.utils.WebDriverFactory;
import static ru.tinab.samokat.utils.config.BASE_URL;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverFactory.create();
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}