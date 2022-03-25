package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.model.User;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private  String browser;

    public RegistrationTest(String browser){
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{
                {"src\\main\\resources\\chromedriver.exe"},
                {"src\\main\\resources\\yandexdriver.exe"}
        };
    }

    ArrayList<String> userToken = new ArrayList<>();

    @Before
    public void setUp() {
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site";
        System.setProperty("webdriver.chrome.driver",browser);
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
        registrationPage.checkError("Некорректный пароль");
    }

    @Test
    public void registrationHappyPathTest() {
        User user = new User();
        user.setName("Name");
        user.setEmail("123456789testuser@yandex.ru");
        user.setPassword("123123");
        RegistrationPage registrationPage = open("/register", RegistrationPage.class);
        registrationPage.registrationTitleExist("Регистрация");
        registrationPage.fillInName(user.getName());
        registrationPage.fillInEmail(user.getEmail());
        registrationPage.fillInPassword(user.getPassword());
        LoginPage loginPage = registrationPage.clickRegistrationButtonAndOpenLoginPage();
        loginPage.pageShouldBeVisible();
        loginPage.fillInEmail(user.getEmail());
        loginPage.fillInPassword(user.getPassword());
        MainPage orderPage = loginPage.clickLoginButton();
        orderPage.checkTitle("Соберите бургер");
        userToken.add(Selenide.localStorage().getItem("accessToken"));
    }


    @After
    public void cleanData(){
        if (userToken.size() > 0){
            Response response = given()
                .header("Authorization", userToken.get(0))
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
            response.then().assertThat().statusCode(202);
        }
        WebDriverRunner.closeWebDriver();
    }
}
