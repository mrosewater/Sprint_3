package ru.praktikum.scooter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAssuresService {

    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://qa-scooter.praktikum-services.ru/")
                .setContentType(ContentType.JSON)
                .build();
    }

}
