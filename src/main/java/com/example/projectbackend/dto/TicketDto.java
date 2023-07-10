package com.example.projectbackend.dto;

public class TicketDto {

    public Long id;
    public String eventname;
    public String tickettype;
    public String daytype;

    public String location;
    public String eventdate;

    public Double price;

    public TicketDto() {
    }

    public TicketDto(Long id, String eventname, String tickettype, String daytype, String location, String eventdate, Double price) {
        this.id = id;
        this.eventname = eventname;
        this.tickettype = tickettype;
        this.daytype = daytype;
        this.location = location;
        this.eventdate = eventdate;
        this.price = price;
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

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

}
