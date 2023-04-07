package com.example.projectbackend.repository;
import com.example.projectbackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
//    public Iterable<Ticket> findByEventDate(LocalDate date);
}
