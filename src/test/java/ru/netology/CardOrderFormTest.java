package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardOrderFormTest {
    @Test
    void TestHappyPath(){
        open("http://localhost:9999");
        $("[data-test-id=name] input"). setValue("Иван Соколов");
        $("[data-test-id=phone] input"). setValue("+79092364751");
        $("[data-test-id=agreement]"). click();
        $(".button"). click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
    @Test
    void TestValidation(){
        open("http://localhost:9999");
        $("[data-test-id=name] input"). setValue("Ivan Sokolov");
        $("[data-test-id=phone] input"). setValue("+79092364751");
        $("[data-test-id=agreement]"). click();
        $(".button"). click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }

}
