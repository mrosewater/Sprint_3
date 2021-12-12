package ru.praktikum.scooter;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderService extends RestAssuresService {

    private static final String ORDER_PATH = "/api/v1/orders/";

    @Step
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step
    public ValidatableResponse getOrderList() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }

    public boolean cancel(int orderTrack) {
        return given()
                .spec(getBaseSpec())
                .when()
                .put(ORDER_PATH + "cancel/?track=" + orderTrack)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }
}
