package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.queryselect.RequestOrderProduct;
import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.response.ResponseOrder;
import com.example.shoppingmall.data.entity.Order;
import com.example.shoppingmall.data.entity.OrderProduct;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.order.OrderProductRepository;
import com.example.shoppingmall.repository.order.OrderRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.service.OrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ResponseOrder> read_order(User user) {
        User userPersisted = entityManager.find(User.class, user.getId());

        List<Order> orderList = userPersisted.getOrderList();

        if (orderList == null) {
            return null;
        }

        List<ResponseOrder> result = new ArrayList<>();

        for(Order order : orderList) {
            List<OrderProduct> orderProductList = order.getOrderProductList();

            result.addAll(orderProductRepository.findResponseOrder(orderProductList)
                    .stream().map(readOrderQuery -> new ResponseOrder(
                            order.getOrderDate(),
                            order.getOrderStatus(),
                            readOrderQuery.getCount(),
                            readOrderQuery.getPrice(),
                            readOrderQuery.getSize(),
                            readOrderQuery.getImgKey()
                    )).toList());
        }

        return result;
    }

    @Override
    @Transactional
    public List<ResponseOrder> create_order(User user, RequestOrder requestOrder) {
        User userPersisted = entityManager.find(User.class, user.getId());

        if (userPersisted == null || requestOrder.getRequestOrderProductList().isEmpty()) {
            return null;
        }

        Order order = new Order(null,
                requestOrder.getOrder_date(),
                requestOrder.getOrder_status(),
                null, new ArrayList<>());

        userPersisted.addOrder(order);
        entityManager.flush();
        orderRepository.save(order);

        List<OrderProduct> orderProductList = productRepository.findByIdList(requestOrder.getRequestOrderProductList()
                .stream().map(RequestOrderProduct::getProduct_id).toList())
                .stream().map(product -> new OrderProduct(
                        null,
                        1,
                        null,
                        product
                )).toList();

        /** 추후 로직 개선 **/
        for(OrderProduct orderProduct : orderProductList) {
            for(RequestOrderProduct requestOrderProduct : requestOrder.getRequestOrderProductList()){
                if(orderProduct.getProduct().getId() == requestOrderProduct.getProduct_id()) {
                    orderProduct.setCount(requestOrderProduct.getCount());
                    break;
                }
            }
        }

        order.addAllOrderProduct(orderProductList);
        orderProductRepository.saveAll(orderProductList);

        return read_order(userPersisted);
    }
}
