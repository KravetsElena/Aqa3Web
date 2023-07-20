package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardOrderFormTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void TestHappyPath() {

        SelenideElement form = $("[form].form_size_m .form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иван Соколов");
        $("[data-test-id=phone] input").setValue("+79092364751");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void TestValidationName() {

        SelenideElement form = $("[form].form_size_m .form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Ivan Sokolov");
        $("[data-test-id=phone] input").setValue("+79092364751");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void TestValidationNameNull() {

        SelenideElement form = $("[form].form_size_m .form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79092364751");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void TestValidationPhone() {

        SelenideElement form = $("[form].form_size_m .form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иван Соколов");
        $("[data-test-id=phone] input").setValue("879092364751");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void TestValidationPhoneNull() {

        SelenideElement form = $("[form].form_size_m .form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иван Соколов");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void TestValidationCheckbox() {

        SelenideElement form = $("[form].form_size_m .form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иван Соколов");
        $("[data-test-id=phone] input").setValue("+79092364751");
        $("[data-test-id=agreement]");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));

    }
}
