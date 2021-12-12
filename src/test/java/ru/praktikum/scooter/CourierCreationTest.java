package ru.praktikum.scooter;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CourierCreationTest {

    private Courier courier;
    private CourierService courierService;
    private int courierId;

    @Before
    public void setUp() {
        courierService = new CourierService();
        courier = Courier.getRandom();
    }

    @After
    public void tearDown() {
        courierService.delete(courierId);
    }

    @Test
    public void createRequestReturnsSuccessCodeTest() {
        ValidatableResponse response = courierService.create(courier);
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is wrong.", 201, statusCode);
        courierId = courierService.login(CourierCredentials.from(courier)).extract().path("id");
    }

    @Test
    public void createRequestReturnsSuccessMessageTest() {
        ValidatableResponse response = courierService.create(courier);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created.", isCreated);
        courierId = courierService.login(CourierCredentials.from(courier)).extract().path("id");
    }

    @Test
    public void sameCourierCannotBeCreatedTwiceTest() {
        courierService.create(courier);
        ValidatableResponse response = courierService.create(courier);
        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");
        assertEquals("Status code is wrong.", 409, statusCode);
        assertEquals ("Error message is wrong.", "Этот логин уже используется. Попробуйте другой.", errorMessage);
        courierId = courierService.login(CourierCredentials.from(courier)).extract().path("id");
    }

}