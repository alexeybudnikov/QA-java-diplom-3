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
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class MovingToAccountTest {

    Map<String, String> user = new UserOperations().register();

    private  String browser;

    public MovingToAccountTest(String browser){
        this.browser=browser;
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
    public void navigationToAccountTest(){
        LoginPage loginPage = open("/login",LoginPage.class);
        loginPage.fillInEmail(user.get("email"));
        loginPage.fillInPassword(user.get("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        mainPage.checkTitle("Соберите бургер");
        AccountPage accountPage = mainPage.openAccount();
        accountPage.checkUserEmail(user.get("email"));
        accountPage.checkUserName(user.get("name"));
    }

    @After
    public void cleanData(){
        WebDriverRunner.closeWebDriver();
        new UserOperations().delete();
    }
}
