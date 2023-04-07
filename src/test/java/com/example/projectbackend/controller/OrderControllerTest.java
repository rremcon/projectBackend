package com.example.projectbackend.controller;
import com.example.projectbackend.dto.OrderDto;
import com.example.projectbackend.service.JwtService;
import com.example.projectbackend.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;


//@WebMvcTest(OrderControllerTest.class)
@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    OrderService orderService;

    @Test
//    @WithMockUser(username="testuser", authorities="USER")
    void shouldRetrieveCorrectOrder() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.selectedticket = "TicketExample";
        orderDto.price = 300;
        orderDto.quantity = 2;

        Mockito.when(orderService.getOrder(1L)).thenReturn(orderDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.selectedticket", is("TicketExample")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(300)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(2)));
    }


    @Test
//    @WithMockUser(username="testuser", authorities="USER")
    void shouldCalculateCorrectOrderAmount() throws Exception {

        Mockito.when(orderService.getAmount(1L)).thenReturn(300.0);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/1/invoice"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", is(300.0)));
    }

}