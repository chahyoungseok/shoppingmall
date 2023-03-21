package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.queryselect.QueryOrderProduct;
import com.example.shoppingmall.data.dto.queryselect.ReadOrderQuery;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(UserRepository userRepository, OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<List<ResponseOrder>> read_order(User user) {
        User userPersisted = userRepository.findByUsername(user.getUsername());
        if(userPersisted == null){
            return null;
        }

        List<Order> orderList = userPersisted.getOrderList();
        if (orderList == null) {
            return null;
        }

        List<List<ResponseOrder>> result = new ArrayList<>();

        int orderList_size = orderList.size();
        for(int i=orderList_size - 1; i>=0; i--) {
            Order order = orderList.get(i);
            List<OrderProduct> orderProductList = order.getOrderProductList();

            List<ReadOrderQuery> readOrderQueryList = orderProductRepository.findResponseOrder(orderProductList);
            if (readOrderQueryList == null){
                continue;
            }

            result.add(new ArrayList<>(readOrderQueryList
                    .stream().map(readOrderQuery -> ResponseOrder
                            .builder()
                            .order(order)
                            .readOrderQuery(readOrderQuery)
                            .build()).toList()));
        }

        return result;
    }

    @Override
    @Transactional
    public List<List<ResponseOrder>> create_order(User user, RequestOrder requestOrder) {
        User userPersisted = userRepository.findByUsername(user.getUsername());

        if (userPersisted == null || requestOrder.getQueryOrderProductList().isEmpty()) {
            return null;
        }

        Order order = requestOrder.toEntity();

        Order savedOrder = orderRepository.save(order);
        userPersisted.addOrder(savedOrder);

        List<Product> productList = productRepository.findByIdList(requestOrder.getQueryOrderProductList()
                .stream().map(QueryOrderProduct::getProduct_id).toList());

        List<OrderProduct> orderProductList = new ArrayList<>();

        for(QueryOrderProduct queryOrderProduct : requestOrder.getQueryOrderProductList()){
            Product equal_product = null;
            for(Product product : productList) {
                if(Objects.equals(product.getId(), queryOrderProduct.getProduct_id())){
                    equal_product = product;
                    break;
                }
            }

            orderProductList.add(new OrderProduct(null, queryOrderProduct.getCount(), order, equal_product, queryOrderProduct.getSize()));
        }

        List<OrderProduct> savedOrderProductList = orderProductRepository.saveAll(orderProductList);
        savedOrder.addAllOrderProduct(savedOrderProductList);

        return read_order(userPersisted);
    }
}
