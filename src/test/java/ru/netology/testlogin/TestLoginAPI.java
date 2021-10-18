package ru.netology.testlogin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Auth;
import ru.netology.data.Registration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestLoginAPI {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldAuthorizeValidActiveUser() {
        Registration validActiveUser = Auth.validActiveUser();
        $("[data-test-id='login'] .input__control").setValue(validActiveUser.getLogin());
        $("[data-test-id='password'] .input__control").setValue(validActiveUser.getPassword());
        $("[data-test-id='action-login']").click();
        $(".heading").shouldHave(text("Личный кабинет"));
    }

    @Test
    void shouldNotAuthorizeValidBlockedUser() {
        Registration validBlockedUser = Auth.validBlockedUser();
        $("[data-test-id='login'] .input__control").setValue(validBlockedUser.getLogin());
        $("[data-test-id='password'] .input__control").setValue(validBlockedUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldNotAuthorizeWithInvalidLogin() {
        Registration invalidLogin = Auth.invalidLogin();
        $("[data-test-id='login'] .input__control").setValue(invalidLogin.getLogin());
        $("[data-test-id='password'] .input__control").setValue(invalidLogin.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldNotAuthorizeWithInvalidPassword() {
        Registration invalidPassword = Auth.invalidPassword();
        $("[data-test-id='login'] .input__control").setValue(invalidPassword.getLogin());
        $("[data-test-id='password'] .input__control").setValue(invalidPassword.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }
}
