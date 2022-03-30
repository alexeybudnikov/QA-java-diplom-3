package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    @FindBy(how = How.XPATH, using = "//h1[@class='text text_type_main-large mb-5 mt-10']")
    private SelenideElement orderTitle;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//a[@class='AppHeader_header__link__3D_hX']/p[text()='Личный Кабинет']")
    private SelenideElement accountButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement main;

    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement sauce;

    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement bun;

    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement mainHeader;

    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы']")
    private SelenideElement sauceHeader;

    @FindBy(how = How.XPATH, using = "//h2[text()='Булки']")
    private SelenideElement bunHeader;


    @Step("Checking title text")
    public boolean isTitleWithTextExist(String text) {
        return orderTitle.should(Condition.visible).shouldHave(Condition.text(text)).isDisplayed();
    }

    @Step("Checking login button text")
    public boolean isLoginButtonHaveText(String text) {
        return loginButton.shouldBe(Condition.visible).shouldHave(Condition.text(text)).isDisplayed();
    }

    @Step("Login through login button")
    public LoginPage loginThroughLoginButton() {
        loginButton.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }

    @Step("Login through account button")
    public LoginPage loginThroughAccountButton() {
        accountButton.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }

    @Step("Open account page")
    public AccountPage openAccount() {
        accountButton.shouldBe(Condition.visible).click();
        return page(AccountPage.class);
    }

    @Step("Checking 'Main' category title exist in constructor")
    public boolean isMainTitleExist() {
        return mainHeader.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Checking 'Sauce' category title exist in constructor")
    public boolean isSauceTitleExist() {
        return sauceHeader.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Checking 'Bun' category title exist in constructor")
    public boolean isBunTitleExist() {
        return bunHeader.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Open 'Main' category by clicking tab in constructor")
    public void openMain() {
        main.click();
    }

    @Step("Open 'Sauce' category by clicking tab in constructor")
    public void openSauce() {
        sauce.click();
    }

    @Step("Open 'Bun' category by clicking tab in constructor")
    public void openBun() {
        bun.click();
    }
}
