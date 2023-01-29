package com.example.shoppingmall.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate; // 주문 날짜

    @Column(nullable = false)
    private String orderStatus; // 주문 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    public void setUser(User user){
        if (this.user != null){
            this.user.getOrderList().remove(this);
        }

        this.user = user;
    }

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<OrderProduct> orderProductList = new ArrayList<>();

    public void addAllOrderProduct(List<OrderProduct> orderProductList) {
        orderProductList.forEach(orderProduct -> orderProduct.setOrder(this));
        getOrderProductList().addAll(orderProductList);
    }
}
