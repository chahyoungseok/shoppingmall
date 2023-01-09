package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@ToString
@Table(name="banner")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgKey;
}
