package com.example.projectbackend.repository;
import com.example.projectbackend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;

public interface AccountRepository extends JpaRepository<Account, Long> {
//    public Iterable<Account> findById();
//    public Iterable<Account> findByBirthDate(LocalDate date);
}
