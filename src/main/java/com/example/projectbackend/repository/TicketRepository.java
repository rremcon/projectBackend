package com.example.projectbackend.repository;
import com.example.projectbackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
//    public Iterable<Ticket> findByEventDate(LocalDate date);
}
