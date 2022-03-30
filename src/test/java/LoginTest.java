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
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.ResetPasswordPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest {

    Map<String, String> user = new UserOperations().register();

    private String browser;
    private String driver;

    public LoginTest(String browser, String driver) {
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
    @DisplayName("Login from main page test")
    public void loginFromMainPage() {
        MainPage mainPage = open("/", MainPage.class);
        LoginPage loginPage = mainPage.loginThroughLoginButton();
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPageAfterLogin = loginPage.clickLoginButton();
        assertTrue("Title have unexpected text", mainPageAfterLogin.isTitleWithTextExist("Соберите бургер"));
        assertTrue("Login button have unexpected text", mainPageAfterLogin.isLoginButtonHaveText("Оформить заказ"));
    }

    @Test
    @DisplayName("Login through clicking account button test")
    public void loginThroughAccountButton() {
        MainPage mainPage = open("/", MainPage.class);
        LoginPage loginPage = mainPage.loginThroughAccountButton();
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPageAfterLogin = loginPage.clickLoginButton();
        assertTrue("Title have unexpected text", mainPageAfterLogin.isTitleWithTextExist("Соберите бургер"));
        assertTrue("Login button have unexpected text", mainPageAfterLogin.isLoginButtonHaveText("Оформить заказ"));
    }

    @Test
    @DisplayName("Login from registration page test")
    public void loginFromRegistrationPage() {
        RegistrationPage registrationPage = open("/register", RegistrationPage.class);
        LoginPage loginPage = registrationPage.clickLoginLinkAndOpenLoginPage();
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPageAfterLogin = loginPage.clickLoginButton();
        assertTrue("Title have unexpected text", mainPageAfterLogin.isTitleWithTextExist("Соберите бургер"));
        assertTrue("Login button have unexpected text", mainPageAfterLogin.isLoginButtonHaveText("Оформить заказ"));
    }

    @Test
    @DisplayName("Login from rest password page test")
    public void loginFromResetPasswordPage() {
        ResetPasswordPage resetPasswordPage = open("/forgot-password", ResetPasswordPage.class);
        LoginPage loginPage = resetPasswordPage.loginThroughLoginLink();
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPageAfterLogin = loginPage.clickLoginButton();
        assertTrue("Title have unexpected text", mainPageAfterLogin.isTitleWithTextExist("Соберите бургер"));
        assertTrue("Login button have unexpected text", mainPageAfterLogin.isLoginButtonHaveText("Оформить заказ"));
    }

    @After
    public void cleanData() {
        WebDriverRunner.closeWebDriver();
        new UserOperations().delete();
    }
}
