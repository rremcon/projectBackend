package com.example.projectbackend.model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


class TicketTest {

    Ticket ticket;
    @BeforeEach
    void setUp() {

        ticket = new Ticket(1L, "EventnameExample", "TickettypeExample", "DaytypeExample", "LocationExample", LocalDate.now(), 170.0, null);
    }

    @AfterEach
    void tearDown() {
        ticket = null;
    }


    @Test
    void ticketIdIsCorrect() {

        Long result = ticket.getId();
        assertEquals(1L, result);
    }

    @Test
    void selectedEventNameIsCorrect() {

        String result = ticket.getEventname();
        assertEquals("EventnameExample", result);
    }

    @Test
    void selectedTicketTypeIsCorrect() {

        String result = ticket.getTickettype();
        assertEquals("TickettypeExample", result);
    }

    @Test
    void selectedDayTypeIsCorrect() {

        String result = ticket.getDaytype();
        assertEquals("DaytypeExample", result);
    }

    @Test
    void selectedLocationIsCorrect() {

        String result = ticket.getLocation();
        assertEquals("LocationExample", result);
    }

    @Test
    void priceIsCorrect() {

        Double result = ticket.getPrice();
        assertEquals(170.0, result);
    }

    @Test
    void checkTicket() {

        Long result1 = ticket.getId();
        String result2 = ticket.getEventname();
        String result3 = ticket.getTickettype();
        String result4 = ticket.getDaytype();
        String result5 = ticket.getLocation();
        double result6 = ticket.getPrice();

        assertEquals(1, result1);
        assertEquals("EventnameExample", result2);
        assertEquals("TickettypeExample", result3);
        assertEquals("DaytypeExample", result4);
        assertEquals("LocationExample", result5);
        assertEquals(170.0, result6);
    }

}