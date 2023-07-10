package com.example.projectbackend.integration;
import com.example.projectbackend.dto.TicketDto;
import com.example.projectbackend.model.Order;
import com.example.projectbackend.model.Ticket;
import com.example.projectbackend.repository.TicketRepository;
import com.example.projectbackend.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class IntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TicketService ticketService;
    @Autowired
    TicketRepository ticketRepository;

    Ticket testTicket1;
    Ticket testTicket2;
    TicketDto testTicketDto1;
    TicketDto testTicketDto2;

    Order testOrder1;
    Order testOrder2;

    @BeforeEach
    public void setUp() {
        testTicket1 = new Ticket(1L, "F1", "Regular", "Friday", "Zandvoort", LocalDate.now(), 90.0, testOrder1);
        testTicket2 = new Ticket(2L, "MGP", "Silver", "Saturday", "Assen", LocalDate.now(), 140.0, testOrder2);

        testTicketDto1 = new TicketDto(testTicket1.getId(), "F1", "Regular", "Friday", "Zandvoort", LocalDate.now().toString(), 90.0);
        testTicketDto2 = new TicketDto(testTicket2.getId(), "MGP", "Silver", "Saturday", "Assen", LocalDate.now().toString(), 140.0);

        ticketRepository.save(testTicket1);
        ticketRepository.save(testTicket2);
    }

    @Test
    void getTicket() throws Exception {

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testTicket2.getId()))
                .andExpect(jsonPath("$[0].eventname").value(testTicket2.getEventname()))
                .andExpect(jsonPath("$[0].tickettype").value(testTicket2.getTickettype()))
                .andExpect(jsonPath("$[0].daytype").value(testTicket2.getDaytype()))
                .andExpect(jsonPath("$[0].location").value(testTicket2.getLocation()))
                .andExpect(jsonPath("$[0].eventdate").value(testTicket2.getEventdate().toString()))
                .andExpect(jsonPath("$[0].price").value(testTicket2.getPrice()));
    }


    @Test
    void deleteTicket() throws Exception {
        mockMvc.perform(delete("/tickets/" + testTicket1.getId().toString()))
                .andExpect(status().isNoContent());
    }


    public static String asJsonString(final TicketDto obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
