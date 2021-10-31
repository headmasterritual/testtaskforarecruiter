import CommonSetUp.SetUp;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.RandomStringGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Headers;
import io.undertow.util.Methods;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Selenide tests")
class TestImplementation extends SetUp {

    private SelenideElement AddComputer = $(By.id("add"));
    private SelenideElement ComputerName = $(By.id("name"));
    private SelenideElement Introduced = $(By.id("introduced"));
    private SelenideElement Discontinued = $(By.id("discontinued"));
    private SelenideElement Company = $(By.cssSelector("#company > option:nth-child(2)"));
    private SelenideElement CreateThis = $(By.cssSelector("#main > form > div > input"));
    private SelenideElement DoneMessage = $(By.cssSelector("#main > div.alert-message.warning"));
    private SelenideElement SearchBox = $(By.id("searchbox"));
    private SelenideElement Searchsubmit = $(By.id("searchsubmit"));
    private SelenideElement firstChild = $(By.cssSelector("#main > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"));
    String computername = "baamboozla" +  RandomStringGenerator.RandomString();

    @Test
    void openTest() {
        setUpSelenide();
        open("http://computer-database.gatling.io/computers");
        AddComputer.should(visible);
        AddComputer.should(visible).click();
        ComputerName.should(visible).sendKeys(computername);
        Introduced.should(visible).sendKeys("2011-10-31");
        Discontinued.should(visible).sendKeys("2021-10-31");
        Company.should(visible).click();
        CreateThis.should(visible).click();
        DoneMessage.shouldHave(text("Done ! Computer " + computername + " has been created"));
        SearchBox.should(visible).sendKeys(computername);
        Searchsubmit.should(visible).click();
        firstChild.shouldHave(text(computername));
    }
}