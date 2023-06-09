package com.example.projectbackend.service;
import com.example.projectbackend.dto.OrderDto;
import com.example.projectbackend.exceptions.RecordNotFoundException;
import com.example.projectbackend.model.Account;
import com.example.projectbackend.model.Order;
import com.example.projectbackend.repository.AccountRepository;
import com.example.projectbackend.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepos;
    private final AccountRepository accountRepository;
    public OrderService(OrderRepository repos, AccountRepository accountRepository) {
        this.orderRepos = repos;
        this.accountRepository = accountRepository;
    }

    public Long createOrder(OrderDto orderDto, Long accountId) {

        Order order = new Order();
        Account account =  accountRepository.getReferenceById(accountId);
        order.setOrderid(orderDto.orderid);
        order.setSelectedticket(orderDto.selectedticket);
        order.setQuantity(orderDto.quantity);
        order.setPrice(orderDto.price);
        order.setTotalprice(orderDto.getQuantity() * orderDto.getPrice());

        order.setAccount(account);
        order.setTicket(orderDto.ticket);

        Order savedOrder = orderRepos.save(toOrder(orderDto));
//        addTicketToOrder(orderDto, savedOrder);

        return savedOrder.getOrderid();
    }

    public OrderDto getOrder(Long orderid) {
        Optional<Order> orderr = orderRepos.findById(orderid);
        if (orderr.isPresent()) {

            Order order = orderr.get();
            OrderDto orderDto = new OrderDto();
            orderDto.orderid = order.getOrderid();
            orderDto.selectedticket = order.getSelectedticket();
            orderDto.quantity = order.getQuantity();
            orderDto.price = order.getPrice();
            orderDto.totalprice = order.getTotalprice();

            orderDto.ticket = order.getTicket();

            return orderDto;
        }
        return null;
    }

    public double getAmount(Long id) {
        Optional<Order> orderr = orderRepos.findById(id);
        if (orderr.isPresent()) {
            Order order = orderr.get();
            return order.calculateAmount();
        }
        return 0;
    }

    public Iterable<OrderDto> getOrders() {
        Iterable<Order> allOrders = orderRepos.findAll();
        ArrayList<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : allOrders) {

            OrderDto orderDto = new OrderDto();
            orderDto.orderid = order.getOrderid();
            orderDto.selectedticket = order.getSelectedticket();
            orderDto.quantity = order.getQuantity();
            orderDto.price = order.getPrice();
            orderDto.totalprice = order.getTotalprice();

            orderDto.ticket = order.getTicket();

            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    public Order saveOrder(Order order) {
        return orderRepos.save(order);
    }

    public void updateOrder(Long id, OrderDto newOrder) {
        if (!orderRepos.existsById(id)) throw new RecordNotFoundException();

        Order order = orderRepos.findById(id).get();
        order.setOrderid(newOrder.getOrderid());
        order.setSelectedticket(newOrder.getSelectedticket());
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
        orderDto.selectedticket = order.getSelectedticket();
        orderDto.quantity = order.getQuantity();
        orderDto.price = order.getPrice();
        orderDto.totalprice = order.getTotalprice();

        orderDto.ticket = order.getTicket();

        return orderDto;
    }

    public Order toOrder(OrderDto orderDto) {

        var order = new Order();
        order.setOrderid(orderDto.getOrderid());
        order.setSelectedticket(orderDto.getSelectedticket());
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(orderDto.getPrice());
        order.setTotalprice(orderDto.getTotalprice());

        order.setTicket(orderDto.getTicket());

        return order;
    }

}

