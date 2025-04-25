package settings;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class SetBrowser {
    public void setUpBrowser() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        Configuration.browser="chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
    }

    public void ClearBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.localStorage().clear();
        Selenide.closeWebDriver();
    }

    @BeforeEach
    public void init() {setUpBrowser();}

    @AfterEach
    public void tearDown() {ClearBrowser();}
}
