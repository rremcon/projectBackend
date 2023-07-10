package com.example.projectbackend.model;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderid;
    private String selecteditem;
    private int quantity = 1;
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


    @OneToOne
    private Mail mail;

    public Order() {
    }

    public Order(Long orderid, String selecteditem, int quantity, double price) {
        this.orderid = orderid;
        this.selecteditem = selecteditem;
        this.quantity = quantity;
        this.price = price;
    }

    public Order(Long orderid, String selecteditem, int quantity, double price, double totalprice) {
        this.orderid = orderid;
        this.selecteditem = selecteditem;
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

    public String getSelecteditem() {
        return selecteditem;
    }

    public void setSelecteditem(String selecteditem) {
        this.selecteditem = selecteditem;
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

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public double calculateAmount() {
        return this.quantity * this.price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return quantity == order.quantity && Double.compare(order.price, price) == 0 && Double.compare(order.totalprice, totalprice) == 0 && Objects.equals(selecteditem, order.selecteditem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selecteditem, quantity, price, totalprice);
    }

}

