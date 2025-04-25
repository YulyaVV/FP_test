package pages;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    //Локаторы
    private final String LOGIN_BTN = "//a[contains(text(), 'Вход и регистрация')]"; // локатор кнопки "Вход и регистрация"
    private final String SIGN = "//input[@id='sign']"; // локатор поля Логин
    private final String PASSWORD = "//input[@id='password']"; //локатор поля Пароль
    private final String SUBMIT_LOGIN_BTN = "//button[@id='signbutton']"; // локатор кнопки "Войти с паролем"
    private final String TITLE_AUTH_PAGE = "//head/title"; // локатор названия страницы

    //Методы
    //Метод клика на кнопку "Вход и регистрация"
    public void clickLoginBtn() {
        $x(LOGIN_BTN).shouldBe(visible)
                .shouldBe(clickable)
                .click();
    }

    //Метод заполнения формы Авторизации
    public void setCredentials(String email, String password) {
        $x(SIGN).setValue(email);
        $x(PASSWORD).setValue(password);
        $x(SUBMIT_LOGIN_BTN).click();
    }

    //Метод получения названия страницы "title"
    public String getTitleAuthPage() {
        return $x(TITLE_AUTH_PAGE).innerText();
    }

}
