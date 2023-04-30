package com.example.projectbackend.service;
import com.example.projectbackend.dto.OrderDto;
import com.example.projectbackend.model.Account;
import com.example.projectbackend.model.Order;
import com.example.projectbackend.model.Ticket;
import com.example.projectbackend.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepos;
    @InjectMocks
    OrderService orderService;

    Order testOrder;
    OrderDto testOrderDto;

    @BeforeEach
    void setUp() {
        Account testAccount = null;
        Ticket testTicket = null;
        testOrder = new Order(1L, "testTicketExample", 3, 80.0, 240.0);
        testOrderDto = new Order(1L, "testTicketExample", 3, 80.0, 240.0);
    }

    @Test
    void createOrderCorrect() {

        Mockito.when(orderRepos.save(testOrder)).thenReturn((testOrder));
        orderService.createOrder( testOrderDto, 1L);

        assertEquals(testOrder.getOrderid(), testOrderDto.getOrderid());
        assertEquals(testOrder.getSelectedticket(), testOrderDto.getSelectedticket());
        assertEquals(testOrder.getQuantity(), testOrderDto.getQuantity());
        assertEquals(testOrder.getPrice(), testOrderDto.getPrice());
        assertEquals(testOrder.getTotalprice(), testOrderDto.getTotalprice());
    }

    @Test
    void ReturnOrderCorrect() {

        Mockito.when(orderRepos.findById(1L)).thenReturn(Optional.of(testOrder));
        orderService.getOrder(1L);

        assertEquals(testOrder.getOrderid(), testOrderDto.getOrderid());
        assertEquals(testOrder.getSelectedticket(), testOrderDto.getSelectedticket());
        assertEquals(testOrder.getQuantity(), testOrderDto.getQuantity());
        assertEquals(testOrder.getPrice(), testOrderDto.getPrice());
        assertEquals(testOrder.getTotalprice(), testOrderDto.getTotalprice());
    }

    @Test
    void getOrderById() {

        Mockito.when(orderRepos.findById(1L)).thenReturn(Optional.of(testOrder));
        orderService.getOrder(1L);

        assertEquals(testOrder.getOrderid(), testOrderDto.getOrderid());
    }

    @Test
    void deleteOrderById() {
        Mockito.when(orderRepos.existsById(1L)).thenReturn(true);
        Mockito.when(orderRepos.findById(1L)).thenReturn(Optional.of(testOrder));
        orderService.deleteOrder(1L);

        (orderRepos).delete(testOrder);
    }

    @Test
    void getOrders() {
        Mockito.when(orderRepos.findAll()).thenReturn(List.of(testOrder));

        List<Order> orderDtoList = orderService.toOrder(orderService.getOrders());

        assertEquals(testOrder.getOrderid(), orderDtoList.get(0).getOrderid());
        assertEquals(testOrder.getSelectedticket(), orderDtoList.get(0).getSelectedticket());
        assertEquals(testOrder.getQuantity(), orderDtoList.get(0).getQuantity());
        assertEquals(testOrder.getPrice(), orderDtoList.get(0).getPrice());
        assertEquals(testOrder.getTotalprice(), orderDtoList.get(0).getTotalprice());
        assertEquals(testOrder.getTicket(), orderDtoList.get(0).getTicket());
    }

}