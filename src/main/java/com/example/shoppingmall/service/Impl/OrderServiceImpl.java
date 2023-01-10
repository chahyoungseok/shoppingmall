package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.response.ResponseOrder;
import com.example.shoppingmall.data.entity.Order;
import com.example.shoppingmall.data.entity.OrderProduct;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.order.OrderProductRepository;
import com.example.shoppingmall.repository.order.OrderRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.repository.user.UserRepository;
import com.example.shoppingmall.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final ProductRepository productRepository;

    public ResponseOrder responseOrder;

    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ResponseOrder> read_order(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return null;
        }

        List<Order> orderList = user.getOrderList();
        List<ResponseOrder> result = new ArrayList<>();

        for(Order order : orderList) {
            List<OrderProduct> orderProductList = order.getOrderProductList();

            for(OrderProduct orderProduct : orderProductList) {
                responseOrder = new ResponseOrder(
                        order.getOrderDate(),
                        order.getOrderStatus(),
                        orderProduct.getCount(),
                        orderProduct.getProduct().getPrice(),
                        orderProduct.getProduct().getSize(),
                        orderProduct.getProduct().getImgKey()
                );

                result.add(responseOrder);
            }
        }

        return result;
    }

    @Override
    public List<ResponseOrder> create_order(RequestOrder requestOrder) {
        User user = userRepository.findByUsername(requestOrder.getUsername());
        Product product = productRepository.findById(requestOrder.getProduct_id()).orElse(null);

        if (user == null || product == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderDate(requestOrder.getOrder_date());
        order.setOrderStatus(requestOrder.getOrder_status());
        order.setUser(user);

        user.addOrder(order);
        orderRepository.save(order);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setCount(requestOrder.getCount());
        orderProduct.setProduct(product);
        orderProduct.setOrder(order);

        orderProductRepository.save(orderProduct);

        return read_order(user.getUsername());
    }
}
