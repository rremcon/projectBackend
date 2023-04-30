package com.example.projectbackend.model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;
    @BeforeEach
    void setUp() {

        order = new Order(1L, "selectedTicketExample", 2, 160, 320);
    }

    @AfterEach
    void tearDown() {
        order = null;
    }


    @Test
    void orderIdIsCorrect() {

        Long result = order.getOrderid();
        assertEquals(1, result);
    }

    @Test
    void selectedTicketNameIsCorrect() {

        String result = order.getSelectedticket();
        assertEquals("selectedTicketExample", result);
    }

    @Test
    void selectedQuantityIsCorrect() {

        Integer result = order.getQuantity();
        assertEquals(2, result);
    }

    @Test
    void priceIsCorrect() {

        Double result = order.getPrice();
        assertEquals(160, result);
    }

    @Test
    void shouldCalculateCorrectAmount() {

        double result = order.calculateAmount();
        assertEquals(320, result);
    }

    @Test
    void checkOrder() {

        Long result1 = order.getOrderid();
        String result2 = order.getSelectedticket();
        int result3 = order.getQuantity();
        double result4 = order.getPrice();
        double result5 = order.getTotalprice();


        assertEquals(1, result1);
        assertEquals("selectedTicketExample", result2);
        assertEquals(2, result3);
        assertEquals(160, result4);
        assertEquals(320, result5);
    }

}