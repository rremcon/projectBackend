package com.example.projectbackend.service;
import com.example.projectbackend.dto.OrderDto;
import com.example.projectbackend.model.Order;
import com.example.projectbackend.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepos;

    @InjectMocks
    OrderService orderService;

    @Test
    void shouldReturnCorrectOrder() {
        //arrange
        Order order = new Order(1L, "TicketExample",3, 0.00 );
        order.setOrderid(1L);

//        Mockito.when(orderRepos.findById(anyInt())).thenReturn(Optional.of(order));
        Mockito.when(orderRepos.findById((long) anyInt())).thenReturn(Optional.of(order));

        //act
        OrderDto orderDto = orderService.getOrder(1L);

        //assert
        assertEquals("TicketExample", orderDto.selectedticket);
        assertEquals(80, orderDto.price);
        assertEquals(3, orderDto.quantity);
        assertEquals(1, orderDto.orderid);

    }

}