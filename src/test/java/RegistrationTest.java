import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.model.User;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private String browser;
    private String driver;

    public RegistrationTest(String browser, String driver) {
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

    ArrayList<String> userToken = new ArrayList<>();

    @Before
    public void setUp() {
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site";
        System.setProperty("webdriver.chrome.driver", driver);
        Selenide.clearBrowserCookies();
    }

    @Test
    public void registrationErrorTest() {
        User user = new User();
        user.setName("Name");
        user.setEmail("123456testuser@yandex.ru");
        user.setPassword("12312");
        RegistrationPage registrationPage = open("/register", RegistrationPage.class);
        registrationPage.registrationTitleExist("Регистрация");
        registrationPage.fillInName(user.getName());
        registrationPage.fillInEmail(user.getEmail());
        registrationPage.fillInPassword(user.getPassword());
        registrationPage.clickRegistrationButton();
        assertTrue("Error message is not exist or have wrong text", registrationPage.isErrorMessageExist("Некорректный пароль"));
    }

    @Test
    public void registrationHappyPathTest() {
        User user = new User();
        user.setName("Name");
        user.setEmail("Testuser123@yandex.ru");
        user.setPassword("123123");
        RegistrationPage registrationPage = open("/register", RegistrationPage.class);
        registrationPage.registrationTitleExist("Регистрация");
        registrationPage.fillInName(user.getName());
        registrationPage.fillInEmail(user.getEmail());
        registrationPage.fillInPassword(user.getPassword());
        LoginPage loginPage = registrationPage.clickRegistrationButtonAndOpenLoginPage();
        loginPage.isLoginTitleHaveText("Вход");
        loginPage.fillInEmail(user.getEmail());
        loginPage.fillInPassword(user.getPassword());
        MainPage orderPage = loginPage.clickLoginButton();
        assertTrue("Title have wrong text", orderPage.isTitleWithTextExist("Соберите бургер"));
    }


    @After
    public void cleanData() {
        userToken.add(Selenide.localStorage().getItem("accessToken"));
        if (userToken.size() > 0 && userToken.get(0) != null) {
            Response response = given()
                    .header("Authorization", userToken.get(0))
                    .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
            response.then().assertThat().statusCode(202);
        }
        WebDriverRunner.closeWebDriver();
    }
}
