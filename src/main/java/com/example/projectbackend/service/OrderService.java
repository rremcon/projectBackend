package com.example.projectbackend.service;
import com.example.projectbackend.dto.OrderDto;
import com.example.projectbackend.exceptions.RecordNotFoundException;
import com.example.projectbackend.model.*;
import com.example.projectbackend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepos;
    private final AccountRepository accountRepository;
    private final TicketRepository ticketRepository;
    private final ProductRepository productRepository;
    public OrderService(OrderRepository repos, AccountRepository accountRepository, TicketRepository ticketRepository, ProductRepository productRepository) {
        this.orderRepos = repos;
        this.accountRepository = accountRepository;
        this.ticketRepository = ticketRepository;
        this.productRepository = productRepository;
    }

    public Long createOrder(OrderDto orderDto, Long accountId) {

        Long ticketId = Long.parseLong( orderDto.selecteditem);
        Long productId = Long.parseLong( orderDto.selecteditem);

        Order order = new Order();

        Optional <Account> optionalAccount = accountRepository.findById(accountId);
        Optional <Ticket> optionalTicket = ticketRepository.findById(ticketId);
        Optional <Product> optionalProduct = productRepository.findById(productId);

        order = toOrder(orderDto);

         if(optionalAccount.isPresent()) {
             order.setAccount(optionalAccount.get());
         }

        if(optionalTicket.isPresent()) {
            order.setTicket(optionalTicket.get());
        }

        if(optionalProduct.isPresent()) {
            order.setProduct(optionalProduct.get());
        }

        Order savedOrder = orderRepos.save(order);
        return savedOrder.getOrderid();
    }

    public OrderDto getOrder(Long orderid) {
        Optional<Order> orderr = orderRepos.findById(orderid);
        if (orderr.isPresent()) {

            Order order = orderr.get();
            OrderDto orderDto = new OrderDto();
            orderDto.orderid = order.getOrderid();
            orderDto.selecteditem = order.getSelecteditem();
            orderDto.quantity = order.getQuantity();
            orderDto.price = order.getPrice();
            orderDto.totalprice = order.getTotalprice();

            return orderDto;
        }
        return null;
    }

    public List<OrderDto> getOrders() {
        List<Order> allOrders = orderRepos.findAll();
        ArrayList<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : allOrders) {

            orderDtoList.add(fromOrder(order));
        }
        return orderDtoList;
    }

    public void updateOrder(Long id, OrderDto newOrder) {
        if (!orderRepos.existsById(id)) throw new RecordNotFoundException();

        Order order = orderRepos.findById(id).get();
        order.setOrderid(newOrder.getOrderid());
        order.setSelecteditem(newOrder.getSelecteditem());
        order.setQuantity(newOrder.getQuantity());
        order.setPrice(newOrder.getPrice());
        order.setTotalprice(newOrder.getTotalprice());

        orderRepos.save(order);
    }

    public void deleteOrder(@RequestBody Long id) {
        orderRepos.deleteById(id);
    }
    public static OrderDto fromOrder(Order order){

        var orderDto = new OrderDto();
        orderDto.orderid = order.getOrderid();
        orderDto.selecteditem = order.getSelecteditem();
        orderDto.quantity = order.getQuantity();
        orderDto.price = order.getPrice();
        orderDto.totalprice = order.getTotalprice();

        return orderDto;
    }

    public Order toOrder(OrderDto orderDto) {

        var order = new Order();
        order.setOrderid(orderDto.getOrderid());
        order.setSelecteditem(orderDto.getSelecteditem());
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(orderDto.getPrice());
        order.setTotalprice(orderDto.getTotalprice());

        return order;
    }

}

