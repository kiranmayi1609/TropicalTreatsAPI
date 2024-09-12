package com.example.test.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Products")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private double price;
    @Column(length = 20000)
    private String imageUrl;
    private String description ;
}





