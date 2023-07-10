package com.example.projectbackend.dto;

public class OrderDto {
    public Long orderid;
    public String selecteditem;
    public int quantity = 1;

    public double price;
    public double totalprice;

    public OrderDto() {
    }

    public OrderDto(Long orderid, String selecteditem, int quantity, double price, double totalprice) {
        this.orderid = orderid;
        this.selecteditem = selecteditem;
        this.quantity = quantity;
        this.price = price;
        this.totalprice = totalprice;
    }


    public String getSelecteditem() {
        return selecteditem;
    }

    public void setSelecteditem(String selecteditem) {
        this.selecteditem = selecteditem;
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

}
