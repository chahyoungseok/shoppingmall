package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name="user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String authority;

    /** OneToMany의 기본 Fetch는 LAZY */
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Order> orderList = new ArrayList<>();

    @Builder
    public User(Long id, String username, String nickname, String password, String telephone, String email, String address, String authority) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.authority = authority;
    }

    public void updateUser(String nickname, String telephone, String email, String address) {
        this.nickname = nickname;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public void changePWD(String password) {
        this.password = password;
    }

    public void upgradeAuth(String authority) {
        this.authority = authority;
    }

    public void addOrder(Order order) {
        order.setUser(this);
        getOrderList().add(order);
    }
}
