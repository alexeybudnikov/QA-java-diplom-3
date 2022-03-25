package test;

import com.UserOperations;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import com.codeborne.selenide.WebDriverRunner;
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


@RunWith(Parameterized.class)
public class LoginTest {

    Map<String, String> user = new UserOperations().register();

    private  String browser;

    public LoginTest(String browser){
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{
                {"src\\main\\resources\\chromedriver.exe"},
                {"src\\main\\resources\\yandexdriver.exe"}
        };
    }


    @Before
    public void setUp() {
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site";
        System.setProperty("webdriver.chrome.driver",browser);
        Selenide.clearBrowserCookies();
    }

    @Test
    public  void loginFromMainPage(){
        MainPage mainPage = open("/",MainPage.class);
        LoginPage loginPage = mainPage.loginThroughLoginButton();
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPageAfterLogin = loginPage.clickLoginButton();
        mainPageAfterLogin.checkTitle("Соберите бургер");
        mainPageAfterLogin.loginButtonHaveText("Оформить заказ");
    }

    @Test
    public void loginThroughAccountButton(){
        MainPage mainPage = open("/",MainPage.class);
        LoginPage loginPage = mainPage.loginThroughAccountButton();
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPageAfterLogin = loginPage.clickLoginButton();
        mainPageAfterLogin.checkTitle("Соберите бургер");
        mainPageAfterLogin.loginButtonHaveText("Оформить заказ");
    }

    @Test
    public void loginFromRegistrationPage(){
        RegistrationPage registrationPage = open("/register", RegistrationPage.class);
        LoginPage loginPage = registrationPage.clickLoginLinkAndOpenLoginPage();
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPageAfterLogin = loginPage.clickLoginButton();
        mainPageAfterLogin.checkTitle("Соберите бургер");
        mainPageAfterLogin.loginButtonHaveText("Оформить заказ");
    }

    @Test
    public void loginFromResetPasswordPage(){
        ResetPasswordPage resetPasswordPage = open("/forgot-password",ResetPasswordPage.class);
        LoginPage loginPage= resetPasswordPage.loginThroughLoginLink();
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPageAfterLogin = loginPage.clickLoginButton();
        mainPageAfterLogin.checkTitle("Соберите бургер");
        mainPageAfterLogin.loginButtonHaveText("Оформить заказ");
    }

    @After
    public void cleanData(){
        WebDriverRunner.closeWebDriver();
        new UserOperations().delete();
    }
}
