package com.example.projectbackend.controller;
import com.example.projectbackend.dto.InvoiceDto;
import com.example.projectbackend.dto.OrderDto;
import com.example.projectbackend.model.Order;
import com.example.projectbackend.service.OrderService;
import com.example.projectbackend.utility.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    @PostMapping("/create")
        public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto, BindingResult br) {
        if (br.hasErrors()) {
            String error = Util.reportErrors(br);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        else {
            Long createdId = service.createOrder(orderDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/orders/create/" + createdId).toUriString());
            return ResponseEntity.created(uri).body("Order " + createdId + " confirmed!");
        }
    }


//    @PostMapping("/create")
//    public ResponseEntity<Long> createOrder(@RequestBody OrderDto orderDto) {
//        Long orderid = service.createOrder(orderDto);
//        return new ResponseEntity<>(orderid, HttpStatus.CREATED);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long id) {
        OrderDto orderDto = service.getOrder(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<Iterable<OrderDto>> getOrders() {
        return ResponseEntity.ok(service.getOrders());
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long id, @RequestBody OrderDto orderDto) {
        service.updateOrder(id, orderDto);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return service.saveOrder(order);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder (@PathVariable("id") Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/invoice")
    public ResponseEntity<InvoiceDto> getAmount(@PathVariable Long id) {
        InvoiceDto invoiceDto = new InvoiceDto();
//        invoiceDto.orderid = id;
        invoiceDto.amount = service.getAmount(id);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

}
