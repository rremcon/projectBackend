package com.example.projectbackend.model;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventname;
    private String tickettype;
    private String daytype;
    private String location;
    private LocalDate eventdate;
    private Double price;

    @ManyToOne
    Order order;

    public Ticket() {
    }

    public Ticket(Long id, String eventname, String tickettype, String daytype, String location, LocalDate eventdate, Double price, Order order) {
        this.id = id;
        this.eventname = eventname;
        this.tickettype = tickettype;
        this.daytype = daytype;
        this.location = location;
        this.eventdate = eventdate;
        this.price = price;
        this.order = order;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getTickettype() {
        return tickettype;
    }

    public void setTickettype(String tickettype) {
        this.tickettype = tickettype;
    }

    public String getDaytype() {
        return daytype;
    }

    public void setDaytype(String daytype) {
        this.daytype = daytype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEventdate() {
        return eventdate;
    }

    public void setEventdate(LocalDate eventdate) {
        this.eventdate = eventdate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
