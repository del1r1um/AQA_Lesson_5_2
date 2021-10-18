package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.*;

public class Auth {
    private static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void setUpAll(Registration registration) {
        given()
                .spec(requestSpecification)
                .body(registration)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static Registration validActiveUser() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.internet().emailAddress();
        String password = faker.internet().password();
        String status = "active";
        Registration registrationDto = new Registration(login, password, status);
        setUpAll(registrationDto);
        return registrationDto;
    }

    public static Registration validBlockedUser() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.internet().emailAddress();
        String password = faker.internet().password();
        String status = "blocked";
        Registration registrationDto = new Registration(login, password, status);
        setUpAll(registrationDto);
        return registrationDto;
    }

    public static Registration invalidLogin() {
        Faker faker = new Faker(new Locale("en"));
        String login = "steve.jobs@apple.com";
        String password = faker.internet().password();
        String status = "active";
        setUpAll(new Registration(login, password, status));
        return new Registration("tim.cook@apple.com", password, status);
    }

    public static Registration invalidPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.internet().emailAddress();
        String password = "12345";
        String status = "active";
        setUpAll(new Registration(login, password, status));
        return new Registration(login, "54321", status);
    }
}
