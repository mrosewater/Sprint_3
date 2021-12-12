package ru.praktikum.scooter;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ValidateCourierCreationTest {

    private final Courier courier;
    private final int expectedStatusCode;
    private final String expectedErrorMessage;

    public ValidateCourierCreationTest(Courier courier, int expectedStatusCode, String expectedErrorMessage) {
        this.courier = courier;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {Courier.getLoginOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getPasswordOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getLoginAndPassword(), 201, null}
        };
    }

    @Test
    public void invalidRequestTest() {
        ValidatableResponse response = new CourierService().create(courier);
        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");
        assertEquals("Status code is wrong.", statusCode, expectedStatusCode);
        assertEquals("Message is wrong.", errorMessage, expectedErrorMessage);
    }

}
