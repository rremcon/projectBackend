package com.example.projectbackend.controller;
import com.example.projectbackend.dto.TicketDto;
import com.example.projectbackend.service.TicketService;
import com.example.projectbackend.utility.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createTicket(@RequestBody TicketDto ticketDto, BindingResult br) {
        if (br.hasErrors()) {
            String error = Util.reportErrors(br);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        else {
            Long createdId = ticketService.createTicket(ticketDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/tickets/create/" + createdId).toUriString());
            return ResponseEntity.created(uri).body("Ticket " + createdId + " created!");
        }
    }


//    @GetMapping("/{id}")
//    @Transactional
//    public ResponseEntity<TicketDto> getTicket(@PathVariable Long id) {
//        return ResponseEntity.ok(ticketService.getTicket(id));
//    }


    @GetMapping( "/{id}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable("id") Long id) {
        TicketDto optionalTicket = ticketService.getTicket(id);
        return ResponseEntity.ok().body(optionalTicket);
    }


    @GetMapping("")
    public ResponseEntity<Iterable<TicketDto>> getTickets() {
        return ResponseEntity.ok(ticketService.getTickets());
    }


    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable("id") Long id, @RequestBody TicketDto ticketDto) {
        ticketService.updateTicket(id, ticketDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTicket (@PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }



//        @GetMapping("/aftereventdate")
//    public ResponseEntity<Iterable<Ticket>> getTicketsEventdate(
//            @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date) {
//        return ResponseEntity.ok(repos.findByEventdate(date));
//    }
//
//
//
//        @GetMapping("/findbyeventname")
//    public ResponseEntity<Iterable<Ticket>> getTicketsContaining(
//            @RequestParam String query) {
//        return ResponseEntity.ok(repos.findByEventNameContaining(query));
//    }


}
