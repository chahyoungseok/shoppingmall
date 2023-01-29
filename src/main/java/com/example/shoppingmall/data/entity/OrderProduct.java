package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Table(name="order_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int count; // 수량

    /** ManyToOne의 기본 Fetch는 EAGER */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Order order;

    public void setOrder(Order order){
        if (this.order != null){
            this.order.getOrderProductList().remove(this);
        }

        this.order = order;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

}
