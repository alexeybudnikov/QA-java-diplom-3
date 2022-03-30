package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class AccountPage {

    @FindBy(how = How.XPATH, using = "//a[@class='Account_link__2ETsJ text text_type_main-medium text_color_inactive Account_link_active__2opc9']")
    private SelenideElement profileLink;

    @FindBy(how = How.XPATH, using = "//a[@class='Account_link__2ETsJ text text_type_main-medium text_color_inactive']")
    private SelenideElement ordersHistoryLink;

    @FindBy(how = How.XPATH, using = "//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']")
    private SelenideElement logoutButton;

    @FindBy(how = How.XPATH, using = "//input[@class='text input__textfield text_type_main-default input__textfield-disabled']")
    private ElementsCollection userInfoFields;

    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH, using = "//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement logo;

    @Step("Get name from account")
    public String getUserNameFromAccount(){
        return userInfoFields.get(0).shouldBe(Condition.visible).getValue();
    }

    @Step("Get email from account")
    public String getUserEmailFromAccount(){
       return userInfoFields.get(1).shouldBe(Condition.visible).getValue();
    }

    @Step("Open constructor through link")
    public MainPage openConstructorThroughLink(){
        constructorButton.shouldBe(Condition.visible).click();
        return page(MainPage.class);
    }

    @Step("Open constructor through logo")
    public MainPage openConstructorThroughLogo(){
        logo.shouldBe(Condition.visible).click();
        return page(MainPage.class);
    }

    @Step("Logout")
    public LoginPage logout(){
        logoutButton.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }
}
