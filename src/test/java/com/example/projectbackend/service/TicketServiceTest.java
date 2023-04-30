package com.example.projectbackend.service;
import com.example.projectbackend.dto.TicketDto;
import com.example.projectbackend.model.Order;
import com.example.projectbackend.model.Ticket;
import com.example.projectbackend.repository.TicketRepository;
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
class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;
    @InjectMocks
    TicketService ticketService;

    Ticket testTicket;
    TicketDto testTicketDto;


    @BeforeEach
    void setUp() {
        Order testOrder = null;
        testTicket = new Ticket(1L, "testEventExample", "Silver", "Sunday", "Zandvoort", 2/12/2002, 140.0, testOrder);
        testTicketDto = new Ticket(1L, "testEventExample", "Silver", "Sunday", "Zandvoort", 12-2-2002, 140.0, testOrder);
    }


    @Test
    void createTicketCorrect() {

       Mockito.when(ticketRepository.save(testTicket)).thenReturn((testTicket));
       ticketService.createTicket(testTicketDto);

        assertEquals(testTicket.getId(), testTicketDto.getId());
        assertEquals(testTicket.getEventname(), testTicketDto.getEventname());
        assertEquals(testTicket.getTickettype(), testTicketDto.getTickettype());
        assertEquals(testTicket.getDaytype(), testTicketDto.getDaytype());
        assertEquals(testTicket.getLocation(), testTicketDto.getLocation());
        assertEquals(testTicket.getEventdate(), testTicketDto.getEventdate());
        assertEquals(testTicket.getPrice(), testTicketDto.getPrice());
    }


    @Test
    void ReturnTicketCorrect() {

        Mockito.when(ticketRepository.findById(1L)).thenReturn(Optional.of(testTicket));
        ticketService.getTicket(1L);

        assertEquals(testTicket.getId(), testTicketDto.getId());
        assertEquals(testTicket.getEventname(), testTicketDto.getEventname());
        assertEquals(testTicket.getTickettype(), testTicketDto.getTickettype());
        assertEquals(testTicket.getDaytype(), testTicketDto.getDaytype());
        assertEquals(testTicket.getLocation(), testTicketDto.getLocation());
        assertEquals(testTicket.getEventdate(), testTicketDto.getEventdate());
        assertEquals(testTicket.getPrice(), testTicketDto.getPrice());
    }


    @Test
    void getTicketById() {

        Mockito.when(ticketRepository.findById(1L)).thenReturn(Optional.of(testTicket));
        ticketService.getTicket(1L);

        assertEquals(testTicket.getId(), testTicketDto.getId());
    }


    @Test
    void deleteTicketById() {
        Mockito.when(ticketRepository.existsById(1L)).thenReturn(true);
        Mockito.when(ticketRepository.findById(1L)).thenReturn(Optional.of(testTicket));
        ticketService.deleteTicket(1L);

        (ticketRepository).delete(testTicket);
    }


    @Test
    void getTickets() {
        Mockito.when(ticketRepository.findAll()).thenReturn(List.of(testTicket));

        List<Ticket> ticketDtoList = ticketService.toTicket(ticketService.getTickets());

        assertEquals(testTicket.getId(), ticketDtoList.get(0).getId());
        assertEquals(testTicket.getEventname(), ticketDtoList.get(0).getEventname());
        assertEquals(testTicket.getTickettype(), ticketDtoList.get(0).getTickettype());
        assertEquals(testTicket.getDaytype(), ticketDtoList.get(0).getDaytype());
        assertEquals(testTicket.getLocation(), ticketDtoList.get(0).getLocation());
        assertEquals(testTicket.getEventdate(), ticketDtoList.get(0).getEventdate());
        assertEquals(testTicket.getPrice(), ticketDtoList.get(0).getPrice());
    }

}