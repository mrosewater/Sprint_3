package ru.praktikum.scooter;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class OrderListTest {

    private OrderService orderService = new OrderService();;

    @Test
    public void orderListTest() {
        ValidatableResponse response = orderService.getOrderList();
        response.assertThat().body("orders.size()", is(not(0)));
    }

}
