package hooks;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.Given;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.UnexpectedAlertBehaviour.IGNORE;
import static org.openqa.selenium.remote.CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR;

public class BeforeSteps {
    @Given("Open website {string}")
    public void open_website(String url) {
        Configuration.browser = Browsers.CHROME;
        Configuration.browserCapabilities.setCapability(UNEXPECTED_ALERT_BEHAVIOUR, IGNORE);
        open(url);
        getWebDriver().manage().window().maximize();
    }

}
