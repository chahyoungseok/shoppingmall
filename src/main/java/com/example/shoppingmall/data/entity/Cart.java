package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@ToString
@Table(name="cart")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int count; // 수량

    /** ManyToOne의 기본 Fetch는 EAGER */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

    @Builder
    public Cart(Long id, int count, User user, Product product) {
        this.id = id;
        this.count = count;
        this.user = user;
        this.product = product;
    }
}
