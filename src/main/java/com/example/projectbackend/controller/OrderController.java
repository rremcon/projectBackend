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
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create/{accountId}")
        public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable Long accountId,BindingResult br) {
        if (br.hasErrors()) {
            String error = Util.reportErrors(br);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        else {
            Long createdId = service.createOrder(orderDto, accountId);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/orders/create/" + createdId).toUriString());
            return ResponseEntity.created(uri).body("Order " + createdId + " confirmed!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long id) {
        OrderDto orderDto = service.getOrder(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<OrderDto>> getOrders() {
        return ResponseEntity.ok(service.getOrders());
    }

    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return service.saveOrder(order);
    }

        @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long orderid, @RequestBody OrderDto orderDto) {
        service.updateOrder(orderid, orderDto);
        return ResponseEntity.noContent().build();
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
