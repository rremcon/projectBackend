package com.example.projectbackend.service;
import com.example.projectbackend.dto.TicketDto;
import com.example.projectbackend.exceptions.RecordNotFoundException;
import com.example.projectbackend.model.Ticket;
import com.example.projectbackend.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Long createTicket(TicketDto ticketDto) {

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

    public List<TicketDto> getTickets() {
        List<Ticket> allTickets = ticketRepository.findAll();
        ArrayList<TicketDto> ticketDtoList = new ArrayList<>();
        for (Ticket ticket : allTickets) {

            ticketDtoList.add(fromTicket(ticket));
        }
        return ticketDtoList;
    }

    public void updateTicket(Long id, TicketDto newTicket) {
        if (!ticketRepository.existsById(id)) throw new RecordNotFoundException();
        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setPrice(newTicket.price);
        ticketRepository.save(ticket);
    }

    public void deleteTicket(@RequestBody Long id) {
        ticketRepository.deleteById(id);
    }
    public static TicketDto fromTicket(Ticket ticket){

        LocalDate localDate = ticket.getEventdate();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = localDate.format(formatter);

        TicketDto ticketDto = new TicketDto();
        ticketDto.id = ticket.getId();
        ticketDto.eventname = ticket.getEventname();
        ticketDto.tickettype = ticket.getTickettype();
        ticketDto.daytype = ticket.getDaytype();
        ticketDto.location = ticket.getLocation();
        ticketDto.eventdate = formattedString;
        ticketDto.price = ticket.getPrice();

        return ticketDto;
    }

    public Ticket toTicket(TicketDto ticketDto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = ticketDto.getEventdate();

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setEventname(ticketDto.getEventname());
        ticket.setTickettype(ticketDto.getTickettype());
        ticket.setDaytype(ticketDto.getDaytype());
        ticket.setLocation(ticketDto.getLocation());
        ticket.setEventdate(localDate);
        ticket.setPrice(ticketDto.getPrice());

        return ticket;
    }

}
