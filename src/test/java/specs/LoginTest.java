package specs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LoginPage;
import settings.OpenURL;
import settings.SetBrowser;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest {
    private final LoginPage lp = new LoginPage();

    private final static String URL = "https://www.farpost.ru/";


    @BeforeEach
    public void openBrowser() {
        SetBrowser setBrowser = new SetBrowser();
        setBrowser.init();

        new OpenURL(URL);
    }

    @AfterEach
    public void tearDown() {
        SetBrowser setBrowser = new SetBrowser();
        setBrowser.ClearBrowser();
    }


    @ParameterizedTest
    @DisplayName("Verify authentication using valid and invalid credentials")
    @CsvFileSource(resources = "/loginData.csv")
    void paramTest(String username, String password, String expectedTitle){
        lp.clickLoginBtn();
        lp.setCredentials(username,password);

        String actualTitle = lp.getTitleAuthPage();
        assertEquals(expectedTitle, actualTitle);
    }
}
