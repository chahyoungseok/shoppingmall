package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String authority;

    @Column(nullable = false)
    private String e_mail;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Product> product_list = new ArrayList<>();
}
