package com.example.projectbackend.service;
import com.example.projectbackend.dto.TicketDto;
import com.example.projectbackend.exceptions.RecordNotFoundException;
import com.example.projectbackend.model.Ticket;
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
class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;
    @InjectMocks
    TicketService ticketService;

    @Captor
    ArgumentCaptor<Ticket> captor;

    Ticket testTicket1;
    Ticket testTicket2;
    TicketDto testTicketDto1;
    TicketDto testTicketDto2;


    @BeforeEach
    void setUp() {

        testTicket1 = new Ticket(1L, "F1", "Regular", "Friday", "Zandvoort", LocalDate.now(), 90.0, null);
        testTicket2 = new Ticket(2L, "MGP", "Silver", "Saturday", "Assen", LocalDate.now(), 140.0, null);
        testTicketDto1 = new TicketDto(1L, "F1", "Regular", "Friday", "Zandvoort", LocalDate.now().toString(), 90.0);
        testTicketDto2 = new TicketDto(2L, "MGP", "Silver", "Saturday", "Assen", LocalDate.now().toString(), 140.0);
    }


    @Test
    void createTicket() {

        //        Mockito.when(ticketRepository.save(testTicket2)).thenReturn((testTicket2));
        when(ticketRepository.save(any(Ticket.class))).thenReturn((testTicket2));
        ticketService.createTicket(testTicketDto2);

        verify(ticketRepository, times(1)).save(captor.capture());
        Ticket captured = captor.getValue();

        assertEquals(testTicket2.getId(), captured.getId());

        assertEquals(testTicket2.getId(), testTicketDto2.getId());
        assertEquals(testTicket2.getEventname(), testTicketDto2.getEventname());
        assertEquals(testTicket2.getTickettype(), testTicketDto2.getTickettype());
        assertEquals(testTicket2.getDaytype(), testTicketDto2.getDaytype());
        assertEquals(testTicket2.getLocation(), testTicketDto2.getLocation());
        assertEquals(testTicket2.getEventdate().toString(), testTicketDto2.getEventdate());
        assertEquals(testTicket2.getPrice(), testTicketDto2.getPrice());
    }


    @Test
    void getTicket() {
        when(ticketRepository.findById(2L)).thenReturn(Optional.of(testTicket2));

        ticketService.getTicket(2L);

        assertEquals(testTicket2.getId(), testTicketDto2.getId());
    }

    @Test
    void getTicketThrowsRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> ticketService.getTicket(null));
    }


    @Test
    void ReturnTicketCorrect() {

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(testTicket1));
        ticketService.getTicket(1L);

        assertEquals(testTicket1.getId(), testTicketDto1.getId());
        assertEquals(testTicket1.getEventname(), testTicketDto1.getEventname());
        assertEquals(testTicket1.getTickettype(), testTicketDto1.getTickettype());
        assertEquals(testTicket1.getDaytype(), testTicketDto1.getDaytype());
        assertEquals(testTicket1.getLocation(), testTicketDto1.getLocation());
        assertEquals(testTicket1.getEventdate().toString(), testTicketDto1.getEventdate());
        assertEquals(testTicket1.getPrice(), testTicketDto1.getPrice());
    }


    @Test
    void getTickets() {
        Mockito.when(ticketRepository.findAll()).thenReturn(List.of(testTicket1, testTicket2));

        List<TicketDto> ticketDtoList = ticketService.getTickets();

        assertEquals(testTicket1.getId(), ticketDtoList.get(0).getId());
        assertEquals(testTicket1.getEventname(), ticketDtoList.get(0).getEventname());
        assertEquals(testTicket1.getTickettype(), ticketDtoList.get(0).getTickettype());
        assertEquals(testTicket1.getDaytype(), ticketDtoList.get(0).getDaytype());
        assertEquals(testTicket1.getLocation(), ticketDtoList.get(0).getLocation());
        assertEquals(testTicket1.getEventdate().toString(), ticketDtoList.get(0).getEventdate());
        assertEquals(testTicket1.getPrice(), ticketDtoList.get(0).getPrice());

        assertEquals(testTicket2.getId(), ticketDtoList.get(1).getId());
        assertEquals(testTicket2.getEventname(), ticketDtoList.get(1).getEventname());
        assertEquals(testTicket2.getTickettype(), ticketDtoList.get(1).getTickettype());
        assertEquals(testTicket2.getDaytype(), ticketDtoList.get(1).getDaytype());
        assertEquals(testTicket2.getLocation(), ticketDtoList.get(1).getLocation());
        assertEquals(testTicket2.getEventdate().toString(), ticketDtoList.get(1).getEventdate());
        assertEquals(testTicket2.getPrice(), ticketDtoList.get(1).getPrice());
    }


    @Test
    void updateTicket() {

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(testTicket1));
        when(ticketRepository.existsById(1L)).thenReturn(true);

        ticketService.updateTicket(1L, testTicketDto1);

        verify(ticketRepository, times(1)).save(captor.capture());

        Ticket captured = captor.getValue();

        assertEquals(testTicket1.getPrice(), captured.getPrice());
    }

    @Test
    void deleteTicket() {

        ticketService.deleteTicket(9003L);

        verify(ticketRepository).deleteById(9003L);
    }

}