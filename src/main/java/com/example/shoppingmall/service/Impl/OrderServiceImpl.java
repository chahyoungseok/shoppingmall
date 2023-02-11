package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.queryselect.QueryOrderProduct;
import com.example.shoppingmall.data.dto.queryselect.ReadOrderQuery;
import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.response.ResponseOrder;
import com.example.shoppingmall.data.entity.Order;
import com.example.shoppingmall.data.entity.OrderProduct;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.order.OrderProductRepository;
import com.example.shoppingmall.repository.order.OrderRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ResponseOrder> read_order(User user) {
        User userPersisted = entityManager.find(User.class, user.getId());
        if(userPersisted == null){
            return null;
        }

        List<Order> orderList = userPersisted.getOrderList();
        if (orderList == null) {
            return null;
        }

        List<ResponseOrder> result = new ArrayList<>();

        for(Order order : orderList) {
            List<OrderProduct> orderProductList = order.getOrderProductList();

            List<ReadOrderQuery> readOrderQueryList = orderProductRepository.findResponseOrder(orderProductList);
            if (readOrderQueryList == null){
                continue;
            }

            result.addAll(readOrderQueryList
                    .stream().map(readOrderQuery -> ResponseOrder
                            .builder()
                            .order(order)
                            .readOrderQuery(readOrderQuery)
                            .build()).toList());
        }

        return result;
    }

    @Override
    @Transactional
    public List<ResponseOrder> create_order(User user, RequestOrder requestOrder) {
        User userPersisted = entityManager.find(User.class, user.getId());

        if (userPersisted == null || requestOrder.getQueryOrderProductList().isEmpty()) {
            return null;
        }

        Order order = requestOrder.toEntity();

        Order savedOrder = orderRepository.save(order);
        userPersisted.addOrder(savedOrder);

        List<OrderProduct> orderProductList = productRepository.findByIdList(requestOrder.getQueryOrderProductList()
                .stream().map(QueryOrderProduct::getProduct_id).toList())
                .stream().map(product -> OrderProduct.builder()
                        .id(null)
                        .count(1)
                        .order(null)
                        .product(product)
                        .size("M")
                        .build()
                ).toList();

        // 추후 로직 개선
        for(OrderProduct orderProduct : orderProductList) {
            for(QueryOrderProduct queryOrderProduct : requestOrder.getQueryOrderProductList()){
                if(Objects.equals(orderProduct.getProduct().getId(), queryOrderProduct.getProduct_id())) {
                    orderProduct.setOption(queryOrderProduct.getCount(), queryOrderProduct.getSize());
                    break;
                }
            }
        }

        List<OrderProduct> savedOrderProductList = orderProductRepository.saveAll(orderProductList);
        savedOrder.addAllOrderProduct(savedOrderProductList);

        return read_order(userPersisted);
    }
}
