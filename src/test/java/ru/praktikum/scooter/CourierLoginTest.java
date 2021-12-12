package ru.praktikum.scooter;

import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierLoginTest {

    private Courier courier;
    private CourierService courierService;
    private int courierId;

    @Before
    public void setUp() {
        courierService = new CourierService();
        courier = Courier.getRandom();
        courierService.create(courier);
        courierId = courierService.login(CourierCredentials.from(courier)).extract().path("id");
    }

    @After
    public void tearDown() {
        courierService.delete(courierId);
    }

    @Test
    public void loginRequestReturnsSuccessTest() {
        ValidatableResponse response = courierService.login(CourierCredentials.from(courier));
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is wrong.", 200, statusCode);
    }
    @Test
    public void loginRequestReturnsIdTest() {
        ValidatableResponse response = courierService.login(CourierCredentials.from(courier));
        int id = response.extract().path("id");
        assertNotEquals("Id is not returned.", 0, id);
    }
    @Test
    public void errorOnWrongLoginTest() {
        courier.login = RandomStringUtils.randomAlphabetic(10);
        ValidatableResponse response = courierService.login(CourierCredentials.from(courier));
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is wrong.", 404, statusCode);
    }

    @Test
    public void errorOnWrongPasswordTest() {
        courier.password = RandomStringUtils.randomAlphabetic(10);
        ValidatableResponse response = courierService.login(CourierCredentials.from(courier));
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is wrong.", 404, statusCode);
    }

}
