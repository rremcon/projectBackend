package com.example.projectbackend.service;
import com.example.projectbackend.dto.OrderDto;
import com.example.projectbackend.model.Account;
import com.example.projectbackend.model.Order;
import com.example.projectbackend.model.Product;
import com.example.projectbackend.model.Ticket;
import com.example.projectbackend.repository.AccountRepository;
import com.example.projectbackend.repository.OrderRepository;
import com.example.projectbackend.repository.ProductRepository;
import com.example.projectbackend.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepos;
    @Mock
    AccountRepository accountRepository;
    @Mock
    TicketRepository ticketRepository;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    OrderService orderService;
    @Captor
    ArgumentCaptor<Order> captor;

    Order testOrder1;
    Order testOrder2;
    OrderDto testOrderDto1;
    OrderDto testOrderDto2;

    Account testAccount;
    Ticket testTicket;
    Product testProduct;

    @BeforeEach
    void setUp() {

        testOrder1 = new Order(1L, "1000", 3, 80.0, 240.0);
        testOrder2 = new Order(2L, "2000", 4, 130.0, 520.0);
        testOrderDto1 = new OrderDto(1L, "1000", 3, 80.0, 240.0);
        testOrderDto2 = new OrderDto(2L, "2000", 4, 130.0, 520.0);

        testAccount = new Account(1L, "testFirstname", "testLastname", LocalDate.now(), "testAddress", 79326, "testCity", "testCountry", "testMail", "testUsername", "testPassword");
        testTicket = new Ticket(1L, "testEventname", "testTickettype", "testDaytype", "testLocation", LocalDate.now(), 140.0, testOrder1);
        testProduct = new Product(1L, "testImage", "testTitle", "testDescription", 140.0, testOrder1);
    }

    @Test
    void createOrder() {

        Mockito.when(orderRepos.save(any(Order.class))).thenReturn((testOrder1));
//        Mockito.when(orderRepos.save(testOrder1)).thenReturn((testOrder1));
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable((testAccount)));
        Mockito.when(ticketRepository.findById(1000L)).thenReturn(Optional.ofNullable((testTicket)));
        Mockito.when(productRepository.findById(1000L)).thenReturn(Optional.ofNullable((testProduct)));

        orderService.createOrder( testOrderDto1, 1L);

        assertEquals(testOrder1.getOrderid(), testOrderDto1.getOrderid());
        assertEquals(testOrder1.getSelecteditem(), testOrderDto1.getSelecteditem());
        assertEquals(testOrder1.getQuantity(), testOrderDto1.getQuantity());
        assertEquals(testOrder1.getPrice(), testOrderDto1.getPrice());
        assertEquals(testOrder1.getTotalprice(), testOrderDto1.getTotalprice());
    }

    @Test
    void getOrder() {

        Mockito.when(orderRepos.findById(2L)).thenReturn(Optional.of(testOrder2));

        orderService.getOrder(2L);

        assertEquals(testOrder2.getOrderid(), testOrderDto2.getOrderid());
    }

    @Test
    void getFalseOrder() {

        Mockito.when(orderRepos.findById(2L)).thenReturn(Optional.empty());

        orderService.getOrder(2L);
        OrderDto orderDto = orderService.getOrder(2L);

        assertNull(orderDto);
    }

    @Test
    void ReturnOrderCorrect() {

        Mockito.when(orderRepos.findById(1L)).thenReturn(Optional.of(testOrder1));
        orderService.getOrder(1L);

        assertEquals(testOrder1.getOrderid(), testOrderDto1.getOrderid());
        assertEquals(testOrder1.getSelecteditem(), testOrderDto1.getSelecteditem());
        assertEquals(testOrder1.getQuantity(), testOrderDto1.getQuantity());
        assertEquals(testOrder1.getPrice(), testOrderDto1.getPrice());
        assertEquals(testOrder1.getTotalprice(), testOrderDto1.getTotalprice());
    }

    @Test
    void getOrders() {
        Mockito.when(orderRepos.findAll()).thenReturn(List.of(testOrder1, testOrder2));

        List<OrderDto> orderDtoList = orderService.getOrders();

        assertEquals(testOrder1.getOrderid(), orderDtoList.get(0).getOrderid());
        assertEquals(testOrder1.getSelecteditem(), orderDtoList.get(0).getSelecteditem());
        assertEquals(testOrder1.getQuantity(), orderDtoList.get(0).getQuantity());
        assertEquals(testOrder1.getPrice(), orderDtoList.get(0).getPrice());
        assertEquals(testOrder1.getTotalprice(), orderDtoList.get(0).getTotalprice());

        assertEquals(testOrder2.getOrderid(), orderDtoList.get(1).getOrderid());
        assertEquals(testOrder2.getSelecteditem(), orderDtoList.get(1).getSelecteditem());
        assertEquals(testOrder2.getQuantity(), orderDtoList.get(1).getQuantity());
        assertEquals(testOrder2.getPrice(), orderDtoList.get(1).getPrice());
        assertEquals(testOrder2.getTotalprice(), orderDtoList.get(1).getTotalprice());
    }

    @Test
    void updateOrder() {

        when(orderRepos.findById(1L)).thenReturn(Optional.of(testOrder1));
        when(orderRepos.existsById(1L)).thenReturn(true);

        orderService.updateOrder(1L, testOrderDto1);

        verify(orderRepos, times(1)).save(captor.capture());

        Order captured = captor.getValue();

        assertEquals(testOrder1.getPrice(), captured.getPrice());
    }

    @Test
    void deleteOrder() {

        orderService.deleteOrder(1L);

        verify(orderRepos).deleteById(1L);
    }

}