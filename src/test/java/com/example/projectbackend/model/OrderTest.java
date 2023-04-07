package com.example.projectbackend.model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;
    @BeforeEach
    void SetUp() {
        order = new Order(1L, "selectedTicketExample", 2, 0.00);
    }

    @AfterEach
    void tear() {
        order = null;
    }

    @Test
    void selectedTicketNameIsCorrect() {

        //arrange
//        Order order = new Order("selectedTicketExample",160, 2);

        //act
        String result = order.getSelectedticket();

        //assert
        assertEquals("selectedTicketExample", result);
    }


    @Test
    void checkOrder() {

        String result1 = order.getSelectedticket();
        double result2 = order.getPrice();
        int result3 = order.getQuantity();

        assertEquals("selectedTicketExample", result1);
        assertEquals(160, result2);
        assertEquals(2, result3);
    }


    @Test
    void shouldCalculateCorrectAmount() {

        double result = order.calculateAmount();

        assertEquals(320, result);
    }

}