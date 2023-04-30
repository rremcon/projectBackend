package com.example.projectbackend.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderid;
    private String selectedticket;
    private int quantity;
    private double price;
    private double totalprice;

    @ManyToOne
//    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany (mappedBy = "order")
    private List<Ticket> tickets;

    @OneToMany (mappedBy = "order")
    private List<Product> products;

    @OneToOne
    private Mail mail;

    public Order() {
    }

    public Order(Long orderid, String selectedticket, int quantity, double price) {
        this.orderid = orderid;
        this.selectedticket = selectedticket;
        this.quantity = quantity;
        this.price = price;
    }

    public Order(Long orderid, String selectedticket, int quantity, double price, double totalprice) {
        this.orderid = orderid;
        this.selectedticket = selectedticket;
        this.quantity = quantity;
        this.price = price;
        this.totalprice = totalprice;
    }

    public Long getOrderid() {
        return orderid;
    }
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getSelectedticket() {
        return selectedticket;
    }
    public void setSelectedticket(String selectedticket) {
        this.selectedticket = selectedticket;
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

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addTickets(Ticket tickets) {
        if (this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
        this.tickets.add(tickets);
    }

    public double calculateAmount() {
        return this.quantity * this.price;
    }

}

