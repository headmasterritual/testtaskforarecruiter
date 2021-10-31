package CommonSetUp;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Selenide tests")
public class SetUp {
    public SelenideDriver driver;

    @BeforeAll
    public void setUpSelenide() {
        driver = new SelenideDriver(new SelenideConfig().headless(false));
        SelenideLogger.addListener("allure", new AllureSelenide().savePageSource(false));
    }
    @AfterAll
    void tearDownSelenide() {
        SelenideLogger.removeListener("allure");
    }
}
