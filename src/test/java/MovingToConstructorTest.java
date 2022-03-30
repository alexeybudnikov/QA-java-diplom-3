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
public class MovingToConstructorTest {

    Map<String, String> user = new UserOperations().register();

    private String browser;
    private String driver;

    public MovingToConstructorTest(String browser, String driver) {
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
    @DisplayName("Open constructor by clicking logo test")
    public void openConstructorThroughLogo() {
        LoginPage loginPage = open("/login", LoginPage.class);
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        AccountPage accountPage = mainPage.openAccount();
        MainPage mainPageAfterClickingLogo = accountPage.openConstructorThroughLogo();
        assertTrue("Title have wrong text", mainPageAfterClickingLogo.isTitleWithTextExist("Соберите бургер"));
        assertTrue("Login button have wrong text", mainPage.isLoginButtonHaveText("Оформить заказ"));
    }

    @Test
    @DisplayName("Open constructor by clicking link test")
    public void openConstructorThroughConstructorLink() {
        LoginPage loginPage = open("/login", LoginPage.class);
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        AccountPage accountPage = mainPage.openAccount();
        MainPage mainPageAfterClickingLogo = accountPage.openConstructorThroughLink();
        assertTrue("Title have wrong text", mainPageAfterClickingLogo.isTitleWithTextExist("Соберите бургер"));
        assertTrue("Login button have wrong text", mainPage.isLoginButtonHaveText("Оформить заказ"));
    }

    @After
    public void cleanData() {
        WebDriverRunner.closeWebDriver();
        new UserOperations().delete();
    }
}
