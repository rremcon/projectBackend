package com.example.projectbackend.dto;
import com.example.projectbackend.model.Account;
import com.example.projectbackend.model.Ticket;
import javax.persistence.OneToMany;
import java.util.Collection;

public class OrderDto {

    public Long orderid;
    public String selectedticket;
    public int quantity;

    public double price;
    public double totalprice;

    public Account account;
    public Ticket ticket;


    public String getSelectedticket() {
        return selectedticket;
    }

    public void setSelectedticket(String selectedticket) {
        this.selectedticket = selectedticket;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
