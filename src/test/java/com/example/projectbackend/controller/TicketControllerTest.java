package com.example.projectbackend.controller;
import com.example.projectbackend.dto.TicketDto;
import com.example.projectbackend.filter.JwtRequestFilter;
import com.example.projectbackend.model.Order;
import com.example.projectbackend.model.Ticket;
import com.example.projectbackend.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
//@ActiveProfiles("test")
class TicketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    TicketService ticketService;

    Ticket testTicket1;
    Ticket testTicket2;

    TicketDto testTicketDto1;
    TicketDto testTicketDto2;

    @BeforeEach
    void setUp() {
        Order order1 = null;
        Order order2 = null;
        testTicket1 = new Ticket(1L, "F1", "Regular", "Friday", "Zandvoort", LocalDate.now(), 90.0, order1);
        testTicket2 = new Ticket(2L, "MGP", "Silver", "Saturday", "Assen", LocalDate.now(), 140.0, order2);
        testTicketDto1 = new TicketDto(1L, "F1", "Regular", "Friday", "Zandvoort", LocalDate.now().toString(), 90.0);
        testTicketDto2 = new TicketDto(2L, "MGP", "Silver", "Saturday", "Assen", LocalDate.now().toString(), 140.0);
    }



    @Test
//    @WithMockUser(username="testuser", authorities="USER")
    void getTickets() throws Exception {
        given(ticketService.getTickets()).willReturn(List.of(testTicketDto1, testTicketDto2));

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].eventname").value("F1"))
                .andExpect(jsonPath("$[0].tickettype").value("Regular"))
                .andExpect(jsonPath("$[0].daytype").value("Friday"))
                .andExpect(jsonPath("$[0].location").value("Zandvoort"))
                .andExpect(jsonPath("$[0].price").value(90))

                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].eventname").value("MGP"))
                .andExpect(jsonPath("$[1].tickettype").value("Silver"))
                .andExpect(jsonPath("$[1].daytype").value("Saturday"))
                .andExpect(jsonPath("$[1].location").value("Assen"))
                .andExpect(jsonPath("$[1].price").value(140))
        ;
    }


    @Test
    void getTicket() throws Exception {
        given(ticketService.getTicket(anyLong())).willReturn(testTicketDto1);

        mockMvc.perform(get("/tickets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("eventname").value("F1"))
                .andExpect(jsonPath("tickettype").value("Regular"))
                .andExpect(jsonPath("daytype").value("Friday"))
                .andExpect(jsonPath("location").value("Zandvoort"))
                .andExpect(jsonPath("price").value(90))
        ;
    }


    @Test
    void createTicket() throws Exception {
        given(ticketService.createTicket(testTicketDto1)).willReturn(testTicketDto2.getId());

        mockMvc.perform(MockMvcRequestBuilders.post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testTicketDto1)))
                .andExpect(status().isCreated())
        ;
    }


    @Test
    void deleteTicket() throws Exception {
        mockMvc.perform(delete("/tickets/1"))
                .andExpect(status().isNoContent());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}