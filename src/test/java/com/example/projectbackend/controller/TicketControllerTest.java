package com.example.projectbackend.controller;
import com.example.projectbackend.dto.TicketDto;
import com.example.projectbackend.service.JwtService;
import com.example.projectbackend.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(TicketController.class)
@AutoConfigureMockMvc(addFilters = false)
class TicketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;
    @MockBean
    TicketService ticketService;


    @Test
//    @WithMockUser(username="testuser", authorities="USER")
    void ReturnCorrectTicket() throws Exception {

        TicketDto ticketDto = new TicketDto();
        ticketDto.eventname = "EventExample";
        ticketDto.tickettype = "TicketTypeExample";
        ticketDto.daytype = "DayTypeExample";
        ticketDto.location = "LocationExample";
        ticketDto.eventdate = "EventdateExample";
        ticketDto.price = 300.0;

        Mockito.when(ticketService.getTicket(1L)).thenReturn(ticketDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/tickets/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.eventname", ("EventExample")))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.tickettype", ("TicketTypeExample")))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.daytype", ("DayTypeExample")))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.location", ("LocationExample")))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.eventdate", ("EventdateExample")))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.price", (300.0)));
    }

}