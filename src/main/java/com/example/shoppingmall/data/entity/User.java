package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // UserID, 실제 로그인하는 ID

    @Column(nullable = false)
    private String nickname; // Username, 실제 유저 이름

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String telephone;

    @Column(nullable = false, unique = true)
    private String e_mail;

    @Column(nullable = false, unique = true)
    private String address;

    @Column(nullable = false)
    private String authority;

    /** OneToMany의 기본 Fetch는 LAZY */
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        order.setUser(this);
        this.orderList.add(order);
    }
}
