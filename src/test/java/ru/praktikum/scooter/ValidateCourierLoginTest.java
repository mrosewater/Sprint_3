package ru.praktikum.scooter;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ValidateCourierLoginTest {

    private final Courier courier;
    private final int expectedStatusCode;
    private final String expectedErrorMessage;

    public ValidateCourierLoginTest(Courier courier, int expectedStatusCode, String expectedErrorMessage) {
        this.courier = courier;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {Courier.getLoginOnly(), 400, "Недостаточно данных для входа"},
                {Courier.getPasswordOnly(), 400, "Недостаточно данных для входа"}
        };
    }

    @Test
    public void invalidRequestTest() {
        ValidatableResponse response = new CourierService().login(CourierCredentials.from(courier));
        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");
        assertEquals("Status code is wrong.", statusCode, expectedStatusCode);
        assertEquals("Error message is wrong.", errorMessage, expectedErrorMessage);
    }

}
