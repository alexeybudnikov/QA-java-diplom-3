package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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




    public void checkTitle(String text){
        orderTitle.should(Condition.visible).isDisplayed();
        assertEquals(text, orderTitle.getText());
    }

    public void loginButtonHaveText(String text){
        String existedText = loginButton.getText();
        assertEquals(text, existedText);
    }

    public LoginPage loginThroughLoginButton(){
        loginButton.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }

    public LoginPage loginThroughAccountButton(){
        accountButton.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }

    public AccountPage openAccount(){
        accountButton.shouldBe(Condition.visible).click();
        return page(AccountPage.class);
    }

    public void checkMainTitle(){
        assertTrue(mainHeader.shouldBe(Condition.visible).isDisplayed());
    }

    public void checkSauceTitle(){
        assertTrue(sauceHeader.shouldBe(Condition.visible).isDisplayed());
    }

    public void checkBunTitle(){
        assertTrue(bunHeader.shouldBe(Condition.visible).isDisplayed());
    }

    public void openMain(){
        main.click();
    }

    public void openSauce(){
        sauce.click();
    }

    public void openBun(){
        bun.click();
    }
}
