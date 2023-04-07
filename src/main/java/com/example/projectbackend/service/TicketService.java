package com.example.projectbackend.service;
import com.example.projectbackend.dto.TicketDto;
import com.example.projectbackend.exceptions.RecordNotFoundException;
import com.example.projectbackend.model.Ticket;
import com.example.projectbackend.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Long createTicket(TicketDto ticketDto) {

        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.id);
        ticket.setEventname(ticketDto.eventname);
        ticket.setTickettype(ticketDto.tickettype);
        ticket.setDaytype(ticketDto.daytype);
        ticket.setLocation(ticketDto.location);
        ticket.setEventdate(ticketDto.eventdate);
        ticket.setPrice(ticketDto.price);

        Ticket savedTicket = ticketRepository.save(toTicket(ticketDto));
        return savedTicket.getId();
    }


    public TicketDto getTicket(Long id) {
        TicketDto ticketDto = new TicketDto();
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticketDto = fromTicket(ticket.get());
        } else {
            throw new RecordNotFoundException();
        }
        return ticketDto;
    }


    public Iterable<TicketDto> getTickets() {
        Iterable<Ticket> allTickets = ticketRepository.findAll();
        ArrayList<TicketDto> ticketDtoList = new ArrayList<>();
        for (Ticket ticket : allTickets) {

            TicketDto ticketDto = new TicketDto();
            ticketDto.id = ticket.getId();
            ticketDto.eventname = ticket.getEventname();
            ticketDto.tickettype = ticket.getTickettype();
            ticketDto.daytype = ticket.getDaytype();
            ticketDto.eventdate = ticket.getEventdate();
            ticketDto.location = ticket.getLocation();
            ticketDto.price = ticket.getPrice();

            ticketDtoList.add(ticketDto);
        }
        return ticketDtoList;
    }


    public void updateTicket(Long id, TicketDto newTicket) {
        if (!ticketRepository.existsById(id)) throw new RecordNotFoundException();
        Ticket ticket = ticketRepository.findById(id).get();

        ticket.setId(newTicket.getId());
        ticket.setEventname(newTicket.getEventname());
        ticket.setTickettype(newTicket.getTickettype());
        ticket.setDaytype(newTicket.getDaytype());
        ticket.setLocation(newTicket.getLocation());
        ticket.setEventdate(newTicket.getEventdate());
        ticket.setPrice(newTicket.getPrice());

        ticketRepository.save(ticket);
    }

    public void deleteTicket(@RequestBody Long id) {
        ticketRepository.deleteById(id);
    }

    public static TicketDto fromTicket(Ticket ticket){

        TicketDto ticketDto = new TicketDto();
        ticketDto.id = ticket.getId();
        ticketDto.eventname = ticket.getEventname();
        ticketDto.tickettype = ticket.getTickettype();
        ticketDto.daytype = ticket.getDaytype();
        ticketDto.location = ticket.getLocation();
        ticketDto.eventdate = ticket.getEventdate();
        ticketDto.price = ticket.getPrice();

        return ticketDto;
    }

    public Ticket toTicket(TicketDto ticketDto) {

        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setEventname(ticketDto.getEventname());
        ticket.setTickettype(ticketDto.getTickettype());
        ticket.setDaytype(ticketDto.getDaytype());
        ticket.setLocation(ticketDto.getLocation());
        ticket.setEventdate(ticketDto.getEventdate());
        ticket.setPrice(ticketDto.getPrice());

        return ticket;
    }

}



