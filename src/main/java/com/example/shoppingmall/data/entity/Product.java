package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Table(name="product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DB pk

    @Column(nullable = false)
    private String name; // 제품명

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description; // 제품 설명

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String imgKey;

    @Column(nullable = false)
    private LocalDateTime date; // 상품 등록 날짜

    @Column(nullable = false)
    private int hits; // 조회 수

    /** ManyToOne의 기본 Fetch는 EAGER */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 다대일 매핑에서 일 쪽의 엔티티에서 참조하는 fk 이름을 적는다
    @ToString.Exclude
    private User user;

    @Builder
    public Product(Long id, String name, int price, String category, String description, String size, String imgKey, LocalDateTime date, int hits, User user) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.size = size;
        this.imgKey = imgKey;
        this.date = date;
        this.hits = hits;
        this.user = user;
    }

    public void updateProduct(String name, int price, String category, String description, String size, String imgKey) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.size = size;
        this.imgKey = imgKey;
    }
}
