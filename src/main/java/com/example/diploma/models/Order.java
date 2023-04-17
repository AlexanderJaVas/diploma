package com.example.diploma.models;

import com.example.diploma.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Person person;

    private int count;
    private float price;
    private LocalDateTime dateTime;
    private OrderStatus orderStatus;

    public Order(String number, Item item,
                 Person person, int count, float price, OrderStatus orderStatus) {
        this.number = number;
        this.item = item;
        this.person = person;
        this.count = count;
        this.price = price;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @PrePersist()
    private void init() {
        dateTime = LocalDateTime.now();
    }
}
