import com.UserOperations;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LogoutTest {

    Map<String, String> user = new UserOperations().register();

    private String browser;
    private String driver;

    public LogoutTest(String browser, String driver) {
        this.browser = browser;
        this.driver = driver;
    }

    @Parameterized.Parameters(name = "Test running in {0}")
    public static Object[][] getBrowser() {
        return new Object[][]{
                {"ChromeBrowser", "src\\main\\resources\\chromedriver.exe"},
                {"YandexBrowser", "src\\main\\resources\\yandexdriver.exe"}
        };
    }

    @Before
    public void setUp() {
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site";
        System.setProperty("webdriver.chrome.driver", driver);
        Selenide.clearBrowserCookies();
    }

    @Test
    @DisplayName("Logout from account page test")
    public void logoutFromAccountPage() {
        LoginPage loginPage = open("/login", LoginPage.class);
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        AccountPage accountPage = mainPage.openAccount();
        LoginPage loginPageAfterLogout = accountPage.logout();
        assertTrue("Login form title have wrong text", loginPageAfterLogout.isLoginTitleHaveText("Вход"));
    }

    @After
    public void cleanData() {
        WebDriverRunner.closeWebDriver();
        new UserOperations().delete();
    }
}
