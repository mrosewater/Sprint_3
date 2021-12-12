package ru.praktikum.scooter;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class OrderTest {

    private Order order;
    private OrderService orderService;
    private int orderTrack;

    @Before
    public void setUp() {
        orderService = new OrderService();
        order = Order.getRandom();
    }

    @After
    public void tearDown() {
        orderService.cancel(orderTrack);
    }

    @Test
    public void createRequestReturnsSuccessCodeTest() {
        ValidatableResponse response = orderService.create(order);
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is wrong.", 201, statusCode);
        orderTrack = response.extract().path("track");
    }

    @Test
    public void createRequestReturnsIdTest() {
        ValidatableResponse response = orderService.create(order);
        orderTrack = response.extract().path("track");
        assertNotEquals("Id is not returned.", 0, orderTrack);
    }

}
