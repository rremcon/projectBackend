package com.example.projectbackend.controller;
import com.example.projectbackend.dto.OrderDto;
import com.example.projectbackend.filter.JwtRequestFilter;
import com.example.projectbackend.model.Order;
import com.example.projectbackend.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    OrderService orderService;

    Order testOrder1;
    Order testOrder2;

    OrderDto testOrderDto1;
    OrderDto testOrderDto2;

    @BeforeEach
    void setUp() {
        testOrder1 = new Order(1L, "1000", 3, 80.0, 240.0);
        testOrder2 = new Order(2L, "2000", 4, 130.0, 520.0);
        testOrderDto1 = new OrderDto(1L, "1000", 3, 80.0, 240.0);
        testOrderDto2 = new OrderDto(2L, "2000", 4, 130.0, 520.0);
    }


    @Test
//    @WithMockUser(username="testuser", authorities="USER")
    void getOrders() throws Exception {
        given(orderService.getOrders()).willReturn(List.of(testOrderDto1, testOrderDto2));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderid").value(1L))
                .andExpect(jsonPath("$[0].selecteditem").value("1000"))
                .andExpect(jsonPath("$[0].quantity").value(3))
                .andExpect(jsonPath("$[0].price").value(80))
                .andExpect(jsonPath("$[0].totalprice").value(240))

                .andExpect(jsonPath("$[1].orderid").value(2L))
                .andExpect(jsonPath("$[1].selecteditem").value("2000"))
                .andExpect(jsonPath("$[1].quantity").value(4))
                .andExpect(jsonPath("$[1].price").value(130))
                .andExpect(jsonPath("$[1].totalprice").value(520))
        ;
    }


    @Test
    void getOrder() throws Exception {
        given(orderService.getOrder(anyLong())).willReturn(testOrderDto1);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("orderid").value(1L))
                .andExpect(jsonPath("selecteditem").value("1000"))
                .andExpect(jsonPath("quantity").value(3))
                .andExpect(jsonPath("price").value(80))
                .andExpect(jsonPath("totalprice").value(240))
        ;
    }


    @Test
    void createOrder() throws Exception {
        given(orderService.createOrder(testOrderDto1, 1L)).willReturn(testOrderDto2.getOrderid());

        mockMvc.perform(MockMvcRequestBuilders.post("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testOrderDto1)))
                .andExpect(status().isCreated())
        ;
    }


    @Test
    void deleteOrder() throws Exception {
        mockMvc.perform(delete("/orders/1"))
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