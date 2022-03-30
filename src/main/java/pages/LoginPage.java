package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    @FindBy(how = How.XPATH, using = "//input[@class='text input__textfield text_type_main-default']")
    private ElementsCollection loginInputElements;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//div[@class='Auth_login__3hAey']/h2[text() = 'Вход']")
    private SelenideElement loginFormTitle;

    @Step("Fill in email ")
    public void fillInEmail(String text){
        loginInputElements.get(0).shouldBe(Condition.visible).click();
        loginInputElements.get(0).shouldBe(Condition.visible).sendKeys(Keys.CONTROL + "a");
        loginInputElements.get(0).shouldBe(Condition.visible).sendKeys(Keys.BACK_SPACE);
        loginInputElements.get(0).shouldBe(Condition.visible).setValue(text);
    }

    @Step("Fill in password")
    public void fillInPassword(String text){
        loginInputElements.get(1).shouldBe(Condition.visible).click();
        loginInputElements.get(1).shouldBe(Condition.visible).sendKeys(Keys.CONTROL + "a");
        loginInputElements.get(1).shouldBe(Condition.visible).sendKeys(Keys.BACK_SPACE);
        loginInputElements.get(1).shouldBe(Condition.visible).setValue(text);
    }

    @Step("Open main page after clicking login button")
    public MainPage clickLoginButton() {
        loginButton.shouldBe(Condition.visible).click();
        return page(MainPage.class);
    }

    @Step("Checking login form title")
    public boolean isLoginTitleHaveText(String text){
        return loginFormTitle.shouldBe(Condition.visible).shouldHave(Condition.text(text)).isDisplayed();
    }
}
