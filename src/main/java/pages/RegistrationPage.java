package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {

    @FindBy(how = How.XPATH, using = "//input[@class='text input__textfield text_type_main-default']")
    private ElementsCollection registrationInputElements;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement registrationButton;

    @FindBy(how = How.XPATH, using = "//div[@class='Auth_login__3hAey']/h2[text() = 'Регистрация']")
    private SelenideElement registrationFormTitle;

    @FindBy(how = How.XPATH, using = "//p[@class='input__error text_type_main-default']")
    private SelenideElement errorMessage;

    @FindBy(how = How.XPATH, using = "//a[@class='Auth_link__1fOlj']")
    private SelenideElement loginLink;

    @Step("Fill in name")
    public void fillInName(String name){
        registrationInputElements.get(0).shouldBe(Condition.visible).click();
        registrationInputElements.get(0).shouldBe(Condition.visible).sendKeys(Keys.CONTROL + "a");
        registrationInputElements.get(0).shouldBe(Condition.visible).sendKeys(Keys.BACK_SPACE);
        registrationInputElements.get(0).shouldBe(Condition.visible).setValue(name);
    }

    @Step("Fill in email")
    public void fillInEmail(String email){
        registrationInputElements.get(1).shouldBe(Condition.visible).click();
        registrationInputElements.get(1).shouldBe(Condition.visible).sendKeys(Keys.CONTROL + "a");
        registrationInputElements.get(1).shouldBe(Condition.visible).sendKeys(Keys.BACK_SPACE);
        registrationInputElements.get(1).shouldBe(Condition.visible).setValue(email);
    }

    @Step("Fill in password")
    public void fillInPassword(String password){
        registrationInputElements.get(2).shouldBe(Condition.visible).click();
        registrationInputElements.get(2).shouldBe(Condition.visible).sendKeys(Keys.CONTROL + "a");
        registrationInputElements.get(2).shouldBe(Condition.visible).sendKeys(Keys.BACK_SPACE);
        registrationInputElements.get(2).shouldBe(Condition.visible).setValue(password);
    }

    @Step("Checking registration title")
    public boolean registrationTitleExist(String title){
        return registrationFormTitle.shouldBe(Condition.visible).shouldHave(Condition.text(title)).isDisplayed();
    }

    @Step("Click registration button")
    public void clickRegistrationButton(){
        registrationButton.shouldBe(Condition.visible).click();
    }

    @Step("Click registration button and open login page")
    public LoginPage clickRegistrationButtonAndOpenLoginPage(){
        registrationButton.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }

    @Step("Click login link and open login page")
    public LoginPage clickLoginLinkAndOpenLoginPage(){
        loginLink.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }

    @Step("Checking error message")
    public boolean isErrorMessageExist(String message){
        return errorMessage.shouldHave(Condition.text(message)).isDisplayed();
    }
}
