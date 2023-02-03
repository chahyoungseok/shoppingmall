package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@ToString
@Table(name="favorite")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    public Favorite(Long id, User user, Product product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }
}
