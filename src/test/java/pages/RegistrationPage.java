package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

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

    public void fillInName(String name){
        registrationInputElements.get(0).shouldBe(Condition.visible).click();
        registrationInputElements.get(0).shouldBe(Condition.visible).sendKeys(Keys.CONTROL + "a");
        registrationInputElements.get(0).shouldBe(Condition.visible).sendKeys(Keys.BACK_SPACE);
        registrationInputElements.get(0).shouldBe(Condition.visible).setValue(name);
    }

    public void fillInEmail(String email){
        registrationInputElements.get(1).shouldBe(Condition.visible).click();
        registrationInputElements.get(1).shouldBe(Condition.visible).sendKeys(Keys.CONTROL + "a");
        registrationInputElements.get(1).shouldBe(Condition.visible).sendKeys(Keys.BACK_SPACE);
        registrationInputElements.get(1).shouldBe(Condition.visible).setValue(email);
    }

    public void fillInPassword(String password){
        registrationInputElements.get(2).shouldBe(Condition.visible).click();
        registrationInputElements.get(2).shouldBe(Condition.visible).sendKeys(Keys.CONTROL + "a");
        registrationInputElements.get(2).shouldBe(Condition.visible).sendKeys(Keys.BACK_SPACE);
        registrationInputElements.get(2).shouldBe(Condition.visible).setValue(password);
    }

    public void registrationTitleExist(String title){
        registrationFormTitle.shouldBe(Condition.visible);
        assertEquals(title, registrationFormTitle.getText());
    }


    public void clickRegistrationButton(){
        registrationButton.shouldBe(Condition.visible).click();
    }

    public LoginPage clickRegistrationButtonAndOpenLoginPage(){
        registrationButton.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }

    public LoginPage clickLoginLinkAndOpenLoginPage(){
        loginLink.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }

    public void checkError(String message){
        errorMessage.shouldHave(Condition.text(message));
    }
}
