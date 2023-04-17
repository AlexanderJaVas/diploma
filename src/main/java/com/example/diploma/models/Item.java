package com.example.diploma.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, columnDefinition = "text", unique = true)
    @NotEmpty(message = "Наименование товара не может быть пустым")
    private String title;

    @NotEmpty(message = "Описание товара не может быть пустым")
    @Column(name = "description", nullable = false, columnDefinition = "text", unique = true)
    private String description;

    @Min(value = 1, message = "Минимальная цена товара 1 рубль")
    @Column(name = "price", nullable = false)
    private float price;

    @NotEmpty(message = "Склад по нахождению товара не может быть пустым")
    @Column(name = "warehouse", nullable = false)
    private String warehouse;

    @NotEmpty(message = "Информация о продавце не может быть пустой")
    @Column(name = "seller", nullable = false)
    private String seller;

    @ManyToOne(optional = false)
    public Category category;

    private LocalDateTime dateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    private List<Image> imageList = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "item_cart", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> personList;

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private List<Order> orderList;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    @PrePersist
    private void init() {
        dateTime = LocalDateTime.now();
    }

    public void addImageToItem(Image image) {
        image.setItem(this);
        imageList.add(image);
    }

}
