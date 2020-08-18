package com.plantify.cart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int productId;
private String productName;
private String productCategory;
private int price;
private String email;
private String phone;
private String address;
private String city;
private int pin;
}
